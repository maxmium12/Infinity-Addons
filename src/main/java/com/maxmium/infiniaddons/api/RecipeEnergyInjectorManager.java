package com.maxmium.infiniaddons.api;

import net.minecraft.item.ItemStack;

import java.util.Map;

public abstract class RecipeEnergyInjectorManager {
    public abstract void addRecipe(ItemStack input,ItemStack output1,ItemStack output2,int energy,double chance);
    public abstract Map getResult(ItemStack input);
    public static final RecipeEnergyInjectorManager INSTANCE;

    static
    {
        try
        {
            Class<?> implClass = Class.forName("com.maxmium.infiniaddons.RecipeEnergyInjectorManagerImpl");
            INSTANCE = (RecipeEnergyInjectorManager) implClass.getDeclaredField("INSTANCE").get(null);
        }
        catch (Exception e)
        {
            throw new RuntimeException("Cannot find implementation", e);
        }
    }
}
