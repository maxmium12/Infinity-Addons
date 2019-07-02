package com.maxmium.infiniaddons.item;

import com.maxmium.infiniaddons.creativetab.CreativeTabsLoader;
import morph.avaritia.handler.AvaritiaEventHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.util.EnumHelper;


public class ItemEndestSword extends ItemSword {
    private static final ToolMaterial TOOL_MATERIAL = EnumHelper.addToolMaterial("TRUTH", 32, 9999, 9999.0F, -3.0F, 200);

    public ItemEndestSword() {
        super(TOOL_MATERIAL);
        setRegistryName("endest_sword");
        setUnlocalizedName("endestSword");
        setCreativeTab(CreativeTabsLoader.tabInfiniaddons);
    }
    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker){
        if(attacker.world.isRemote){
            return true;
        }
        else {
            if(target instanceof EntityPlayer) {
                InventoryPlayer playerinv=new InventoryPlayer((EntityPlayer)target);
                for (int i=0;i<=playerinv.armorInventory.size();i++){
                if(playerinv.armorInventory.get(i)!=ItemStack.EMPTY){
                    if(playerinv.getFirstEmptyStack()==-1){
                        playerinv.armorInventory.get(i).getItem().createEntity(target.world,target,playerinv.armorInventory.get(i));
                        playerinv.armorInventory.set(i,ItemStack.EMPTY);
                    }
                        else {
                            playerinv.setInventorySlotContents(playerinv.getFirstEmptyStack(),playerinv.armorInventory.get(i));
                        playerinv.armorInventory.set(i,ItemStack.EMPTY);
                        }
                    }
                }

                }
        }
        return true;
        }
        @Override
        public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity){
            if(!player.world.isRemote&&entity instanceof EntityLiving&&!entity.isDead){
                entity.attackEntityFrom(new DamageSource("truth").setDamageIsAbsolute().setDamageBypassesArmor().setDamageAllowedInCreativeMode(),(((EntityLiving) entity).getHealth()));
                if(!entity.isDead) {
                    entity.attackEntityFrom(new DamageSource("truth").setDamageBypassesArmor().setDamageIsAbsolute().setDamageAllowedInCreativeMode(), 2147483647F);
                }
                if(!entity.isDead){
                    ((EntityLiving) entity).onDeath(new DamageSource("truth").setDamageBypassesArmor().setDamageIsAbsolute());
                }
                return true;
            }
            return false;
    }
    }
