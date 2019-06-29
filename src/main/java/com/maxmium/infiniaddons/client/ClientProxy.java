package com.maxmium.infiniaddons.client;

import com.maxmium.infiniaddons.common.CommonProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit()
    {
        super.preInit();
        new ItemRenderLoader();
    }
}
