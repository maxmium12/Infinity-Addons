package com.maxmium.infiniaddons.Block;

import codechicken.lib.util.RotationUtils;
import com.maxmium.infiniaddons.Tile.TileCobblestoneGenerator;
import com.maxmium.infiniaddons.Tile.TileEnergyInjector;
import com.maxmium.infiniaddons.Tile.TileMachineBase;
import com.maxmium.infiniaddons.creativetab.CreativeTabsLoader;
import com.maxmium.infiniaddons.gui.GuiHandler;
import com.maxmium.infiniaddons.infiniaddons;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import static com.maxmium.infiniaddons.InfiniaddonsProps.ACTIVE;
import static com.maxmium.infiniaddons.InfiniaddonsProps.HORIZONAL_FACING;

public class BlockCobblestoneGenerator extends BlockContainer {
    public BlockCobblestoneGenerator(){
        super(Material.IRON);
        setHardness(20F);
        setResistance(100F);
        setSoundType(SoundType.STONE);
        setCreativeTab(CreativeTabsLoader.tabInfiniaddons);
        setUnlocalizedName("cobblestoneGenerator");
        setRegistryName("cobblestone_generator");
    }
    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(!worldIn.isRemote){
            if (playerIn.isSneaking()){
                return true;
            }
            playerIn.openGui(infiniaddons.instance, GuiHandler.GUI_COBBLE,worldIn,pos.getX(),pos.getY(),pos.getZ());
        }
        return true;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileCobblestoneGenerator();
    }
}
