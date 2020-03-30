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
    protected float getGravityVelocity()
    {
        return 0F;
    }
    @Override
    protected void onImpact(RayTraceResult result) {
        if(!this.world.isRemote){
            double x=result.hitVec.x;
            double y=result.hitVec.y;
            double z=result.hitVec.z;
            List<EntityLiving> entitylist=world.getEntitiesWithinAABB(EntityLiving.class,new AxisAlignedBB(x+3d,y+3d,z+3d,x-3d,y-3d,z-3d));
            for(EntityLiving entity:entitylist) {
                entity.attackEntityFrom(new DamageSource("electric").setDamageBypassesArmor().setDamageIsAbsolute(), 130f);
        }
    }
}
}
