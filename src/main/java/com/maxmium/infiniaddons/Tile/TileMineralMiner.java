package com.maxmium.infiniaddons.Tile;

import blusunrize.immersiveengineering.api.tool.ExcavatorHandler;
import ic2.api.energy.tile.IEnergyEmitter;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.Random;

public abstract class TileMineralMiner extends TileMachineBase{
    private int Runtime=0;
    private ItemStackHandler OutputInventory;
    private Capability<IItemHandler> capability=CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        if (this.capability.equals(capability))
        {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        if (this.capability.equals(capability))
        {
            return (T)OutputInventory;
        }
        return super.getCapability(capability, facing);

    }
    @Override
    public void readFromNBT(NBTTagCompound compound){
        super.readFromNBT(compound);
        this.OutputInventory.deserializeNBT(compound.getCompoundTag("outputInventory"));
    }
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound){
        super.writeToNBT(compound);
        compound.setTag("outputInventory",this.OutputInventory.serializeNBT());
        return super.writeToNBT(compound);
    }
    @Override
    protected void doWork() {
        ExcavatorHandler.MineralMix mineral =ExcavatorHandler.getRandomMineral(world,pos.getX()>>4,pos.getZ()>>4);
        ItemStack ore=mineral.getRandomOre(new Random());
        if(Runtime>=maxRuntime()){
            OutputInventory.insertItem(0,ore,false);
            TileEntity te=world.getTileEntity(new BlockPos(pos.getX(),pos.getY()+1,pos.getZ()));
            if(world.getTileEntity(new BlockPos(pos.getX(),pos.getY()+1,pos.getZ()))==null){
                if(te.hasCapability(capability,EnumFacing.DOWN)){
                    boolean isinsert=false;
                    for(int i=0;i<=te.getCapability(capability,EnumFacing.DOWN).getSlots();i++){
                       if(te.getCapability(capability,EnumFacing.DOWN).getStackInSlot(i).getItem().equals(ore.getItem())||te.getCapability(capability,EnumFacing.DOWN).getStackInSlot(i).isEmpty()){
                           te.getCapability(capability,EnumFacing.DOWN).insertItem(i,ore,false);
                           isinsert=true;
                           break;
                       }
                    }
                    if(!isinsert){
                        InventoryHelper.spawnItemStack(world,pos.getX(),pos.getY()+1,pos.getZ(),ore);
                    }
                   }
                }
            }
            Runtime=0;
            markDirty();
        }

    @Override
    protected void onWorkStopped() {

    }

    @Override
    protected boolean canWork() {
        return true;
    }

    @Override
    public double getDemandedEnergy() {
        return 0;
    }

    @Override
    public int getSinkTier() {
        return 0;
    }

    @Override
    public double injectEnergy(EnumFacing enumFacing, double v, double v1) {
        return 0;
    }

    @Override
    public boolean acceptsEnergyFrom(IEnergyEmitter iEnergyEmitter, EnumFacing enumFacing) {
        return false;
    }

    public  abstract int maxRuntime();

}
