package com.maxmium.infiniaddons.events;

import com.maxmium.infiniaddons.infiniaddons;
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
import net.minecraft.entity.player.InventoryPlayer;
        import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumFacing;
        import net.minecraft.util.EnumHand;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Loader;
        import net.minecraftforge.fml.common.Mod;
        import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.items.IItemHandlerModifiable;
        import net.minecraftforge.items.ItemStackHandler;
        import net.minecraftforge.items.wrapper.CombinedInvWrapper;
        import scala.Array;

        import javax.swing.plaf.basic.BasicComboBoxUI;
        import java.util.ArrayList;
        import java.util.List;
@Mod.EventBusSubscriber(modid=infiniaddons.MODID)
public class EventHandler {
    public EventHandler(){
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public static void onPlayerConnect(PlayerEvent.PlayerLoggedInEvent evt){
        if (Loader.isModLoaded("projecte")) {
            EntityPlayer player = evt.player;
            InventoryPlayer playerivt = new InventoryPlayer(player);
            if (player.isServerWorld()) {
                ThreadTimer threadTimer = new ThreadTimer(player,"timer");
                threadTimer.start();
            }
        }
    }

    @SubscribeEvent
    public void onClientPacket(FMLNetworkEvent.ClientCustomPacketEvent evt) {
        FMLLog.getLogger().info(new String(evt.getPacket().payload().array()));
    }
}
