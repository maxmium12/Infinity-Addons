package com.maxmium.infiniaddons.Tile;

import ic2.api.energy.tile.IEnergyEmitter;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

;

public class TileCobblestoneGenerator extends TileMachineBase{
    protected double recievedEnergyUnit=0;
    protected ItemStackHandler outputInv=new ItemStackHandler(16);
    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if (CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.equals(capability)) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }
    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing){
        if (CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.equals(capability)) {
            if(facing!=null){
            return (T)outputInv;
        }
        }
        return super.getCapability(capability, facing);
        }
    @Override
    public void readFromNBT(NBTTagCompound compound){
        super.readFromNBT(compound);
        this.outputInv.deserializeNBT(compound.getCompoundTag("InputInventory"));
    }
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound){
        super.writeToNBT(compound);
        compound.setTag("outputInventory",this.outputInv.serializeNBT());
        return super.writeToNBT(compound);
    }

    @Override
    protected void doWork() {
        for(int i=0;i<=15;i++){
            outputInv.setStackInSlot(i,new ItemStack(Blocks.COBBLESTONE,64));
        }
    }

    @Override
    protected void onWorkStopped() {

    }

    @Override
    protected boolean canWork() {
        for(int i=0;i<=15;i++){
            if(outputInv.getStackInSlot(i).getCount()<=64){
                return true;
            }
        }
        return false;
    }

    public double getEnergyCapacity()
    {
        return 3276800;
    }
    @Override
    public double getDemandedEnergy() {
        return Math.max(0,this.getEnergyCapacity()-this.recievedEnergyUnit);
    }

    @Override
    public int getSinkTier() {
        return 3;
    }

    @Override
    public double injectEnergy(EnumFacing directionFrom, double amount, double voltage) {
        this.recievedEnergyUnit += amount;
        return 0;
    }

    @Override
    public boolean acceptsEnergyFrom(IEnergyEmitter emitter, EnumFacing side) {
        return true;
    }
}
