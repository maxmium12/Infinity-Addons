package com.maxmium.infiniaddons;

import com.maxmium.infiniaddons.api.IModelRegister;
import com.maxmium.infiniaddons.item.*;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.function.Consumer;

import static com.maxmium.infiniaddons.infiniaddons.proxy;

public class ModItems {
    public static ItemEnergyDust itemEnergyDust;
    public static ItemEnergySword itemEnergySword;
    public static ItemElectronicBall itemElectronicBall;
    public static ItemEndestSword itemEndestSword;
    public static ItemProspectingPick.copperProspectingPick itemCopperProspectingPick=new ItemProspectingPick.copperProspectingPick();
    public static ItemProspectingPick.ironProspectingPick itemIronProspectingPick=new ItemProspectingPick.ironProspectingPick();
    public static ItemProspectingPick.diamondProspectingPick itemDiamondProspectingPick=new ItemProspectingPick.diamondProspectingPick();
    public static void init() {
        itemEnergyDust = new ItemEnergyDust();
        itemEnergyDust.setRegistryName("energy_dust");
        registerItem(itemEnergyDust);
        itemEnergySword=new ItemEnergySword();
        itemEnergySword.setRegistryName("energy_sword");
        registerItem(itemEnergySword);
        itemElectronicBall=new ItemElectronicBall();
        itemElectronicBall.setRegistryName("electronic_ball");
        registerItem(itemElectronicBall);
        itemEndestSword=new ItemEndestSword();
        registerItem(itemEndestSword);
        registerItem(itemCopperProspectingPick);
        registerItem(itemIronProspectingPick);
        registerItem(itemDiamondProspectingPick);


    }

    public static <V extends Item> V registerItem(V item) {
        registerImpl(item, ForgeRegistries.ITEMS::register);
        return item;
    }

    public static <V extends IForgeRegistryEntry<V>> V registerImpl(V registryObject, Consumer<V> registerCallback) {
        registerCallback.accept(registryObject);
        return registryObject;
    }
    @SideOnly(Side.CLIENT)
    public static void registerRenders()
    {
        registerRender(itemEnergyDust);
        registerRender(itemEnergySword);
        registerRender(itemElectronicBall);
        registerRender(itemCopperProspectingPick);
        registerRender(itemIronProspectingPick);
        registerRender(itemDiamondProspectingPick);
    }

    @SideOnly(Side.CLIENT)
    private static void registerRender(Item item) {
        ModelResourceLocation model = new ModelResourceLocation(item.getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(item, 0, model);
    }
}





