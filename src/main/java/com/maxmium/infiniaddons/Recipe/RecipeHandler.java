package com.maxmium.infiniaddons.Recipe;

import com.maxmium.infiniaddons.RecipeEnergyInjectorManagerImpl;
import com.maxmium.infiniaddons.item.ItemEnergyDust;
import morph.avaritia.Avaritia;
import morph.avaritia.item.ItemResource;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class RecipeHandler {
    public static void init(){

    }
    class EnergyInjectorRecipe{
        public void addRecipe(){
            RecipeEnergyInjectorManagerImpl.INSTANCE.addRecipe(
                    new ItemStack((new ItemResource(Avaritia.tab,"resource")),1,1)
                    ,new ItemStack(new ItemEnergyDust()),new ItemStack(Blocks.DIRT),24576000,0.5);
        }
    }
}
