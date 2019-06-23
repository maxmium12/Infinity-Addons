package com.maxmium.infiniaddons;

import com.maxmium.infiniaddons.common.CommonProxy;
import com.maxmium.infiniaddons.common.ConfigLoader;
import com.maxmium.infiniaddons.crafting.RecipeHandler;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;


@Mod(modid = infiniaddons.MODID, name = infiniaddons.NAME, version = infiniaddons.VERSION, acceptedMinecraftVersions = "1.12.2",dependencies = "required-after:avaritia@[3.3.0]")
public class infiniaddons {
    public static final String MODID="infiniaddons";
    public static final String NAME="Infinity Addons";
    public static final String VERSION="1.0.0";
    @Mod.Instance(infiniaddons.MODID)
    public static infiniaddons instance;
@SidedProxy(clientSide = "com.maxmium.infiniaddons.client.ClientProxy",serverSide = "com.maxmium.infiniaddons.common.CommonProxy")
public static CommonProxy proxy;

@Mod.EventHandler

    public void preInit(FMLPreInitializationEvent event)
    {
    proxy.preInit();
        RecipeHandler.init();
        new ConfigLoader(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

    }
    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event){
        proxy.serverStarting(event);
    }
}
