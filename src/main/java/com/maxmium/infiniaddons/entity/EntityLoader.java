package com.maxmium.infiniaddons.entity;

import com.maxmium.infiniaddons.client.entity.EntityRenderFactory;
import com.maxmium.infiniaddons.client.entity.render.RenderElectronicBall;
import com.maxmium.infiniaddons.infiniaddons;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDesert;
import net.minecraft.world.biome.BiomeRiver;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityLoader {
    private static int nextID=0;
    public EntityLoader(){
        ForgeRegistries.ENTITIES.register(EntityEntryBuilder.create()
                .entity(EntityDiggerZombie.class)
                .name("DiggerZombie")
                .id(new ResourceLocation(infiniaddons.MODID,"digger_zombie"),1)
                .tracker(64,3,true)
                .spawn(EnumCreatureType.MONSTER,100,4,4)
                .build());
        ForgeRegistries.ENTITIES.register(EntityEntryBuilder.create()
                .id(new ResourceLocation(infiniaddons.MODID,"electronic_ball"),1)
                .entity(EntityElectronicBall.class)
                .name("Electronic Ball")
                .tracker(64,3,true)
                .build());

    }
    @SideOnly(Side.CLIENT)
    public static void registerRenders()
    {
        registerEntityRender(EntityElectronicBall.class, RenderElectronicBall.class);
    }
    @SideOnly(Side.CLIENT)
    private static <T extends Entity> void registerEntityRender(Class<T> entityClass, Class<? extends Render<T>> render)
    {
        RenderingRegistry.registerEntityRenderingHandler(entityClass, new EntityRenderFactory<T>(render));
    }
}
