package com.maxmium.infiniaddons.capability;

public interface ICapabilityWar {
    boolean isWar();
    void setWar(boolean war);
    int getDeadtimes();
    void setDeadtimes(int deadtimes);
    long getWarEndTime();
    boolean isUnableWar();
    void setUnableWar(boolean war);
    void setWarEndTime(long time);
    void setDefence(boolean defence);
    boolean isDefence();
}
