package com.maxmium.infiniaddons;

import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.util.EnumFacing;

public class InfiniaddonsProps {
    public static final PropertyEnum<EnumFacing> HORIZONAL_FACING=PropertyEnum.create("facing",EnumFacing.class,facing -> facing.getAxis() != EnumFacing.Axis.Y);
    public static final PropertyBool ACTIVE = PropertyBool.create("active");
}
