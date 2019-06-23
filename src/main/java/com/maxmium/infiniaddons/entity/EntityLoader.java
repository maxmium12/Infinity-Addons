package com.maxmium.infiniaddons.entity;

import com.maxmium.infiniaddons.infiniaddons;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDesert;
import net.minecraft.world.biome.BiomeRiver;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

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

    }
}
