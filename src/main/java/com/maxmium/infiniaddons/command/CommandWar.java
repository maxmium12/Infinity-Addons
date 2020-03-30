package com.maxmium.infiniaddons.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.server.command.CommandTreeBase;

public class CommandWar extends CommandTreeBase {
    public CommandWar(){
        this.addSubcommand(new CommandBase() {
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
                EnumFacing facing=sender.getCommandSenderEntity().getHorizontalFacing();
                switch (facing){
                    case NORTH:

                }
            }
        });
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return null;
    }
}
