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
            return 25;
        else if (target.getMaxHealth() > 50)
            return 10;
        else if (target.getMaxHealth() > 30)
            return 5;
        else
            return 1;
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
            nbtData.putInt("gexium.kill_count", pStack.getTag().getInt("gexium.kill_count") + targetEval(pTarget));
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
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (pStack.getTag().getInt("gexium.kill_count") > 0)
        {
            int killCount = pStack.getTag().getInt("gexium.kill_count");
            pTooltipComponents.add(Component.literal((Integer.toString(killCount) + "%")));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
