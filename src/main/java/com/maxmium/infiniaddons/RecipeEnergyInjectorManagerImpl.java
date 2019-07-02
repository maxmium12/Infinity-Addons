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
        for (int i=0;i<=recipes.size();i++) {
            try {
                if (recipes.get(i).input.isItemEqual(in)) {
                    return recipes.get(i);
                }
            } catch (NullPointerException | IllegalArgumentException | IndexOutOfBoundsException e) {
                //skip recipe
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
       return findRecipe(input).output1!=ItemStack.EMPTY;
    }
    public static class Recipe{
    private   ItemStack input;
    private  ItemStack output1;
    private  ItemStack output2;
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

