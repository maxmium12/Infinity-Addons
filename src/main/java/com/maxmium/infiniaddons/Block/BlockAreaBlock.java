package com.maxmium.infiniaddons.Block;

import com.feed_the_beast.ftblib.lib.data.ForgePlayer;
import com.feed_the_beast.ftblib.lib.data.ForgeTeam;
import com.feed_the_beast.ftblib.lib.data.Universe;
import com.feed_the_beast.ftblib.lib.gui.misc.ChunkSelectorMap;
import com.feed_the_beast.ftblib.lib.math.ChunkDimPos;
import com.feed_the_beast.ftblib.lib.math.MathUtils;
import com.feed_the_beast.ftbutilities.data.ClaimResult;
import com.feed_the_beast.ftbutilities.data.ClaimedChunk;
import com.feed_the_beast.ftbutilities.data.ClaimedChunks;
import com.feed_the_beast.ftbutilities.data.FTBUtilitiesTeamData;
import com.feed_the_beast.ftbutilities.net.MessageClaimedChunksModify;
import com.feed_the_beast.ftbutilities.net.MessageClaimedChunksRequest;
import com.feed_the_beast.ftbutilities.net.MessageClaimedChunksUpdate;
import com.maxmium.infiniaddons.Tile.TileAreaBlock;
import com.maxmium.infiniaddons.creativetab.CreativeTabsLoader;
import com.mojang.authlib.GameProfile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class BlockAreaBlock extends BlockContainer {
    public BlockAreaBlock() {
        super(Material.IRON);
        setResistance(Float.POSITIVE_INFINITY);
        setHardness(10F);
        setSoundType(SoundType.STONE);
        setCreativeTab(CreativeTabs.MISC);
        setUnlocalizedName("areaBlock");
        setRegistryName("area_block");

    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            TileAreaBlock te = (TileAreaBlock) worldIn.getTileEntity(pos);
            te.setAll(pos, playerIn.getEntityWorld().provider.getDimension(), playerIn.getUniqueID(), playerIn.getName());
            ClaimResult result = ClaimedChunks.instance.claimChunk(Universe.get().getPlayer(playerIn.getUniqueID()),new ChunkDimPos(pos, worldIn.provider.getDimension()));
            if (result == ClaimResult.SUCCESS) {
                playerIn.sendMessage(new TextComponentTranslation("block.areablock.success"));
            } else if (result == ClaimResult.ALREADY_CLAIMED) {
                playerIn.sendMessage(new TextComponentTranslation("block.areablock.already_claimed"));
            } else if (result == ClaimResult.BLOCKED) {
                playerIn.sendMessage(new TextComponentTranslation("block.areablock.deny"));
            } else if (result == ClaimResult.NO_TEAM) {
                playerIn.sendMessage(new TextComponentTranslation("block.areablock.no_team"));
            } else if (result == ClaimResult.DIMENSION_BLOCKED) {
                playerIn.sendMessage(new TextComponentTranslation("block.areablock.dim_deny"));
            } else {
                playerIn.sendMessage(new TextComponentTranslation("block.areablock.no_power"));
            }

            new MessageClaimedChunksUpdate(MathUtils.chunk(pos.getX()) - 7, MathUtils.chunk(pos.getZ()) - 7, playerIn).sendTo((EntityPlayerMP) playerIn);
        }
        return true;
    }
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileAreaBlock();
    }
    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state){
        if(!worldIn.isRemote) {
            TileAreaBlock te = (TileAreaBlock) worldIn.getTileEntity(pos);
            ClaimedChunks.instance.unclaimChunk(Universe.get().getPlayer(te.getUuid()),new ChunkDimPos(pos, worldIn.provider.getDimension()));
        }
        super.breakBlock(worldIn, pos, state);
    }
}
