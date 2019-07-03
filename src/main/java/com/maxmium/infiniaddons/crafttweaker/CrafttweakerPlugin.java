package com.maxmium.infiniaddons.crafttweaker;

import com.maxmium.infiniaddons.RecipeEnergyInjectorManagerImpl;
import com.maxmium.infiniaddons.common.ConfigLoader;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.oredict.IOreDictEntry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;

public class CrafttweakerPlugin {
    public static void init(){
        CraftTweakerAPI.registerClass(ZenEnergyInjector.class);
    }
    private static ItemStack toStack(IIngredient ing)
    {
        Object internal = ing.getInternal();
        if (!(internal instanceof ItemStack))
        {
            ConfigLoader.logger().error("Not a valid item stack: " + ing);
            return ItemStack.EMPTY;
        }

        return (ItemStack) internal;
    }
    @ZenClass("maxmium.infiniaddons.EnergyInject")
    public static class ZenEnergyInjector{

        @ZenMethod
        public static void addRecipe(@Nullable IIngredient input, @Nullable IItemStack output1, @Nullable IItemStack output2, int energy, double chance){
            if(input==null||output1==null||output2==null){
                ConfigLoader.logger().error("Required parameters missing for recipe");
                return;
            }
            if(input instanceof IOreDictEntry){
                RecipeEnergyInjectorManagerImpl.INSTANCE.addRecipe(((IOreDictEntry) input).getName(), CraftTweakerMC.getItemStack(output1),CraftTweakerMC.getItemStack(output2),energy,chance);
            }
            else RecipeEnergyInjectorManagerImpl.INSTANCE.addRecipe(toStack(input), CraftTweakerMC.getItemStack(output1),CraftTweakerMC.getItemStack(output2),energy,chance);

        }
        @ZenMethod
        public static void removeRecipe(@Nullable IIngredient output){
            List<RecipeEnergyInjectorManagerImpl.Recipe> toRemove=RecipeEnergyInjectorManagerImpl.INSTANCE.getRecipeList().
                    stream().filter(recipe -> output.matches(CraftTweakerMC.getIItemStack(recipe.getOutput())))
                    .collect(Collectors.toList());
            RecipeEnergyInjectorManagerImpl.INSTANCE.getRecipeList().removeAll(toRemove);
        }


    }
}
