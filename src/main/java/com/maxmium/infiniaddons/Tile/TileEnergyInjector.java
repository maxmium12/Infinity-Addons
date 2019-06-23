package com.maxmium.infiniaddons.Tile;

import codechicken.lib.util.ItemUtils;
import com.maxmium.infiniaddons.ModItems;
import com.maxmium.infiniaddons.RecipeEnergyInjectorManagerImpl;
import com.maxmium.infiniaddons.item.ItemEnergyDust;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.api.energy.tile.IEnergySink;
import net.minecraft.block.BlockDirt;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraftforge.items.wrapper.SidedInvWrapper;

import java.util.ArrayList;
import java.util.Map;

public class TileEnergyInjector extends TileMachineBase {
    public static int ProductTick=0;
    private int progress;
    protected double recievedEnergyUnit=0;
    protected int Runtime=0;
    private ItemStack itemEnergyDust=new ItemStack(ModItems.itemEnergyDust);

    protected ItemStackHandler InputInventory=new ItemStackHandler();
    protected ItemStackHandler OutputInventory=new ItemStackHandler();
    protected ItemStackHandler Trash=new ItemStackHandler();
    RecipeEnergyInjectorManagerImpl recipeEnergyInjectorManager=new RecipeEnergyInjectorManagerImpl();
    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        if (CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.equals(capability))
        {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        if (CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.equals(capability))
        {
            if(facing==EnumFacing.UP){
                        return (T)InputInventory;
                    }
        else if(facing==EnumFacing.DOWN){
            return (T)Trash;
                    }
        else {
            return (T)OutputInventory;
        }
        }
        return super.getCapability(capability, facing);

    }
    @Override
    public void readFromNBT(NBTTagCompound compound){
        super.readFromNBT(compound);
        this.InputInventory.deserializeNBT(compound.getCompoundTag("InputInventory"));
        this.OutputInventory.deserializeNBT(compound.getCompoundTag("OutputInventory"));
        this.Runtime = compound.getInteger("Runtime");
    }
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound){
        super.writeToNBT(compound);
        compound.setTag("InputInventory",this.InputInventory.serializeNBT());
        compound.setTag("OutPutInventory",this.InputInventory.serializeNBT());
        compound.setInteger("Runtime",this.Runtime);
        return super.writeToNBT(compound);
    }

    @Override
    protected void doWork() {
        ItemStack itemstack1=InputInventory.extractItem(0,1,true);
        ItemStack itemstack=itemstack1.copy();
        Map<String,ItemStack> output=RecipeEnergyInjectorManagerImpl.INSTANCE.getResult(itemstack);
        double chance=RecipeEnergyInjectorManagerImpl.INSTANCE.getChance(itemstack);
        ItemStack output1=output.get("output1");
        ItemStack trashitem=output.get("output2");
        if(recipeEnergyInjectorManager.isRightItem(itemstack)) {
           if(Runtime>=getTotalProductTick()) {
               double trash = Math.random();
               if (trash <=chance) {
                   OutputInventory.insertItem(0,output1, false);
               } else {
                   Trash.insertItem(0, trashitem, true);
               }
               Runtime = 0;
               markDirty();

           }
            Runtime++;
        }
        else {
            Runtime=0;
            markDirty();
        }
    }

    @Override
    protected void onWorkStopped() {
        progress=0;
    }

    @Override
    protected boolean canWork() {
        ItemStack itemStackinput=InputInventory.extractItem(0,1,true);
        ItemStack itemStackoutput=OutputInventory.extractItem(0,1,true);
        ItemStack trash=Trash.extractItem(0,1,true);
        return !itemStackinput.isEmpty()&&itemStackoutput.getCount()<64&&trash.getCount()<64;
    }

    public double getRequiredEnergyPerTick()
    {
        return 8192;
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
        return 5;
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
    public int getProductTick(){
        return Runtime;
    }
    public int getTotalProductTick() {
        return (int)RecipeEnergyInjectorManagerImpl.INSTANCE.getenergy(InputInventory.extractItem(0,1,true))/8192;
    }
}

