package com.maxmium.infiniaddons.command;

import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class CommandHandler {
    public CommandHandler(FMLServerStartingEvent event){
        event.registerServerCommand(new CommandRecipes());
        event.registerServerCommand(new CommandDebug());
        event.registerServerCommand(new CommandWar());
    }
}
