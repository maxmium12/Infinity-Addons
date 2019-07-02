package com.maxmium.infiniaddons.gui.container;

import com.maxmium.infiniaddons.Tile.TileCobblestoneGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class CobblestoneGeneratorContainer extends Container {
    private IItemHandler outputinv;
    private TileCobblestoneGenerator tileEntity;
    public CobblestoneGeneratorContainer(EntityPlayer player, TileEntity tileEntity){
        super();
        this.outputinv=tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
        for(int i=0;i<2;++i){
            for(int j=0;j<8;++j){
                this.addSlotToContainer(new SlotItemHandler(outputinv,i*8+j,12+j*18,25+i*18){
                    @Override
                    public boolean isItemValid(ItemStack stack){
                        return false;
                    }
                });
            }
        }
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
        this.tileEntity=(TileCobblestoneGenerator) tileEntity;
    }
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        Slot slot = inventorySlots.get(index);

        if (slot == null || !slot.getHasStack())
        {
            return ItemStack.EMPTY;
        }

        ItemStack newStack = slot.getStack(), oldStack = newStack.copy();

        boolean isMerged = false;

        if (index <=15)
        {
            isMerged = mergeItemStack(newStack, 16, 52, true);
        }


        if (!isMerged)
        {
            return ItemStack.EMPTY;
        }

        if (newStack.getMaxStackSize() == 0)
        {
            slot.putStack(ItemStack.EMPTY);
        }
        else
        {
            slot.onSlotChanged();
        }

        return oldStack;

    }
    public boolean canInteractWith(EntityPlayer playerIn) {
        return playerIn.getDistanceSq(this.tileEntity.getPos()) <= 64;
    }
}
