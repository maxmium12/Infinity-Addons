package com.maxmium.infiniaddons.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CreativeTabsLoader {
    public static CreativeTabs tabInfiniaddons;
    public CreativeTabsLoader(FMLPreInitializationEvent event){
        tabInfiniaddons=new CreativeTabsInfiniaddons();
    }

}
