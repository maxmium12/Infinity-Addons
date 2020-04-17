package com.maxmium.infiniaddons.command;

import com.feed_the_beast.ftblib.lib.EnumTeamStatus;
import com.feed_the_beast.ftblib.lib.data.FakeForgePlayer;
import com.feed_the_beast.ftblib.lib.data.ForgePlayer;
import com.feed_the_beast.ftblib.lib.data.ForgeTeam;
import com.feed_the_beast.ftblib.lib.data.Universe;
import com.feed_the_beast.ftblib.lib.math.ChunkDimPos;
import com.feed_the_beast.ftbutilities.data.ClaimedChunk;
import com.feed_the_beast.ftbutilities.data.ClaimedChunks;
import com.maxmium.infiniaddons.capability.CapabilityHandler;
import com.maxmium.infiniaddons.capability.ICapabilityWar;
import com.maxmium.infiniaddons.command.war.WarUtils;
import com.maxmium.infiniaddons.utils.CommonUtils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.common.Loader;
import omtteam.openmodularturrets.OpenModularTurrets;
import omtteam.openmodularturrets.tileentity.TurretBase;

import java.util.*;

public class WarSubStart extends CommandBase {
    @Override
    public String getName() {
        return "start";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/war start 开始一次战争";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (sender.getCommandSenderEntity().hasCapability(CapabilityHandler.capabilityWar, null)) {
            ICapabilityWar capabilityWar=sender.getCommandSenderEntity().getCapability(CapabilityHandler.capabilityWar,null);
            if(sender.getEntityWorld().getTotalWorldTime()-capabilityWar.getWarEndTime()<=36000){
                throw new CommandException(new TextComponentTranslation("war.infiniaddons.cooling_down").toString());
            }
            EnumFacing facing = sender.getCommandSenderEntity().getHorizontalFacing();
            ForgePlayer player = Universe.get().getPlayer(sender);
            ChunkDimPos Pos = new ChunkDimPos(sender.getCommandSenderEntity());
            int x = Pos.posX;
            int z = Pos.posZ;
            int dim = Pos.dim;
            ChunkDimPos chunkDimPos = Pos;
            switch (facing) {
                case NORTH:
                    chunkDimPos = new ChunkDimPos(x, z - 1, dim);
                    break;
                case SOUTH:
                    chunkDimPos = new ChunkDimPos(x, z + 1, dim);
                    break;
                case EAST:
                    chunkDimPos = new ChunkDimPos(x + 1, z, dim);
                    break;
                case WEST:
                    chunkDimPos = new ChunkDimPos(x - 1, z, dim);
                    break;
            }
            if (ClaimedChunks.instance.getChunk(chunkDimPos) == null) {
                throw new CommandException("command.war.no_team");
            }
            List<EntityPlayerMP> playerList=ClaimedChunks.instance.getChunk(chunkDimPos).getTeam().getOnlineMembers();
            ClaimedChunk chunk = ClaimedChunks.instance.getChunk(chunkDimPos);
            ForgePlayer fakeplayer = Universe.get().fakePlayer;
            if(fakeplayer==null){
                throw new CommandException(new TextComponentTranslation("war.infiniaddons.debug").toString());
            }
            ArrayList<EntityPlayer> list = new ArrayList<>(playerList);
            list.addAll(player.team.getOnlineMembers());
            WarUtils.battlingteam.add(list);
            WarUtils.map.put(list,chunkDimPos);
            ForgeTeam team = fakeplayer.team;
            for (EntityPlayer player1:player.team.getOnlineMembers()) {
                if (player1.hasCapability(CapabilityHandler.capabilityWar, null)) {
                    ICapabilityWar war = player1.getCapability(CapabilityHandler.capabilityWar, null);
                    war.setWar(true);
                    war.setUnableWar(false);
                    CommonUtils.syncWarCapability((EntityPlayerMP) player1);
                    war.setDefence(false);
                }
                ForgePlayer forgePlayer = Universe.get().getPlayer(player1.getUniqueID());
                team.setStatus(forgePlayer, EnumTeamStatus.ALLY);
            }
            for (EntityPlayer player1:playerList) {
                if (player1.hasCapability(CapabilityHandler.capabilityWar, null)) {
                    ICapabilityWar war = player1.getCapability(CapabilityHandler.capabilityWar, null);
                    war.setWar(true);
                    war.setUnableWar(false);
                    war.setDefence(true);
                    CommonUtils.syncWarCapability((EntityPlayerMP)player1);
                }
                WarUtils.instance.setTurrentAttackPlayer(sender.getEntityWorld(),chunkDimPos.posX,chunkDimPos.posZ,true);
                ClaimedChunks.instance.unclaimChunk(chunk.getTeam().getOwner(), chunkDimPos);
                ClaimedChunks.instance.claimChunk(fakeplayer, chunkDimPos);
                ForgePlayer forgePlayer = Universe.get().getPlayer(player1.getUniqueID());
                team.setStatus(forgePlayer, EnumTeamStatus.ALLY);
            }
            server.sendMessage(new TextComponentTranslation("war.infiniaddons.start"));
        }
    }
}
