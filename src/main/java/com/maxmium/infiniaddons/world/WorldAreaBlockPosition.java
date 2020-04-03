package com.maxmium.infiniaddons.world;

import com.feed_the_beast.ftblib.lib.math.ChunkDimPos;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldSavedData;

import java.util.*;

public class WorldAreaBlockPosition extends WorldSavedData {
    private List<ChunkDimPos>chunkDimPos=new ArrayList<ChunkDimPos>();
    private List<BlockPos>pos=new ArrayList<BlockPos>();
    private Map<ChunkDimPos,BlockPos> areaBlockPos=new HashMap<>();
    public WorldAreaBlockPosition(String name) {
        super(name);
    }
    public int size(){
        return chunkDimPos.size();
    }
    public void add(ChunkDimPos chunkDimPos,BlockPos pos){
        areaBlockPos.put(chunkDimPos, pos);
        this.chunkDimPos.add(chunkDimPos);
        this.pos.add(pos);
        this.markDirty();
    }
    public List<ChunkDimPos> getChunkDimPoses(){
        return chunkDimPos;
    }
    public void removeElement(BlockPos pos,ChunkDimPos dimPos){
        chunkDimPos.remove(dimPos);
        this.pos.remove(pos);
    }
    public List<BlockPos> getPoses(){
        return pos;
    }
    public Map<ChunkDimPos,BlockPos> getAreaBlockPos(){
        return areaBlockPos;
    }
    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        this.pos.clear();
        this.chunkDimPos.clear();
        NBTTagList list=(NBTTagList) nbt.getTag("areaBlock_positions");
        if(list==null){
            list=new NBTTagList();
        }
        for (int i=list.tagCount()-1;i>=0;--i){
            NBTTagCompound compound= (NBTTagCompound) list.get(i);
            pos.add(new BlockPos(compound.getInteger("x"),compound.getInteger("y"),compound.getInteger("z")));
            chunkDimPos.add(new ChunkDimPos(compound.getInteger("chunk_x"),compound.getInteger("chunk_z"),compound.getInteger("dim")));
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        NBTTagList list=new NBTTagList();
        for(int i=chunkDimPos.size()-1;i>=0;--i){
            ChunkDimPos chunkDimPos=this.chunkDimPos.get(i);
            BlockPos pos=this.pos.get(i);
            NBTTagCompound compound=new NBTTagCompound();
            compound.setInteger("chunk_x",chunkDimPos.posX);
            compound.setInteger("chunk_z",chunkDimPos.posZ);
            compound.setInteger("dim",chunkDimPos.dim);
            compound.setInteger("x",pos.getX());
            compound.setInteger("y",pos.getY());
            compound.setInteger("z",pos.getZ());
            list.appendTag(compound);
        }
        nbt.setTag("areaBlock_positions",list);
        return nbt;
    }
    public static WorldAreaBlockPosition get(World world){
        WorldSavedData data=world.getPerWorldStorage().getOrLoadData(WorldAreaBlockPosition.class,"AreaBlockPosition");
        if(data==null){
            data=new WorldAreaBlockPosition("AreaBlockPosition");
            world.getMapStorage().setData("AreaBlockPosition",data);
        }
        return (WorldAreaBlockPosition)data;
    }
    public static WorldAreaBlockPosition getGlobal(World world){
        WorldSavedData data=world.getMapStorage().getOrLoadData(WorldAreaBlockPosition.class,"AreaBlockPosition");
        if(data==null){
            data=new WorldAreaBlockPosition("AreaBlockPosition");
            world.getMapStorage().setData("AreaBlockPosition",data);
        }
        return (WorldAreaBlockPosition)data;
    }
}
