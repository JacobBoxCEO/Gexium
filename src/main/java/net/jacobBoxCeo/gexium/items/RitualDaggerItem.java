package net.jacobBoxCeo.gexium.items;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
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

    private int targetEval(LivingEntity target) {
        if (target.getType().getCategory() == MobCategory.MONSTER | target instanceof Player){
            if (target instanceof Player)
                return 100;
            return (int) (100 * Math.floorDiv((long) target.getMaxHealth(),200));
        }
        return 0;
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack pStack, LivingEntity pTarget, @NotNull LivingEntity pAttacker) {
        CompoundTag nbtData = new CompoundTag();
        if (pTarget.getHealth() <= 0f) {
            int targetValue = targetEval(pTarget);
            assert pStack.getTag() != null;
            int killCount = pStack.getTag().getInt("gexium.kill_count");
            if (killCount < 100) {
                nbtData.putInt("gexium.kill_count", Math.min((killCount + targetValue), 100));
                pStack.setTag(nbtData);
            }
        }
        return true;
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        assert pStack.getTag() != null;
        return pStack.getTag().getInt("gexium.kill_count") == 100;
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
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        assert pStack.getTag() != null;
        int killCount = pStack.getTag().getInt("gexium.kill_count");
        if (killCount == 100) {
            pTooltipComponents.add(Component.literal(("100/100")).withStyle(ChatFormatting.DARK_RED));
        }
        else if (killCount > 0) {
            pTooltipComponents.add(Component.literal((killCount + "/100")).withStyle(ChatFormatting.GRAY));
        }
    }
}
