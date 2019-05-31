package com.maxmium.infiniaddons.command;

import com.maxmium.infiniaddons.RecipeEnergyInjectorManagerImpl;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentTranslation;

public class CommandRecipes extends CommandBase {

    @Override
    public String getName() {
        return "getrecipelist";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "commands.getrecipelist.usage";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        EntityPlayer player=CommandBase.getPlayer(server,sender,args[0]);
        player.sendMessage(new TextComponentTranslation(RecipeEnergyInjectorManagerImpl.INSTANCE.toString()));
    }
    @Override
    public int getRequiredPermissionLevel(){
        return 2;
    }
}
