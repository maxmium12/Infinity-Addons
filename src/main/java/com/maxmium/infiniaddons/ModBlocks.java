package com.maxmium.infiniaddons;

import com.maxmium.infiniaddons.Block.BlockEnergyInjector;
import com.maxmium.infiniaddons.Tile.TileEnergyInjector;
import com.maxmium.infiniaddons.api.IModelRegister;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.GameData;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.function.Consumer;

import static com.maxmium.infiniaddons.infiniaddons.proxy;


public class ModBlocks{
    public static BlockEnergyInjector blockEnergyInjector=new BlockEnergyInjector();

    public static void init() {
        blockEnergyInjector=registerBlock(new BlockEnergyInjector());
        registerItemBlock(blockEnergyInjector);
        GameRegistry.registerTileEntity(TileEnergyInjector.class,"energy_injector");

    }

    public static <V extends Block> V registerBlock(V block) {
        registerImpl(block, ForgeRegistries.BLOCKS::register);
        return block;
    }

    public static <V extends Item> V registerItem(V item) {
        registerImpl(item, ForgeRegistries.ITEMS::register);
        return item;
    }


    public static <V extends IForgeRegistryEntry<V>> V registerImpl(V registryObject, Consumer<V> registerCallback) {
        registerCallback.accept(registryObject);
        return registryObject;
    }
    public static ItemBlock registerItemBlock(Block block) {
        ItemBlock itemBlock = new ItemBlock(block);
        registerItem(itemBlock.setRegistryName(block.getRegistryName()));
        return itemBlock;
    }

}

