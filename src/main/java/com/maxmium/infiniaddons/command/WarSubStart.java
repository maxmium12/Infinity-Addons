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
import com.maxmium.infiniaddons.command.war.WarFakePlayer;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.TextComponentTranslation;

import java.util.UUID;

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
            new WarFakePlayer();
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
                case SOUTH:
                    chunkDimPos = new ChunkDimPos(x, z + 1, dim);
                case EAST:
                    chunkDimPos = new ChunkDimPos(x + 1, z, dim);
                case WEST:
                    chunkDimPos = new ChunkDimPos(x - 1, z, dim);
            }
            if (ClaimedChunks.instance.getChunk(chunkDimPos) == null) {
                throw new CommandException("command.war.no_team");
            }
            ClaimedChunk chunk = ClaimedChunks.instance.getChunk(chunkDimPos);
            PlayerList list = server.getPlayerList();
            ForgePlayer fakeplayer = Universe.get().getPlayer(UUID.fromString("19f51a77-b6fb-3469-a8b6-228ec5ce5961"));
            ClaimedChunks.instance.unclaimChunk(chunk.getTeam().getOwner(), chunkDimPos);
            ClaimedChunks.instance.claimChunk(fakeplayer, chunkDimPos);
            ForgeTeam team = Universe.get().getTeam("WarChunk");
            for (int i = 0; i <= player.team.getOnlineMembers().size(); i++) {
                EntityPlayer player1 = player.team.getOnlineMembers().get(i);
                if (player.team.getOnlineMembers().get(i).hasCapability(CapabilityHandler.capabilityWar, null)) {
                    ICapabilityWar war = player1.getCapability(CapabilityHandler.capabilityWar, null);
                    war.setWar(true);
                }
                ForgePlayer forgePlayer = Universe.get().getPlayer(player1.getUniqueID());
                team.setStatus(forgePlayer, EnumTeamStatus.ALLY);
            }
            for (int i = 0; i <= ClaimedChunks.instance.getChunk(chunkDimPos).getTeam().getOnlineMembers().size(); i++) {
                EntityPlayer player1 = player.team.getOnlineMembers().get(i);
                if (player.team.getOnlineMembers().get(i).hasCapability(CapabilityHandler.capabilityWar, null)) {
                    ICapabilityWar war = player1.getCapability(CapabilityHandler.capabilityWar, null);
                    war.setWar(true);
                }
                ForgePlayer forgePlayer = Universe.get().getPlayer(player1.getUniqueID());
                team.setStatus(forgePlayer, EnumTeamStatus.ALLY);
            }
            server.sendMessage(new TextComponentTranslation("war.infiniaddons.start"));
        }
    }
}
