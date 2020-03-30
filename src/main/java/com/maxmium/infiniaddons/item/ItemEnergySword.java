package com.maxmium.infiniaddons.item;

import com.maxmium.infiniaddons.creativetab.CreativeTabsLoader;
import com.maxmium.infiniaddons.entity.EntityElectronicBall;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public class ItemEnergySword extends ItemSword {
    public static final ToolMaterial ENERGY = EnumHelper.addToolMaterial("ENERGY", 7, 15360, 64F, 64F, 10);
    long historytime;
    public ItemEnergySword() {
        super(ENERGY);
        this.setUnlocalizedName("energySword");
        this.setCreativeTab(CreativeTabsLoader.tabInfiniaddons);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack item=playerIn.getHeldItem(EnumHand.MAIN_HAND);
        item.damageItem(30,playerIn);
        if(worldIn.getTotalWorldTime()-historytime>=20) {
            EntityElectronicBall entity=new EntityElectronicBall(worldIn,playerIn);
            worldIn.playSound(playerIn, new BlockPos(playerIn), new SoundEvent(new ResourceLocation("infiniaddons:infiniaddons.electric")), SoundCategory.PLAYERS, 1.0F, 1.0F);
            entity.shoot(playerIn,playerIn.rotationPitch,playerIn.rotationYaw,0,1.5F,1);
            worldIn.spawnEntity(entity);
            historytime=worldIn.getTotalWorldTime();
            return new ActionResult(EnumActionResult.SUCCESS, item);
        }
        playerIn.sendMessage(new TextComponentTranslation("item.energySword.fail"));
        return new ActionResult(EnumActionResult.FAIL,item);

    }
    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker){
        target.attackEntityFrom(new DamageSource("electronic").setDamageIsAbsolute().setDamageBypassesArmor(),getAttackDamage());
        stack.damageItem(1,attacker);
        return true;
    }
    @Override
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 60;
    }

}

