package com.maxmium.infiniaddons;

import com.maxmium.infiniaddons.Block.BlockAreaBlock;
import com.maxmium.infiniaddons.Block.BlockCobblestoneGenerator;
import com.maxmium.infiniaddons.Block.BlockEnergyInjector;
import com.maxmium.infiniaddons.Tile.TileAreaBlock;
import com.maxmium.infiniaddons.Tile.TileCobblestoneGenerator;
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
import net.minecraftforge.fml.common.Loader;
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
    public static BlockCobblestoneGenerator blockCobblestoneGenerator=new BlockCobblestoneGenerator();
    public static BlockAreaBlock blockAreaBlock=new BlockAreaBlock();

    public static void init() {
        if(Loader.isModLoaded("ic2")) {
            blockEnergyInjector = registerBlock(new BlockEnergyInjector());
            registerItemBlock(blockEnergyInjector);
            GameRegistry.registerTileEntity(TileEnergyInjector.class, "energy_injector");
        }
        blockCobblestoneGenerator=registerBlock(new BlockCobblestoneGenerator());
        registerItemBlock(blockCobblestoneGenerator);
        GameRegistry.registerTileEntity(TileCobblestoneGenerator.class,"cobblestone_generator");
        registerBlock(blockAreaBlock);
        registerItemBlock(blockAreaBlock);
        GameRegistry.registerTileEntity(TileAreaBlock.class,"area_block");

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
    @SideOnly(Side.CLIENT)
    public static void registerRenders()
    {
        registerStateMapper(blockEnergyInjector,new StateMap.Builder().withSuffix("_injector").build());
        registerRender(blockEnergyInjector);
        registerRender(blockCobblestoneGenerator,0,"energy_injector");
    }

    public static ItemBlock registerItemBlock(Block block) {
        ItemBlock itemBlock = new ItemBlock(block);
        registerItem(itemBlock.setRegistryName(block.getRegistryName()));
        return itemBlock;
    }
    @SideOnly(Side.CLIENT)
    private static void registerRender(Block block)
    {
        ModelResourceLocation model = new ModelResourceLocation(block.getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, model);
    }
    @SideOnly(Side.CLIENT)
    private static void registerStateMapper(Block block, IStateMapper mapper)
    {
        ModelLoader.setCustomStateMapper(block, mapper);
    }
    @SideOnly(Side.CLIENT)
    private static void registerRender(Block block, int meta, String name)
    {
        ModelResourceLocation model = new ModelResourceLocation(name, "inventory");
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, model);
    }
}



