package com.maxmium.infiniaddons.common;

import com.maxmium.infiniaddons.ModBlocks;
import com.maxmium.infiniaddons.ModItems;
import com.maxmium.infiniaddons.api.IModelRegister;
import com.maxmium.infiniaddons.command.CommandHandler;
import com.maxmium.infiniaddons.entity.EntityLoader;
import com.maxmium.infiniaddons.gui.GuiHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public  class CommonProxy {
    public void preInit(FMLPreInitializationEvent evt) {
        ModItems.init();
        ModBlocks.init();
        new GuiHandler();
        new EntityLoader();
    }

    public void serverStarting(FMLServerStartingEvent evt) {
        new CommandHandler(evt);

    }
}
