package com.maxmium.infiniaddons.gui;

import com.maxmium.infiniaddons.client.gui.GuiCobbleGenerator;
import com.maxmium.infiniaddons.client.gui.GuiEnergyInjectorContainer;
import com.maxmium.infiniaddons.gui.container.CobblestoneGeneratorContainer;
import com.maxmium.infiniaddons.gui.container.EnergyInjectorContainer;
import com.maxmium.infiniaddons.infiniaddons;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import javax.annotation.Nullable;

public class GuiHandler implements IGuiHandler {
    public static final int GUI_INJECTOR=1;
    public static final int GUI_COBBLE=2;
    public GuiHandler(){
        NetworkRegistry.INSTANCE.registerGuiHandler(infiniaddons.instance, this);
    }

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID){
            case GUI_INJECTOR:
                return new EnergyInjectorContainer(player,world.getTileEntity(new BlockPos(x,y,z)));
            case  GUI_COBBLE:
                return new CobblestoneGeneratorContainer(player,world.getTileEntity(new BlockPos(x,y,z)));
                default:
                return null;
        }
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID){
            case GUI_INJECTOR:
                return new GuiEnergyInjectorContainer(new EnergyInjectorContainer(player, world.getTileEntity(new BlockPos(x, y, z))));
            case GUI_COBBLE:
                return new GuiCobbleGenerator(new CobblestoneGeneratorContainer(player,world.getTileEntity(new BlockPos(x,y,z))));
                default:
                return null;
        }
    }
}
