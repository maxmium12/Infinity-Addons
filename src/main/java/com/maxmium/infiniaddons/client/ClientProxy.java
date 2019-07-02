package com.maxmium.infiniaddons.client;

import com.maxmium.infiniaddons.client.entity.EntityRenderLoader;
import com.maxmium.infiniaddons.common.CommonProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent evt)
    {
        super.preInit(evt);
        new ItemRenderLoader();
        new EntityRenderLoader();
    }
}
