package com.maxmium.infiniaddons.entity.ai;

import com.maxmium.infiniaddons.entity.EntityDiggerZombie;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

import java.util.Iterator;
import java.util.List;

public class EntityDigthingsAi extends EntityAIBase {
    private final EntityDiggerZombie entity;
    public EntityDigthingsAi(EntityDiggerZombie entity) {
        this.entity =entity;
    }

    @Override
    public boolean shouldExecute() {
        BlockPos entityPos=new BlockPos(entity);
        EnumFacing entityface=entity.getHorizontalFacing();
        if(!entity.world.isAirBlock(entityPos.offset(entityface))||!entity.world.isAirBlock(entityPos)){
            return true;
        }
        return false;
    }
    @Override
    public void updateTask(){
        BlockPos entityPos=new BlockPos(entity);
        List entitylist=entity.world.getEntitiesWithinAABB(EntityPlayer.class,entity.getEntityBoundingBox().grow(32D,4D,32D));
        for(Iterator iterator=entitylist.iterator();iterator.hasNext();){
            EntityLiving entity=(EntityLiving)iterator.next();


        }
    }
}
