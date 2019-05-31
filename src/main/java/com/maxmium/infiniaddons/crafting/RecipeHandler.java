package com.maxmium.infiniaddons.crafting;

import com.maxmium.infiniaddons.RecipeEnergyInjectorManagerImpl;
import com.maxmium.infiniaddons.item.ItemEnergyDust;
import morph.avaritia.Avaritia;
import morph.avaritia.init.ModItems;
import morph.avaritia.item.ItemResource;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class RecipeHandler {
    public static void init(){
        new EnergyInjectorRecipes();
    }
    private static class EnergyInjectorRecipes{
        private ItemResource itemResource=new ItemResource(Avaritia.tab,"resource");
        public EnergyInjectorRecipes(){
            RecipeEnergyInjectorManagerImpl.INSTANCE.addRecipe(
                    new ItemStack(new ItemResource(Avaritia.tab,"neutronium_ingot"))
                    ,new ItemStack(new ItemEnergyDust())
                    ,new ItemStack(Blocks.DIRT),24576000,
                    0.5);
        }
    }
}
