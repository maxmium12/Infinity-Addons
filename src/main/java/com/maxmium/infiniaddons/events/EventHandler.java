package com.maxmium.infiniaddons.events;

import com.maxmium.infiniaddons.infiniaddons;
import moze_intel.projecte.api.ProjectEAPI;
import moze_intel.projecte.api.capabilities.IKnowledgeProvider;
import moze_intel.projecte.emc.EMCMapper;
import moze_intel.projecte.gameObjs.container.TransmutationContainer;
import moze_intel.projecte.gameObjs.container.inventory.TransmutationInventory;
import moze_intel.projecte.gameObjs.gui.GUITransmutation;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
        import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumFacing;
        import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
        import net.minecraftforge.fml.common.Loader;
        import net.minecraftforge.fml.common.Mod;
        import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
        import net.minecraftforge.items.IItemHandlerModifiable;
        import net.minecraftforge.items.ItemStackHandler;
        import net.minecraftforge.items.wrapper.CombinedInvWrapper;
        import scala.Array;

        import javax.swing.plaf.basic.BasicComboBoxUI;
        import java.util.ArrayList;
        import java.util.List;
@Mod.EventBusSubscriber(modid=infiniaddons.MODID)
public class EventHandler {
    @SubscribeEvent
    public void onPlayerOpenContainer(PlayerContainerEvent.Open evt) {
        if (Loader.isModLoaded("projecte")) {
            EntityPlayer player = evt.getEntityPlayer();
            InventoryPlayer playerivt = new InventoryPlayer(player);
            if (player.isServerWorld()) {
                //判断是否是转化桌Container
                TransmutationContainer container = new TransmutationContainer(playerivt, new TransmutationInventory(player), EnumHand.MAIN_HAND);
                if (evt.getContainer().equals(container)) {
                    ThreadTimer threadTimer = new ThreadTimer(player);
                    threadTimer.setRunnable(true);
                    threadTimer.run();
                }
            }
        }


    }

    @SubscribeEvent
    public void onPlayerCloseContainer(PlayerContainerEvent.Close evt) {
        if (Loader.isModLoaded("projecte")) {
            EntityPlayer player = evt.getEntityPlayer();
            InventoryPlayer playerivt = new InventoryPlayer(player);
            if (player.isServerWorld()) {
                //判断是否是转化桌Container
                TransmutationContainer container = new TransmutationContainer(playerivt, new TransmutationInventory(player), EnumHand.MAIN_HAND);
                if (evt.getContainer().equals(container)) {
                    ThreadTimer threadTimer = new ThreadTimer(player);
                    threadTimer.setRunnable(false);
                }
                //ProjectE Repair Event

            }
        }
    }
    @SubscribeEvent
    public void onPlayerAttackEntity(AttackEntityEvent evt){
        Entity entity=evt.getTarget();
        if(entity instanceof EntityPlayer){
            if(evt.getEntityPlayer().getHeldItemMainhand().getItem() instanceof ItemTool);
        }
    }
}
