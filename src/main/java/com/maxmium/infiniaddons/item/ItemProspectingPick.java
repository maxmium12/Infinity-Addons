package com.maxmium.infiniaddons.item;

import blusunrize.immersiveengineering.api.tool.ExcavatorHandler;
import com.feed_the_beast.ftblib.lib.math.ChunkDimPos;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Set;

public class ItemProspectingPick extends Item {
    private int damage;
    public ItemProspectingPick(int damage,String unlocalname,String registryname){
        super();
        this.setMaxDamage(damage);
        this.setUnlocalizedName(unlocalname);
        this.setRegistryName(registryname);
        this.setCreativeTab(CreativeTabs.TOOLS);

    }
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            if (!player.isSneaking()) {
                if (worldIn.getBlockState(pos).getBlock().equals(Blocks.BEDROCK)) {
                    ChunkDimPos chunk = new ChunkDimPos(pos, worldIn.provider.getDimension());
                    if(ExcavatorHandler.getMineralWorldInfo(worldIn, chunk.posX, chunk.posZ).mineralOverride.name==null){
                        player.sendMessage(new TextComponentTranslation("infiniaddons.prospect.none"));
                    }
                    player.sendMessage(new TextComponentTranslation("infiniaddons.prospect", ExcavatorHandler.getMineralWorldInfo(worldIn, chunk.posX, chunk.posZ).mineralOverride.name));
                    ItemStack stack = player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
                    stack.damageItem(1, player);
                    return EnumActionResult.SUCCESS;
                }
            }
            return EnumActionResult.FAIL;
        }
        return EnumActionResult.PASS;
    }
     public static class copperProspectingPick extends ItemProspectingPick{
         public copperProspectingPick() {
             super(70,"copperProspectingPick","copper_prospecting_pick");
         }
     }
    public static class ironProspectingPick extends ItemProspectingPick{
        public ironProspectingPick() {
            super(140,"ironProspectingPick","iron_prospecting_pick");
        }
    }
    public static class diamondProspectingPick extends ItemProspectingPick{
        public diamondProspectingPick() {
            super(1400,"diamondProspectingPick","diamond_prospecting_pick");
        }
    }
}


