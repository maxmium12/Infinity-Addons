package com.maxmium.infiniaddons.command.war;

import com.feed_the_beast.ftblib.lib.data.ForgePlayer;
import com.feed_the_beast.ftblib.lib.data.ForgeTeam;
import com.feed_the_beast.ftblib.lib.data.TeamType;
import com.feed_the_beast.ftblib.lib.data.Universe;

import java.util.UUID;

public class WarFakePlayer {
    private ForgeTeam team;
    private UUID uuid=UUID.fromString("825a7ae3-605a-4978-bf5f-1af0fa069a9d");
    public WarFakePlayer(){
        if(Universe.get().getPlayer(uuid)==null) {
            ForgePlayer fakeplayer = new ForgePlayer(Universe.get(), uuid, "Nightingale");
            Universe.get().players.put(uuid, fakeplayer);
            fakeplayer.clearCache();
            team = new ForgeTeam(Universe.get(), Universe.get().generateTeamUID((short) 0), "WarChunk", TeamType.SERVER);
        }
        }
        public ForgeTeam getTeam(){
        return team;
        }
}
