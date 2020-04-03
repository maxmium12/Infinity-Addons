package com.maxmium.infiniaddons.events;

import com.feed_the_beast.ftblib.events.team.ForgeTeamCreatedEvent;
import com.feed_the_beast.ftblib.events.team.ForgeTeamDataEvent;
import com.feed_the_beast.ftblib.lib.EnumTeamStatus;
import com.feed_the_beast.ftblib.lib.data.ForgePlayer;
import com.feed_the_beast.ftblib.lib.data.ForgeTeam;
import com.feed_the_beast.ftblib.lib.data.TeamData;
import com.feed_the_beast.ftblib.lib.data.Universe;
import com.feed_the_beast.ftblib.lib.math.ChunkDimPos;
import com.feed_the_beast.ftbutilities.FTBUtilitiesNotifications;
import com.feed_the_beast.ftbutilities.data.ClaimedChunks;
import com.feed_the_beast.ftbutilities.data.FTBUtilitiesTeamData;
import com.maxmium.infiniaddons.Block.BlockAreaBlock;
import com.maxmium.infiniaddons.Tile.TileAreaBlock;
import com.maxmium.infiniaddons.capability.CapabilityHandler;
import com.maxmium.infiniaddons.capability.CapabilityWar;
import com.maxmium.infiniaddons.capability.ICapabilityWar;
import com.maxmium.infiniaddons.command.war.WarUtils;
import com.maxmium.infiniaddons.infiniaddons;
import com.maxmium.infiniaddons.network.MessageMain;
import com.maxmium.infiniaddons.network.MessageWar;
import com.maxmium.infiniaddons.utils.CommonUtils;
import com.maxmium.infiniaddons.world.WorldAreaBlockPosition;
import crafttweaker.api.event.EntityLivingDeathEvent;
import moze_intel.projecte.api.ProjectEAPI;
import moze_intel.projecte.api.capabilities.IKnowledgeProvider;
import moze_intel.projecte.emc.EMCMapper;
import moze_intel.projecte.gameObjs.container.TransmutationContainer;
import moze_intel.projecte.gameObjs.container.inventory.TransmutationInventory;
import moze_intel.projecte.gameObjs.gui.GUITransmutation;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
        import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
        import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Loader;
        import net.minecraftforge.fml.common.Mod;
        import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.items.IItemHandlerModifiable;
        import net.minecraftforge.items.ItemStackHandler;
        import net.minecraftforge.items.wrapper.CombinedInvWrapper;
        import scala.Array;

        import javax.swing.plaf.basic.BasicComboBoxUI;
import java.util.*;

