package com.maxmium.infiniaddons.Tile;

import codechicken.lib.util.CommonUtils;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySink;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.network.handshake.NetworkDispatcher;
import net.minecraftforge.server.timings.TimeTracker;

public abstract class TileMachineBase extends TileBase implements ITickable, IEnergySink {
    protected boolean isActive;
    protected TimeTracker offTracker = new TimeTracker();
    protected abstract void doWork();
    private boolean sendUpdatePacket;
    public boolean fullContainerSync;
    protected EnumFacing facing=EnumFacing.NORTH;
    public boolean updated=false;
    @Override
    public void invalidate()
    {
        super.invalidate();
        if (!this.world.isRemote && Loader.isModLoaded("IC2"))
        {
            this.onIC2MachineUnloaded();
        }
    }

    private void onIC2MachineUnloaded() {
        MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
    }

    @Override
    public final void update() {
        if (CommonUtils.isClientWorld(world)) {
            return;
        }
        if (!this.updated && Loader.isModLoaded("IC2"))
        {
            this.onIC2MachineLoaded();
            this.updated = true;
        }
        if (canWork()) {
            if (!isActive && !wasActive) {
                sendUpdatePacket = true;
            }
            isActive = true;
            wasActive = false;
            doWork();
        } else {
            if (isActive) {
                onWorkStopped();
                wasActive = true;
            }
            isActive = false;
        }
    }

    private void onIC2MachineLoaded() {
        MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
    }


    protected abstract void onWorkStopped();

    protected abstract boolean canWork();
    private boolean wasActive;

    public EnumFacing getFacing(){
        return facing;
    }

    public void setFacing(EnumFacing facing) {
        this.facing = facing;
    }
    public boolean isActive(){
        return isActive;
    }
}
