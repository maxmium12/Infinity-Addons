package com.maxmium.infiniaddons.common;

import com.maxmium.infiniaddons.ModBlocks;
import com.maxmium.infiniaddons.ModItems;
import com.maxmium.infiniaddons.api.IModelRegister;
import com.maxmium.infiniaddons.command.CommandHandler;
import com.maxmium.infiniaddons.gui.GuiHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public  class CommonProxy {
    public void preInit() {
        ModItems.init();
        ModBlocks.init();
        new GuiHandler();
    }
    public void serverStarting(FMLServerStartingEvent evt){
        new CommandHandler(evt);

    }

    public void addModelRegister(IModelRegister registryObject) {
    }
}
