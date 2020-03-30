package com.maxmium.infiniaddons.Tile;

import com.feed_the_beast.ftblib.lib.math.ChunkDimPos;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

import java.util.UUID;

public class TileAreaBlock extends TileBase {
    private int dim;
    private int x;
    private int y;
    private int z;
    private UUID uuid=UUID.fromString("19f51a77-b6fb-3469-a8b6-228ec5ce5961");
    private String name="nightingale";
    @Override
    public void readFromNBT(NBTTagCompound compound){
        super.readFromNBT(compound);
        this.x=compound.getInteger("X");
        this.y=compound.getInteger("Y");
        this.z=compound.getInteger("Z");
        this.uuid=UUID.fromString(compound.getString("uuid"));
        this.name=compound.getString("name");
        this.dim=compound.getInteger("dim");
    }
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setInteger("X",x);
        compound.setInteger("Y",y);
        compound.setInteger("Z",z);
        compound.setString("uuid",uuid.toString());
        compound.setString("name",name);
        compound.setInteger("dim",dim);
        return super.writeToNBT(compound);
    }
    public ChunkDimPos getChunkPos(){
        return new ChunkDimPos((int)Math.floor(x/16),(int)Math.floor(z/16),dim);
    }
    public UUID getUuid(){
        return uuid;
    }
    public String getName(){
        return name;
    }
    public void setAll(BlockPos pos,int dim,UUID uuid,String name){
        this.x=pos.getX();
        this.y=pos.getY();
        this.z=pos.getZ();
        this.dim=dim;
        this.uuid=uuid;
        this.name=name;
    }
}
