package com.maxmium.infiniaddons.events;

import moze_intel.projecte.api.ProjectEAPI;
import moze_intel.projecte.api.capabilities.IKnowledgeProvider;
import moze_intel.projecte.api.item.IItemEmc;
import moze_intel.projecte.emc.EMCMapper;
import moze_intel.projecte.emc.SimpleStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.IItemHandlerModifiable;

import java.util.ArrayList;
import java.util.List;

public class ThreadTimer extends Thread{
   public boolean runnable;
    public EntityPlayer player;
    public ThreadTimer(EntityPlayer player){
        this.player=player;
    }

    IKnowledgeProvider provider=player.getCapability(ProjectEAPI.KNOWLEDGE_CAPABILITY,null);
    @Override
    public void run(){

        while(runnable=true){
            provider.sync((EntityPlayerMP) player);
            try {
                sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
                }
                public void setRunnable(boolean b){
                    runnable=b;
                }
            }



