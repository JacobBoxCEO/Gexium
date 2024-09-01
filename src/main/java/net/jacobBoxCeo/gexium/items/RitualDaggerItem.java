package net.jacobBoxCeo.gexium.items;

import net.jacobBoxCeo.gexium.util.Util;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RitualDaggerItem extends SwordItem {

    public RitualDaggerItem(Properties pProperties) {
        super(ModToolTiers.GEXIUM, -3, -2, pProperties);
    }

    public int getKills(ItemStack stack) {
        return !stack.hasTag() ? 0 : stack.getTag().getInt("gexium.kill_count");
    }

    public int getMaxKills() {
        return 100;
    }

    private void setKills(ItemStack stack, int newValue) {
        CompoundTag nbtData = new CompoundTag();
        if (newValue > 100 || 0 > newValue) {
            nbtData.putInt("gexium.kill_count", newValue);
            stack.setTag(nbtData);
        } else {
            throw new IllegalStateException("Kill Count was attempted to be set outside of bounds!");
        }
    }

    public void resetKills(ItemStack stack) {
        setKills(stack, 0);
    }

    public void increaseKills(ItemStack stack, int modifier) {
        int value = Math.min(getKills(stack) + modifier, getMaxKills());
        setKills(stack, value);
    }

    private int targetEval(LivingEntity target) {
        if (target.getType().getCategory() == MobCategory.MONSTER || target instanceof Player){
            if (target instanceof Player) {
                return getMaxKills();
            }
            return (int) Math.floor(100 * target.getMaxHealth()/200);
        }
        return 0;
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack pStack, LivingEntity pTarget, @NotNull LivingEntity pAttacker) {
        if (pTarget.getHealth() <= 0f) {
            int targetValue = targetEval(pTarget);
            increaseKills(pStack, targetValue);
        }
        return true;
    }

    @Override
    public boolean isBarVisible(@NotNull ItemStack pStack) {
        return getKills(pStack) > 0;
    }

    @Override
    public int getBarWidth(@NotNull ItemStack pStack) {
        return Math.round(((float) getKills(pStack) / getMaxKills()) * 13f);
    }

    @Override
    public int getBarColor(@NotNull ItemStack pStack) {
        float value = (float) (((float) getKills(pStack) / (getMaxKills() * 2)) + 0.5);
        return Mth.hsvToRgb(0f, 1f, value);
    }

    @Override
    public boolean isFoil(@NotNull ItemStack pStack) {
        return getKills(pStack) == getMaxKills();
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return false;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        int killCount = getKills(pStack);
        if (killCount == getMaxKills()) {
            Util.literalComponent(pTooltipComponents, killCount +"/" + getMaxKills(), ChatFormatting.DARK_RED, ChatFormatting.ITALIC);
            Util.translatableComponent(pTooltipComponents, "item", "ritual_dagger.kill_unit", ChatFormatting.DARK_RED, ChatFormatting.ITALIC);
        } else {
            Util.literalComponent(pTooltipComponents,killCount + "/" + getMaxKills(), ChatFormatting.GRAY, ChatFormatting.ITALIC);
            Util.translatableComponent(pTooltipComponents, "item", "ritual_dagger.kill_unit", ChatFormatting.GRAY, ChatFormatting.ITALIC);
        }
    }
}
