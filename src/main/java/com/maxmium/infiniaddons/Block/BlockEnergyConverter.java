package com.maxmium.infiniaddons.Block;

import com.maxmium.infiniaddons.creativetab.CreativeTabsLoader;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import static com.maxmium.infiniaddons.InfiniaddonsProps.HORIZONAL_FACING;

public class BlockEnergyConverter extends BlockContainer {
    public BlockEnergyConverter(){
        super(Material.IRON);
        this.setUnlocalizedName("energyConverter");
        this.setRegistryName("energy_converter");
        setHardness(20F);
        setResistance(100F);
        setSoundType(SoundType.STONE);
        setCreativeTab(CreativeTabsLoader.tabInfiniaddons);
        setDefaultState(this.blockState.getBaseState().withProperty(HORIZONAL_FACING, EnumFacing.NORTH));
    }
    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return null;
    }
}
