package com.maxmium.infiniaddons.command;

import com.maxmium.infiniaddons.RecipeEnergyInjectorManagerImpl;
import com.maxmium.infiniaddons.events.EventHandler;
import morph.avaritia.Avaritia;
import morph.avaritia.item.ItemResource;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentTranslation;

public class CommandDebug extends CommandBase{
    @Override
    public String getName() {
        return "tester";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "commands.tester.usage";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        EntityPlayer player= CommandBase.getPlayer(server,sender,args[0]);

        player.sendMessage(new TextComponentTranslation(String.valueOf(RecipeEnergyInjectorManagerImpl.INSTANCE.getenergy(new ItemStack(new ItemResource(Avaritia.tab,"resource"),1,4)))));
    }
    @Override
    public int getRequiredPermissionLevel(){
        return 2;
    }
}
