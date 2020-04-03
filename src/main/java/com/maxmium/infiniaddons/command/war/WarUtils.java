package com.maxmium.infiniaddons.command.war;

import com.feed_the_beast.ftblib.lib.data.ForgePlayer;
import com.feed_the_beast.ftblib.lib.data.ForgeTeam;
import com.feed_the_beast.ftblib.lib.data.TeamType;
import com.feed_the_beast.ftblib.lib.data.Universe;
import com.feed_the_beast.ftblib.lib.math.ChunkDimPos;
import javafx.util.Pair;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.chunk.Chunk;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
public class WarUtils {
    public WarUtils instance;
    public static List<ArrayList<EntityPlayer>> battlingteam=new ArrayList<>();
    public static Map<ArrayList<EntityPlayer>, ChunkDimPos> map;
}
