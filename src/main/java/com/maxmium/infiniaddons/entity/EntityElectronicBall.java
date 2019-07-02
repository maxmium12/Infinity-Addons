package com.maxmium.infiniaddons.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import java.util.List;

public class EntityElectronicBall extends EntityThrowable {
    public EntityElectronicBall(World worldln){
        super(worldln);
    }
    public EntityElectronicBall(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    public EntityElectronicBall(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }
    @Override
    protected void onImpact(RayTraceResult result) {
        if(!this.world.isRemote){

            try{
            List<EntityLiving> entitylist=world.getEntitiesWithinAABB(EntityLiving.class,new AxisAlignedBB(1.5D,1.5D,1.5D,-1.5D,-1.5D,-1.5D));
            for(int i=0;i<=entitylist.size();i++){
                entitylist.get(i).attackEntityFrom(new DamageSource("electric").setDamageBypassesArmor().setDamageIsAbsolute(),130F);
            }
            }
            catch (IndexOutOfBoundsException e){

            }
        }
    }
}
