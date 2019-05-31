package com.maxmium.infiniaddons.Block;

import codechicken.lib.model.ModelRegistryHelper;
import codechicken.lib.model.bakery.CCBakeryModel;
import codechicken.lib.model.bakery.ModelBakery;
import codechicken.lib.util.RotationUtils;
import com.maxmium.infiniaddons.InfiniaddonsProps;
import com.maxmium.infiniaddons.Tile.TileEnergyInjector;
import com.maxmium.infiniaddons.Tile.TileMachineBase;
import com.maxmium.infiniaddons.api.IModelRegister;
import com.maxmium.infiniaddons.gui.GuiHandler;
import com.maxmium.infiniaddons.infiniaddons;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.omg.PortableInterceptor.ACTIVE;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Function;


import static com.maxmium.infiniaddons.InfiniaddonsProps.HORIZONAL_FACING;
import static com.maxmium.infiniaddons.InfiniaddonsProps.ACTIVE;

public class BlockEnergyInjector extends BlockContainer implements IModelRegister{
    public BlockEnergyInjector() {
        super(Material.IRON);
        setHardness(20F);
        setResistance(100F);
        setSoundType(SoundType.STONE);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        setDefaultState(this.blockState.getBaseState().withProperty(HORIZONAL_FACING, EnumFacing.NORTH).withProperty(ACTIVE, false));
        setUnlocalizedName("energyinjector");
        setRegistryName("energy_injector");
    }

    @Override
    protected BlockStateContainer createBlockState() {

        return new BlockStateContainer(this, HORIZONAL_FACING, ACTIVE);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase player, ItemStack stack) {
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof TileEnergyInjector) {
            TileEnergyInjector machine = (TileEnergyInjector) te;
            machine.setFacing(RotationUtils.getPlacedRotationHorizontal(player));
        }
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        TileEntity te = worldIn.getTileEntity(pos);
        if (te instanceof TileMachineBase) {
            TileMachineBase machineBase = (TileMachineBase) te;
            state = state.withProperty(HORIZONAL_FACING, machineBase.getFacing());
            state = state.withProperty(ACTIVE, machineBase.isActive());
        }
        return super.getActualState(state, worldIn, pos);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEnergyInjector();
    }


    @Override
    public void RegisterModels() {
        StateMap.Builder stateMap = new StateMap.Builder();
        ModelLoader.setCustomStateMapper(this, stateMap.build());
        ModelResourceLocation location = new ModelResourceLocation(getRegistryName(), "normal");
        ModelRegistryHelper.register(location, new CCBakeryModel());
    }
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
      if(!worldIn.isRemote){
          if (playerIn.isSneaking()){
              return true;
          }
          playerIn.openGui(infiniaddons.instance, GuiHandler.GUI_INJECTOR,worldIn,pos.getX(),pos.getY(),pos.getZ());
      }
      return true;
    }
    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state){
        TileEnergyInjector te=(TileEnergyInjector)worldIn.getTileEntity(pos);
        IItemHandler in=te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,EnumFacing.UP);
        IItemHandler out=te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,EnumFacing.NORTH);
        IItemHandler trash=te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,EnumFacing.DOWN);
        for(int i=in.getSlots()-1;i>=0;--i){
            if (in.getStackInSlot(i) != null)
            {
                Block.spawnAsEntity(worldIn, pos, in.getStackInSlot(i));
                ((IItemHandlerModifiable) in).setStackInSlot(i, ItemStack.EMPTY);
            }
        }
        for(int i=out.getSlots()-1;i>=0;--i){
            if (out.getStackInSlot(i) != null)
            {
                Block.spawnAsEntity(worldIn, pos, out.getStackInSlot(i));
                ((IItemHandlerModifiable) out).setStackInSlot(i, ItemStack.EMPTY);
            }
        }
        for(int i=trash.getSlots()-1;i>=0;--i){
            if (trash.getStackInSlot(i) != null)
            {
                Block.spawnAsEntity(worldIn, pos, trash.getStackInSlot(i));
                ((IItemHandlerModifiable) trash).setStackInSlot(i, ItemStack.EMPTY);
            }
        }
        super.breakBlock(worldIn, pos, state);
    }
}
