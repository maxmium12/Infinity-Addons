package com.maxmium.infiniaddons;

import codechicken.lib.gui.SimpleCreativeTab;
import com.feed_the_beast.ftblib.lib.data.ForgePlayer;
import com.feed_the_beast.ftblib.lib.data.Universe;
import com.maxmium.infiniaddons.common.CommonProxy;
import com.maxmium.infiniaddons.common.ConfigLoader;
import com.maxmium.infiniaddons.crafting.RecipeHandler;
import com.maxmium.infiniaddons.creativetab.CreativeTabsLoader;
import de.ellpeck.actuallyadditions.mod.creative.CreativeTab;
import net.minecraft.crash.CrashReport;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.util.ReportedException;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.util.UUID;


@Mod(modid = infiniaddons.MODID, name = infiniaddons.NAME, version = infiniaddons.VERSION, acceptedMinecraftVersions = "1.12.2")
public class infiniaddons {
    public static final String MODID="infiniaddons";
    public static final String NAME="Infinity Addons";
    public static final String VERSION="1.0.3";
    @Mod.Instance(infiniaddons.MODID)
    public static infiniaddons instance;
@SidedProxy(clientSide = "com.maxmium.infiniaddons.client.ClientProxy",serverSide = "com.maxmium.infiniaddons.common.CommonProxy")
public static CommonProxy proxy;
@Mod.EventHandler

    public void preInit(FMLPreInitializationEvent event)
    {
    proxy.preInit(event);
        RecipeHandler.init();
        new ConfigLoader(event);
        new CreativeTabsLoader(event);
        if(Loader.isModLoaded("crafttweaker")){
            try {
                Class.forName("com.maxmium.infiniaddons.crafttweaker.CrafttweakerPlugin").getMethod("init").invoke(null);
            }
            catch (Exception e)
            {
                throw new ReportedException(new CrashReport("Error initializing minetweaker integration", e));
            }
        }
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
