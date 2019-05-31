package com.maxmium.infiniaddons;

import com.maxmium.infiniaddons.api.RecipeEnergyInjectorManager;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.core.pattern.NotANumber;

import java.util.*;

public class RecipeEnergyInjectorManagerImpl extends RecipeEnergyInjectorManager {
    public static final RecipeEnergyInjectorManagerImpl INSTANCE = new RecipeEnergyInjectorManagerImpl();
    private final List<Recipe> recipes = new ArrayList<Recipe>();

    @Override
    public void addRecipe(ItemStack input, ItemStack output1, ItemStack output2, int energy, double chance) {
        this.recipes.add(new Recipe(input, output1, output2, energy, chance));
    }

    @Override
    public Map<String, ItemStack> getResult(ItemStack input) {
        for (Recipe recipe : recipes) {
            try {
                if (recipe.input == input) {
                    return recipesMap(recipe.output1, recipe.output2);
                }
            } catch (NullPointerException | IllegalArgumentException e) {

            }
        }
        return recipesMap(ItemStack.EMPTY, ItemStack.EMPTY);
    }

    public List<Recipe> getRecipeList() {
        return recipes;
    }

    public ArrayList<ItemStack> getInputList() {
        ArrayList<ItemStack> a = new ArrayList<ItemStack>();
        for (Recipe recipe : recipes) {
            a.add(recipe.input);
        }
        return a;
    }

    private static Map<String, ItemStack> recipesMap(ItemStack output1, ItemStack output2) {
        Map<String, ItemStack> result = new HashMap<String, ItemStack>();
        result.put("output1", output1);
        result.put("output2", output2);
        return result;
    }

    public int getenergy(ItemStack input) {
        for (Recipe recipe : recipes) {
            try {
                if (recipe.input == input) {
                    return recipe.energy;
                }
            } catch (NullPointerException | IllegalArgumentException e) {

            }
        }
        return 1;
    }

    public double getChance(ItemStack input) {
        for (Recipe recipe : recipes) {
            try {
                if (recipe.input == input) {
                    return recipe.chance;
                }
            } catch (NullPointerException | IllegalArgumentException e) {

            }
        }
        return 0;

    }

    public String toString() {

        for (Recipe recipe : recipes) {
            try {
                return String.format(Locale.US, "Recipes:input%s,output:%s,trash:%s,energy:%s,chance:%s", recipe.input, recipe.output1, recipe.output2, recipe.energy, recipe.chance);
            }
            catch (NullPointerException|IllegalArgumentException e){
            }
        }
        return String.format(Locale.US,"No recipes");
    }
    //Just for debug
    public static class Recipe{
    private   ItemStack input=ItemStack.EMPTY;
    private  ItemStack output1=ItemStack.EMPTY;
    private  ItemStack output2=ItemStack.EMPTY;
    private  int energy;
    private  double chance;
    private Recipe(ItemStack input,ItemStack output1,ItemStack output2,int energy,double chance){
        this.input=input;
        this.output1=output1;
        this.output2=output2;
        this.energy=energy;
        this.chance=chance;

    }
    }

}

