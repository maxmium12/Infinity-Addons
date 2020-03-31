package com.maxmium.infiniaddons.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.server.command.CommandTreeBase;

public class CommandWar extends CommandTreeBase {
    public CommandWar() {
        this.addSubcommand(new WarSubStart());
    }
    @Override
    public String getName() {
        return "war";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/war";
    }
}
