package com.maxmium.infiniaddons.api;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IModelRegister {
    @SideOnly(Side.CLIENT)
    void RegisterModels();
}
