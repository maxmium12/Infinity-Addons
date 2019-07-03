package com.maxmium.infiniaddons;

import com.maxmium.infiniaddons.api.RecipeEnergyInjectorManager;
import ic2.api.recipe.IEmptyFluidContainerRecipeManager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.logging.log4j.core.pattern.NotANumber;

import java.util.*;

public class RecipeEnergyInjectorManagerImpl extends RecipeEnergyInjectorManager {
    public static final RecipeEnergyInjectorManagerImpl INSTANCE = new RecipeEnergyInjectorManagerImpl();
    private final List<Recipe> recipes = new ArrayList();

    @Override
    public void addRecipe(ItemStack input, ItemStack output1, ItemStack output2, int energy, double chance) {
        this.recipes.add(new RecipeItem(input, output1, output2, energy, chance));
    }
    public void addRecipe(String input, ItemStack output1, ItemStack output2, int energy, double chance){
        this.recipes.add(new RecipeOreDict(input, output1, output2, energy, chance));
    }
    public Recipe findRecipe(ItemStack in){
        for (Recipe recipe:recipes) {
           if(recipe.isRight(in)){
               return recipe;
           }
        }
        return new RecipeItem(ItemStack.EMPTY,ItemStack.EMPTY,ItemStack.EMPTY,1,0);
    }
    public Recipe findRecipe(String  in){
        for (Recipe recipe:recipes) {
            for(ItemStack stack:OreDictionary.getOres(in)){
                if(recipe.isRight(stack)){
                    return recipe;
                }
            }
        }
        return new RecipeItem(ItemStack.EMPTY,ItemStack.EMPTY,ItemStack.EMPTY,1,0);
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
                return String.format(Locale.US, "Recipes:output:%s,trash:%s,energy:%s,chance:%s", recipe.output1, recipe.output2, recipe.energy, recipe.chance);
            }
            catch (NullPointerException|IllegalArgumentException e){
            }
        }
        return String.format(Locale.US,"No recipes");
    }
    public Boolean isRightItem(ItemStack input){
        return findRecipe(input).isRight(input);
    }
    public abstract static class Recipe{
    private  ItemStack output1;
    private  ItemStack output2;
    private  int energy;
    private  double chance;
    public Recipe(ItemStack output1,ItemStack output2,int energy,double chance){
        this.output1=output1;
        this.output2=output2;
        this.energy=energy;
        this.chance=chance;

    }
        public ItemStack getOutput()
        {
            return output1;
        }
    public abstract boolean isRight(ItemStack in);
    }
    public static class RecipeItem extends Recipe{
            public ItemStack input;
        public RecipeItem(ItemStack input, ItemStack output1, ItemStack output2, int energy, double chance){
            super(output1, output2, energy, chance);
            this.input = input;
        }
        @Override
        public boolean isRight(ItemStack in) {
            return OreDictionary.itemMatches(input,in,true);
        }
    }
    public static class RecipeOreDict extends Recipe{
        public String input;
        public RecipeOreDict(String input,ItemStack output1, ItemStack output2, int energy, double chance) {
            super(output1, output2, energy, chance);
            this.input=input;
        }

        @Override
        public boolean isRight(ItemStack in) {
            for(int i:OreDictionary.getOreIDs(in)){
                if(i==OreDictionary.getOreID(input)){
                    return true;
                }
            }
            return false;
        }
    }

}

