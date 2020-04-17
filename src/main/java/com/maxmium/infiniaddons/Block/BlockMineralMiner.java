package com.maxmium.infiniaddons.Block;

import com.maxmium.infiniaddons.Tile.TileMineralMinerMK1;
import com.maxmium.infiniaddons.Tile.TileMineralMinerMK2;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import javax.annotation.Nullable;

public class BlockMineralMiner extends BlockContainer {
    public BlockMineralMiner() {
        super(Material.IRON);
        setHardness(3.0F);
        setResistance(10F);
        setCreativeTab(CreativeTabs.MISC);
        setRegistryName("mineral_miner");
        setUnlocalizedName("mineralMiner");
    }
    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        switch (meta){
            case 0:
                return new TileMineralMinerMK1();
            case 1:
                return new TileMineralMinerMK2();
        }
        return null;
    }
}
