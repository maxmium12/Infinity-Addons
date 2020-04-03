package com.maxmium.infiniaddons.utils;

import com.maxmium.infiniaddons.capability.CapabilityHandler;
import com.maxmium.infiniaddons.capability.ICapabilityWar;
import com.maxmium.infiniaddons.network.MessageMain;
import com.maxmium.infiniaddons.network.MessageWar;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.Capability;

public class CommonUtils {
    //Use this method must check if player has the capability first
    public static void syncWarCapability(EntityPlayerMP player){
        ICapabilityWar capability=player.getCapability(CapabilityHandler.capabilityWar,null);
        MessageWar message=new MessageWar();
        Capability.IStorage<ICapabilityWar> storage=CapabilityHandler.capabilityWar.getStorage();
        message.nbt=new NBTTagCompound();
        message.nbt.setTag("warStat",storage.writeNBT(CapabilityHandler.capabilityWar,capability,null));
        MessageMain.instance.sendTo(message,(EntityPlayerMP)player);
    }
}
