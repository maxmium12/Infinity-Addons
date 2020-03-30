package com.maxmium.infiniaddons.command;

import com.feed_the_beast.ftblib.lib.data.ForgePlayer;
import com.feed_the_beast.ftblib.lib.data.Universe;
import com.feed_the_beast.ftbutilities.data.ClaimedChunks;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumFacing;

public class WarSubStart extends CommandBase{
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
            //TODO
            EnumFacing facing=sender.getCommandSenderEntity().getHorizontalFacing();
            ForgePlayer player=Universe.get().getPlayer(sender);
            switch (facing){
                case NORTH:
                case SOUTH:
                case EAST:
                case WEST:
            }
}
}
