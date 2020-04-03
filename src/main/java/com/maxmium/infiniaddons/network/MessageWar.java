package com.maxmium.infiniaddons.network;

import com.maxmium.infiniaddons.capability.CapabilityHandler;
import com.maxmium.infiniaddons.capability.ICapabilityWar;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageWar implements IMessage {
    public NBTTagCompound nbt;

    @Override
    public void fromBytes(ByteBuf buf) {
        nbt = ByteBufUtils.readTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, nbt);
    }

    public static class MessageHandler implements IMessageHandler<MessageWar,IMessage> {

        @Override
        public IMessage onMessage(MessageWar message, MessageContext ctx) {
            if(ctx.side.isClient()){
                final NBTBase nbt=message.nbt.getTag("warStat");
                Minecraft.getMinecraft().addScheduledTask(new Runnable() {
                    @Override
                    public void run() {
                        EntityPlayer player=Minecraft.getMinecraft().player;
                        if(player.hasCapability(CapabilityHandler.capabilityWar,null)){
                            ICapabilityWar capability=player.getCapability(CapabilityHandler.capabilityWar,null);
                            Capability.IStorage<ICapabilityWar> storage=CapabilityHandler.capabilityWar.getStorage();
                            storage.readNBT(CapabilityHandler.capabilityWar,capability,null,nbt);
                        }
                    }
                });
            }
            return null;
        }
    }
}
