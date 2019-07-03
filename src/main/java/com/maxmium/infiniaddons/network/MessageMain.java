package com.maxmium.infiniaddons.network;

import com.maxmium.infiniaddons.infiniaddons;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.FMLEventChannel;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class MessageMain {
    static FMLEventChannel channel;
    public static void init(FMLPreInitializationEvent evt){
        MinecraftForge.EVENT_BUS.register(evt);
        FMLCommonHandler.instance().bus().register(evt);
        channel = NetworkRegistry.INSTANCE.newEventDrivenChannel("servermodconnecter");
        channel.register(evt);
    }
}
