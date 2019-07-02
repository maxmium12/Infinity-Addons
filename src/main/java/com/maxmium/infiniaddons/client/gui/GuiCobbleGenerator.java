package com.maxmium.infiniaddons.client.gui;

import com.maxmium.infiniaddons.gui.container.CobblestoneGeneratorContainer;
import com.maxmium.infiniaddons.infiniaddons;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

import static com.mcf.davidee.nbtedit.gui.GuiSaveSlotButton.TEXTURE;

public class GuiCobbleGenerator extends GuiContainer {
    private static final String TEXTURE_PATH= infiniaddons.MODID+":"+"textures/gui/gui_cobblestone_generator.png";
    private static final ResourceLocation TEXTURE = new ResourceLocation(TEXTURE_PATH);
    public GuiCobbleGenerator(CobblestoneGeneratorContainer container){
        super(container);
        this.xSize=176;
        this.ySize=156;
    }
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(TEXTURE);
        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;

        this.drawTexturedModalRect(offsetX, offsetY, 0, 0, this.xSize, this.ySize);
    }
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY){
        String title = I18n.format("container.infiniaddons.cobblestonegenerator");
        this.fontRenderer.drawString(title, (this.xSize - this.fontRenderer.getStringWidth(title)) / 2, 8, 0x404040);
    }
}
