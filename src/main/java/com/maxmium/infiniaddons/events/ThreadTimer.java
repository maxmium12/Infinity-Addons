package com.maxmium.infiniaddons.events;

import com.maxmium.infiniaddons.common.ConfigLoader;
import moze_intel.projecte.api.ProjectEAPI;
import moze_intel.projecte.api.capabilities.IKnowledgeProvider;
import moze_intel.projecte.api.item.IItemEmc;
import moze_intel.projecte.emc.EMCMapper;
import moze_intel.projecte.emc.SimpleStack;
import moze_intel.projecte.network.PacketHandler;
import moze_intel.projecte.network.packets.SyncEmcPKT;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.IItemHandlerModifiable;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class ThreadTimer extends Thread{
   public boolean runnable;
    public EntityPlayer player;
    private Thread t;
    public String threadName;
    public ThreadTimer(EntityPlayer player,String threadName)
    {
        this.player=player;
        this.threadName=threadName;

    }
    @Override
    public void start(){
        if(t==null){
            t=new Thread(this,threadName);
            t.start();
            runnable=true;
            ConfigLoader.logger().info("Thread Create");
        }
    }
    @Override
    public void run() {
        IKnowledgeProvider knowledge = player.getCapability(ProjectEAPI.KNOWLEDGE_CAPABILITY, null);
        while (runnable) {
            knowledge.sync((EntityPlayerMP)player);
            PacketHandler.sendFragmentedEmcPacket((EntityPlayerMP)player);
            try {
                sleep(1000);{
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}



