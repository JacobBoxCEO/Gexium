package net.jacobBoxCeo.gexium.items;

import net.jacobBoxCeo.gexium.Gexium;
import net.jacobBoxCeo.gexium.init.ModItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum ModArmorMaterial implements ArmorMaterial {
    GEXIUM("gexium", 40, new int[] {3, 6, 8, 3}, 30, SoundEvents.ARMOR_EQUIP_NETHERITE,
            4.0f, 0.0f, () -> Ingredient.of(ModItems.GEXIUM.get()));

    private static final int[] BASE_DURABILITY = {11, 16, 16, 13};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionValues;
    private final int enchantPower;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    ModArmorMaterial(String name, int durabilityMultiplier, int[] protectionValues, int enchantPower, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionValues = protectionValues;
        this.enchantPower = enchantPower;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        return BASE_DURABILITY[type.ordinal()] * durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        return protectionValues[type.ordinal()];
    }

    @Override
    public int getEnchantmentValue() {
        return enchantPower;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return Gexium.MODID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}