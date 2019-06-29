package com.maxmium.infiniaddons.item;

import com.maxmium.infiniaddons.creativetab.CreativeTabsLoader;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemEnergyDust extends Item {
    public ItemEnergyDust(){
        super();
        this.setUnlocalizedName("energyDust");
        this.setCreativeTab(CreativeTabsLoader.tabInfiniaddons);


    }
}
