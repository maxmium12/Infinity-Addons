package com.maxmium.infiniaddons.client;

import com.maxmium.infiniaddons.ModBlocks;
import com.maxmium.infiniaddons.ModItems;

public class ItemRenderLoader {
    public ItemRenderLoader(){
        ModItems.registerRenders();
        ModBlocks.registerRenders();
    }
}
