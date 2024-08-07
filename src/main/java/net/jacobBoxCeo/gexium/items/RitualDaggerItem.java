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

    private int getKills(ItemStack stack) {
        return !stack.hasTag() ? 0 : stack.getTag().getInt("gexium.killCount");
    }

    private int getMaxKills() {
        return 100;
    }

    private int targetEval(LivingEntity target) {
        if (target.getType().getCategory() == MobCategory.MONSTER | target instanceof Player){
            if (target instanceof Player)
                return getMaxKills();
            return (int) Math.floor(100 * target.getMaxHealth()/200);
        }
        return 0;
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack pStack, LivingEntity pTarget, @NotNull LivingEntity pAttacker) {
        CompoundTag nbtData = new CompoundTag();
        if (pTarget.getHealth() <= 0f) {
            int targetValue = targetEval(pTarget);
            assert pStack.getTag() != null;
            int killCount = getKills(pStack);
            if (killCount < getMaxKills()) {
                nbtData.putInt("gexium.killCount", Math.min((killCount + targetValue), getMaxKills()));
                pStack.setTag(nbtData);
            }
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
        if (killCount == 100) {
            Util.addLiteralComponent(pTooltipComponents, killCount +"/" + getMaxKills(), ChatFormatting.DARK_RED);
            Util.addTranslatableComponent(pTooltipComponents, "item", "ritual_dagger.kill_unit", ChatFormatting.DARK_RED);
        }
        else if (killCount > 0) {
            Util.addLiteralComponent(pTooltipComponents,killCount + "/" + getMaxKills(), ChatFormatting.GRAY);
            Util.addTranslatableComponent(pTooltipComponents, "item", "ritual_dagger.kill_unit", ChatFormatting.GRAY);
        }
    }




}
