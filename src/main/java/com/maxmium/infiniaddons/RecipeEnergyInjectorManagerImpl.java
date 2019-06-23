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
    public Recipe findRecipe(ItemStack in){
        for (Recipe recipe : recipes) {
            try {
                if (recipe.input == in) {
                    return recipe;
                }
            } catch (NullPointerException | IllegalArgumentException e) {

            }
        }
        return new Recipe(ItemStack.EMPTY,ItemStack.EMPTY,ItemStack.EMPTY,1,0);
    }

    @Override
    public Map<String, ItemStack> getResult(ItemStack input) {
       Recipe out=findRecipe(input);
        return recipesMap(out.output1,out.output2);
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
       return findRecipe(input).energy;
    }

    public double getChance(ItemStack input) {
        return findRecipe(input).chance;

    }
    //Just for debug
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
    public Boolean isRightItem(ItemStack input){
        if(findRecipe(input).input==input){
            return true;
        }
        return false;
    }
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

