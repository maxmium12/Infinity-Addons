package com.maxmium.infiniaddons.item;

import com.maxmium.infiniaddons.creativetab.CreativeTabsLoader;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

public class ItemEnergySword extends ItemSword {
    public static final ToolMaterial ENERGY= EnumHelper.addToolMaterial("ENERGY",7,153600,64F,64F,10);
    public ItemEnergySword() {
        super(ENERGY);
        this.setUnlocalizedName("energySword");
        this.setCreativeTab(CreativeTabsLoader.tabInfiniaddons);

    }
}
