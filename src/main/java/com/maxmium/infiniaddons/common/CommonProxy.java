package com.maxmium.infiniaddons.common;

import com.maxmium.infiniaddons.ModBlocks;
import com.maxmium.infiniaddons.ModItems;
import com.maxmium.infiniaddons.api.IModelRegister;
import com.maxmium.infiniaddons.capability.CapabilityHandler;
import com.maxmium.infiniaddons.command.CommandHandler;
import com.maxmium.infiniaddons.entity.EntityLoader;
import com.maxmium.infiniaddons.events.EventHandler;
import com.maxmium.infiniaddons.gui.GuiHandler;
import com.maxmium.infiniaddons.network.MessageMain;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public  class CommonProxy {
    public void preInit(FMLPreInitializationEvent evt) {
        ModItems.init();
        ModBlocks.init();
        new GuiHandler();
        new EntityLoader();
        new EventHandler();
        new CapabilityHandler();
        new MessageMain(evt);
    }

    public void serverStarting(FMLServerStartingEvent evt) {
        new CommandHandler(evt);

    }
}
