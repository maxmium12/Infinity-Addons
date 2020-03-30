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
        return compound;
    }

    @Override
    public void readNBT(Capability<ICapabilityWar> capability, ICapabilityWar instance, EnumFacing side, NBTBase nbt) {
        NBTTagCompound compound = (NBTTagCompound) nbt;
        instance.setWar(compound.getBoolean("war"));
        instance.setDeadtimes(compound.getInteger("deadtimes"));
    }
}
    public static class Implementation implements ICapabilityWar{
        private boolean war;
        private int deadtimes;

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
