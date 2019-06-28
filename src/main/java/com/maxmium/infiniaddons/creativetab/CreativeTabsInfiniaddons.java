package com.maxmium.infiniaddons.creativetab;

import com.maxmium.infiniaddons.item.ItemEnergyDust;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabsInfiniaddons extends CreativeTabs {
    public CreativeTabsInfiniaddons() {
        super("infinity addons");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(new ItemEnergyDust());
    }
}
