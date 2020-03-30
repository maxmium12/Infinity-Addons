package com.maxmium.infiniaddons.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityHandler {
    @CapabilityInject(ICapabilityWar.class)
    public static Capability<ICapabilityWar> capabilityWar;
    public CapabilityHandler(){
        CapabilityManager.INSTANCE.register(ICapabilityWar.class,new CapabilityWar.Storage(),CapabilityWar.Implementation.class);
    }
}
