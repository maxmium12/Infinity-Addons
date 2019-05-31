package com.maxmium.infiniaddons;

import com.maxmium.infiniaddons.api.IModelRegister;
import com.maxmium.infiniaddons.item.ItemEnergyDust;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.function.Consumer;

import static com.maxmium.infiniaddons.infiniaddons.proxy;

public class ModItems {
    public static ItemEnergyDust itemEnergyDust;
    public static void init(){
        itemEnergyDust=new ItemEnergyDust();
        itemEnergyDust.setRegistryName("energy_dust");
        registerItem(itemEnergyDust);

    }
    public static <V extends Item> V registerItem(V item) {
        registerImpl(item, ForgeRegistries.ITEMS::register);
        return item;
    }

    public static <V extends IForgeRegistryEntry<V>> V registerImpl(V registryObject, Consumer<V> registerCallback) {
        registerCallback.accept(registryObject);

        if (registryObject instanceof IModelRegister) {
            proxy.addModelRegister((IModelRegister) registryObject);
        }

        return registryObject;
    }
    }



