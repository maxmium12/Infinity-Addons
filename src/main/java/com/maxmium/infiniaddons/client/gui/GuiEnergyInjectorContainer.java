package com.maxmium.infiniaddons.client.gui;

import com.maxmium.infiniaddons.gui.container.EnergyInjectorContainer;
import com.maxmium.infiniaddons.infiniaddons;
import ic2.core.block.comp.Energy;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiEnergyInjectorContainer extends GuiContainer {
    private static final String TEXTURE_PATH= infiniaddons.MODID+":"+"textures/gui/gui_energy_injector.png";
    private static final ResourceLocation TEXTURE = new ResourceLocation(TEXTURE_PATH);
    protected EnergyInjectorContainer inventory;
    private int totalProduceTime;
    public GuiEnergyInjectorContainer(EnergyInjectorContainer container){
        super(container);
        this.xSize = 176;
        this.ySize = 156;
        this.inventory=container;
        this.totalProduceTime=container.getTotalProduceTime();
    }
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(TEXTURE);
        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;

        this.drawTexturedModalRect(offsetX, offsetY, 0, 0, this.xSize, this.ySize);
        int ProduceTime=this.inventory.getProduceTime();
        int textureWidth=1+(int)Math.ceil(44.0*ProduceTime/totalProduceTime);
        this.drawTexturedModalRect(offsetX + 54, offsetY + 15, 0, 156, textureWidth, 44);

    }
}
