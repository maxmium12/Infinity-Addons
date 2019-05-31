package com.maxmium.infiniaddons.gui.container;


import com.maxmium.infiniaddons.RecipeEnergyInjectorManagerImpl;
import com.maxmium.infiniaddons.Tile.TileEnergyInjector;
import morph.avaritia.init.ModItems;
import morph.avaritia.recipe.AvaritiaRecipeManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import java.util.LinkedList;
import java.util.List;

public class EnergyInjectorContainer extends Container {
    private IItemHandler inputItems;
    private IItemHandler outputItems;
    private IItemHandler trash;
    protected int ProduceTime=0;

    protected TileEnergyInjector tileEntity;
    private List recipes=RecipeEnergyInjectorManagerImpl.INSTANCE.getRecipeList();

    protected int activeTime = 0;
    public EnergyInjectorContainer(EntityPlayer player, TileEntity tileEntity){
        super();
        this.inputItems=tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
        this.trash=tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,EnumFacing.DOWN);
        this.outputItems=tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,EnumFacing.NORTH);
        this.addSlotToContainer(new SlotItemHandler(this.inputItems, 0, 31, 26));

        this.addSlotToContainer(new SlotItemHandler(this.trash, 0, 128, 25){
            @Override
            public  boolean isItemValid(ItemStack stack){
                return false;
            }
        });
        this.addSlotToContainer(new SlotItemHandler(this.outputItems,0,105,25){
            @Override
            public  boolean isItemValid(ItemStack stack){
                return false;
            }
        });
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 74 + i * 18));
            }
        }

        for (int i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 132));
        }
        this.tileEntity=(TileEnergyInjector)tileEntity;
    }
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotNumber) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = (Slot) this.inventorySlots.get(slotNumber);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (slotNumber == 2) {
                if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (slotNumber != 0) {
                if (AvaritiaRecipeManager.getCompressorRecipeFromInput(itemstack1) != null) {
                    if (!this.mergeItemStack(itemstack1, 0, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (slotNumber >= 3 && slotNumber < 30) {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (slotNumber >= 30 && slotNumber < 39 && !this.mergeItemStack(itemstack1, 2, 29, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
        }
        return itemstack;
    }
    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int id, int data)
    {
        super.updateProgressBar(id, data);

        switch (id)
        {
            case 0:
                this.ProduceTime = data;
                break;
            default:
                break;
        }
    }
    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        this.ProduceTime = tileEntity.getProductTick();

        for (IContainerListener i : this.listeners)
        {
            i.sendWindowProperty(this,0,this.ProduceTime);
        }
    }
    public int getProduceTime()
    {
        return this.ProduceTime;
    }
    public int getTotalProduceTime()
    {
        return this.tileEntity.getTotalProductTick();
    }


        @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }
}