@Mod.EventBusSubscriber(modid=infiniaddons.MODID)
public class EventHandler {
    public EventHandler(){
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void BlockBreakEvent(BlockEvent.BreakEvent event) {
        if (event.getState().getBlock().equals(new BlockAreaBlock())) {
            if (!event.getWorld().isRemote) {
                if(event.getPlayer().hasCapability(CapabilityHandler.capabilityWar,null)){
                    ICapabilityWar capability=event.getPlayer().getCapability(CapabilityHandler.capabilityWar,null);
                    if(capability.isWar()){
                        event.getPlayer().getServer().sendMessage(new TextComponentTranslation("war.infiniaddons.win"));
                    }
                }
                TileAreaBlock te = (TileAreaBlock) event.getWorld().getTileEntity(event.getPos());
                ClaimedChunks.instance.unclaimChunk(Universe.get().getPlayer(te.getUuid()), new ChunkDimPos(event.getPos(), event.getWorld().provider.getDimension()));
                FTBUtilitiesNotifications.updateChunkMessage((EntityPlayerMP) event.getPlayer(), new ChunkDimPos(event.getPos(), event.getWorld().provider.getDimension()));
            }

        }
    }
    @SubscribeEvent
    public void attachCapabilityOnEntity(AttachCapabilitiesEvent event){
        if(event.getObject() instanceof EntityPlayer) {
            ICapabilitySerializable<NBTTagCompound> provider = new CapabilityWar.ProviderPlayer();
            event.addCapability(new ResourceLocation(infiniaddons.MODID + ":" + "warstat"), provider);
        }
    }
    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event){
        Capability<ICapabilityWar> capability= CapabilityHandler.capabilityWar;
        Capability.IStorage<ICapabilityWar> storage=CapabilityHandler.capabilityWar.getStorage();
        if (event.getOriginal().hasCapability(capability,null)&&event.getEntityPlayer().hasCapability(capability,null)){
            NBTBase nbt=storage.writeNBT(capability,event.getOriginal().getCapability(capability,null),null);
            storage.readNBT(capability,event.getEntityPlayer().getCapability(capability,null),null,nbt);
        }
    }
    @SubscribeEvent
    public void onPlayerLogged(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent event){
        if(event.player.hasCapability(CapabilityHandler.capabilityWar,null)){
            ICapabilityWar eventCapability=event.player.getCapability(CapabilityHandler.capabilityWar,null);
            Boolean iswar=eventCapability.isWar();
            for (EntityPlayer player:Universe.get().getPlayer(event.player.getUniqueID()).team.getOnlineMembers()){
                if(player.hasCapability(CapabilityHandler.capabilityWar,null)){
                    ICapabilityWar playerCapability=player.getCapability(CapabilityHandler.capabilityWar,null);
                    if(playerCapability.isWar()&&!eventCapability.isUnableWar()){
                        event.player.getCapability(CapabilityHandler.capabilityWar,null).setWar(true);
                    }
                }
            }

        }
    }
    @SubscribeEvent
    public void onPlayerDead(LivingDeathEvent event) {
        if (!event.getEntity().world.isRemote) {
            if (event.getEntity() instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) event.getEntity();
                if (player.hasCapability(CapabilityHandler.capabilityWar, null)) {
                    ICapabilityWar capability = player.getCapability(CapabilityHandler.capabilityWar, null);
                    MessageWar message = new MessageWar();
                    Capability.IStorage<ICapabilityWar> storage = CapabilityHandler.capabilityWar.getStorage();
                    if (capability.isWar()) {
                        int deadtimes = capability.getDeadtimes();
                        deadtimes++;
                        capability.setDeadtimes(deadtimes);
                        ForgePlayer forgePlayer = Universe.get().getPlayer(player);
                        List<EntityPlayerMP> onlinePlayer = forgePlayer.team.getOnlineMembers();
                        if (deadtimes >= 3) {
                            capability.setUnableWar(true);
                            capability.setWar(false);
                            capability.setDeadtimes(0);
                            CommonUtils.syncWarCapability((EntityPlayerMP) player);
                        }
                        List<EntityPlayer> players = new ArrayList<>();
                        for (EntityPlayer player1 : onlinePlayer) {
                            if (player1.hasCapability(CapabilityHandler.capabilityWar, null)) {
                                ICapabilityWar capabilityWar = player1.getCapability(CapabilityHandler.capabilityWar, null);
                                if (!capabilityWar.isUnableWar()) {
                                    break;
                                }
                                players.add(player);
                            }
                        }
                        ChunkDimPos pos;
                        List<EntityPlayer> battlingteam = new ArrayList<>();
                        if (!capability.isDefence()) {
                            if (players.equals(onlinePlayer)) {
                                for (ArrayList<EntityPlayer> list : WarUtils.battlingteam) {
                                    for (EntityPlayer player1 : list) {
                                        if (player1 == onlinePlayer.get(0)) {
                                            battlingteam = list;
                                            pos = WarUtils.map.get(list);
                                            break;
                                        }

                                    }
                                }
                                for (EntityPlayer player1 : battlingteam) {
                                    if (player1.hasCapability(CapabilityHandler.capabilityWar, null)) {
                                        ICapabilityWar capabilityWar = player1.getCapability(CapabilityHandler.capabilityWar, null);
                                        capabilityWar.setWarEndTime(event.getEntity().world.getWorldTime());
                                        CommonUtils.syncWarCapability((EntityPlayerMP) player1);
                                    }
                                }

                                player.getServer().sendMessage(new TextComponentTranslation("war.infiniaddons.fail"));
                            }
                        }
                    }
                }
            }
        }
    }
    public void onPlayerJoinTheWorld(EntityJoinWorldEvent event){
        if(!event.getWorld().isRemote&&event.getEntity() instanceof EntityPlayer){
            EntityPlayerMP player= (EntityPlayerMP) event.getEntity();
            if(player.hasCapability(CapabilityHandler.capabilityWar,null)){
               CommonUtils.syncWarCapability(player);
            }
        }
    }

}



