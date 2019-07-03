package com.maxmium.infiniaddons.Tile;

import ic2.api.energy.tile.IEnergyEmitter;
import net.minecraft.util.EnumFacing;

public class TileEnergyConverter extends TileMachineBase{
    private double receivedEnergyUnit;

    @Override
    protected void doWork() {

    }

    @Override
    protected void onWorkStopped() {

    }

    @Override
    protected boolean canWork() {
        return true;
    }

    public double getEnergyCapacity()
    {
        return 3276800;
    }
    @Override
    public double getDemandedEnergy() {
        return Math.max(0,this.getEnergyCapacity()-this.receivedEnergyUnit);
    }

    @Override
    public int getSinkTier() {
        return 5;
    }

    @Override
    public double injectEnergy(EnumFacing directionFrom, double amount, double voltage) {
        this.receivedEnergyUnit += amount;
        return 0;
    }


    @Override
    public boolean acceptsEnergyFrom(IEnergyEmitter emitter, EnumFacing side) {
        return true;
    }
}
