package com.maxmium.infiniaddons.crafting;

import com.maxmium.infiniaddons.RecipeEnergyInjectorManagerImpl;
import com.maxmium.infiniaddons.item.ItemEnergyDust;
import morph.avaritia.Avaritia;
import morph.avaritia.init.ModItems;
import morph.avaritia.item.ItemResource;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class RecipeHandler {
    public static void init(){
        new EnergyInjectorRecipes();
    }
    private static class EnergyInjectorRecipes{
        private ItemResource itemResource=new ItemResource(Avaritia.tab,"resource");
        public EnergyInjectorRecipes(){
            RecipeEnergyInjectorManagerImpl.INSTANCE.addRecipe(
                    new ItemStack(itemResource,1,4)
                    ,new ItemStack(new ItemEnergyDust())
                    ,new ItemStack(Blocks.DIRT),24576000,
                    0.5);
            RecipeEnergyInjectorManagerImpl.INSTANCE.addRecipe(
                    new ItemStack(Items.IRON_INGOT),new ItemStack(Items.ENDER_PEARL),
                    new ItemStack(Blocks.DIRT),65536,0.7);

        }
    }
}
