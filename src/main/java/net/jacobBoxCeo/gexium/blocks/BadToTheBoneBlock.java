package net.jacobBoxCeo.gexium.blocks;

import net.jacobBoxCeo.gexium.init.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class BadToTheBoneBlock extends Block
{
    public BadToTheBoneBlock(Properties pProperties)
    {
        super(pProperties);
    }

    @Override
    public InteractionResult use(
            BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit)
    {
        pLevel.playSound(pPlayer,pPos, ModSounds.BAD_TO_THE_BONE.get(), SoundSource.BLOCKS);
        pLevel.explode(null,
                pPos.getX(),
                pPos.getY(),
                pPos.getZ(),
                20f,
                Level.ExplosionInteraction.TNT);
        return InteractionResult.SUCCESS;
    }
}
