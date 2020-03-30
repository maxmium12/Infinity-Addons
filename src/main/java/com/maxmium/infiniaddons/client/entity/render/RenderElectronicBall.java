package com.maxmium.infiniaddons.client.entity.render;

import com.maxmium.infiniaddons.entity.EntityElectronicBall;
import com.maxmium.infiniaddons.item.ItemElectronicBall;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RenderElectronicBall extends RenderSnowball<EntityElectronicBall> {
    public RenderElectronicBall(RenderManager renderManagerIn) {
        super(renderManagerIn, new ItemElectronicBall(), Minecraft.getMinecraft().getRenderItem());

    }

}
