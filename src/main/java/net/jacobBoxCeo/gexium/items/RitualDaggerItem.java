package net.jacobBoxCeo.gexium.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RitualDaggerItem extends SwordItem
{

    public RitualDaggerItem(Properties pProperties)
    {
        super(ModToolTiers.GEXIUM, -3, -2, pProperties);
    }

    private int targetEval(LivingEntity target)
    {
        if (target instanceof Player | target.getMaxHealth() > 200)
            return 100;
        else if (target.getMaxHealth() > 100)
            return 50;
        else if (target.getMaxHealth() > 50)
            return 15;
        else if (target.getMaxHealth() > 30)
            return 5;
        else if (target.getMaxHealth() > 10)
            return 1;
        else
            return 0;
    }

    public int getMaxDamage(ItemStack stack)
    {
        return 300;
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker)
    {
        CompoundTag nbtData = new CompoundTag();
        if (pTarget.getHealth() <= 0f)
        {
            int targetValue = targetEval(pTarget);
            int killCount = pStack.getTag().getInt("gexium.kill_count");
            nbtData.putInt("gexium.kill_count", Math.min((killCount + targetValue), 100));
            pStack.setTag(nbtData);
        }
        return true;
    }

    @Override
    public boolean isFoil(ItemStack pStack)
    {
        if (pStack.getTag().getInt("gexium.kill_count") >= 100)
            return pStack.hasTag();
        else
            return false;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced)
    {
        int killCount = pStack.getTag().getInt("gexium.kill_count");
        if (killCount > 0)
            pTooltipComponents.add(Component.literal((killCount + "% charged")));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
