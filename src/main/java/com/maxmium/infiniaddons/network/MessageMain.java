package com.maxmium.infiniaddons.network;

import com.maxmium.infiniaddons.infiniaddons;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.FMLEventChannel;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class MessageMain {
    static FMLEventChannel channel;
    private static int NextID=0;
    public static SimpleNetworkWrapper instance=NetworkRegistry.INSTANCE.newSimpleChannel(infiniaddons.MODID);
    public MessageMain(FMLPreInitializationEvent event){
        registerMessage(MessageWar.MessageHandler.class,MessageWar.class,Side.CLIENT);
    }
    private static <REQ extends IMessage, REPLY extends IMessage> void registerMessage(Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType, Side side){
        instance.registerMessage(messageHandler,requestMessageType,NextID++,side);
    }
}
