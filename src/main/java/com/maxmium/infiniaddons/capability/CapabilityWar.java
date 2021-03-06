package com.maxmium.infiniaddons.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CapabilityWar{

public static class Storage implements Capability.IStorage<ICapabilityWar>{
    @Nullable
    @Override
    public NBTBase writeNBT(Capability<ICapabilityWar> capability, ICapabilityWar instance, EnumFacing side) {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setBoolean("war", instance.isWar());
        compound.setInteger("deadtimes", instance.getDeadtimes());
        compound.setLong("warendtime",instance.getWarEndTime());
        compound.setBoolean("isunablewar",instance.isUnableWar());
        compound.setBoolean("isdefence",instance.isDefence());
        return compound;
    }

    @Override
    public void readNBT(Capability<ICapabilityWar> capability, ICapabilityWar instance, EnumFacing side, NBTBase nbt) {
        NBTTagCompound compound = (NBTTagCompound) nbt;
        instance.setWar(compound.getBoolean("war"));
        instance.setDeadtimes(compound.getInteger("deadtimes"));
        instance.setUnableWar(compound.getBoolean("isunablewar"));
        instance.setWarEndTime(compound.getLong("warendtime"));
        instance.setDefence(compound.getBoolean("isdefence"));
    }
}
    public static class Implementation implements ICapabilityWar{
        private boolean war;
        private int deadtimes;
        private long warendtime;
        private boolean unablewar;
        private boolean defence;

        @Override
        public boolean isWar() {
            return war;
        }

        @Override
        public void setWar(boolean war) {
            this.war=war;
        }

        @Override
        public int getDeadtimes() {
            return deadtimes;
        }

        @Override
        public void setDeadtimes(int deadtimes) {
            this.deadtimes=deadtimes;
        }

        @Override
        public long getWarEndTime() {
            return warendtime;
        }

        @Override
        public boolean isUnableWar() {
            return unablewar;
        }

        @Override
        public void setUnableWar(boolean war) {
            unablewar=war;
        }

        @Override
        public void setWarEndTime(long time) {
            warendtime=time;
        }

        @Override
        public void setDefence(boolean defence) {
            this.defence=defence;
        }

        @Override
        public boolean isDefence() {
            return defence;
        }
    }
    public static class ProviderPlayer implements ICapabilitySerializable<NBTTagCompound> {
        private ICapabilityWar capabilityWar=new Implementation();
        private Capability.IStorage<ICapabilityWar> storage=new Storage();

        @Override
        public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
            return CapabilityHandler.capabilityWar.equals(capability);
        }

        @Nullable
        @Override
        public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
            if(CapabilityHandler.capabilityWar.equals(capability)){
                T result=(T) capabilityWar;
                return result;
            }
            return null;
        }

        @Override
        public NBTTagCompound serializeNBT() {
            NBTTagCompound compound=new NBTTagCompound();
            compound.setTag("warStat",storage.writeNBT(CapabilityHandler.capabilityWar,capabilityWar,null));
            return compound;
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt) {
            NBTTagCompound compound=nbt.getCompoundTag("warStat");
            storage.readNBT(CapabilityHandler.capabilityWar,capabilityWar,null,compound);
        }

    }
}
