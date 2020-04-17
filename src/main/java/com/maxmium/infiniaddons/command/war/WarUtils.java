package com.maxmium.infiniaddons.command.war;

import com.feed_the_beast.ftblib.lib.data.ForgePlayer;
import com.feed_the_beast.ftblib.lib.data.ForgeTeam;
import com.feed_the_beast.ftblib.lib.data.TeamType;
import com.feed_the_beast.ftblib.lib.data.Universe;
import com.feed_the_beast.ftblib.lib.math.ChunkDimPos;
import javafx.util.Pair;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Optional;
import omtteam.openmodularturrets.tileentity.TurretBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
public class WarUtils {
    public static WarUtils instance;
    public static List<ArrayList<EntityPlayer>> battlingteam=new ArrayList<>();
    public static Map<ArrayList<EntityPlayer>, ChunkDimPos> map;
    public void setTurrentAttackPlayer(World world,int x,int z,boolean istrue){
        if(Loader.isModLoaded("openmodularturrents")){
            Chunk chunk=new Chunk(world,x,z);
            for (TileEntity entity : chunk.getTileEntityMap().values()) {
                if (entity instanceof TurretBase) {
                    ((TurretBase) entity).setAttacksPlayers(istrue);
                }
            }
        }
    }
}
