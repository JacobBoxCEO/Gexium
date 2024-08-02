package net.jacobBoxCeo.gexium.items;

import net.jacobBoxCeo.gexium.util.Util;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.List;

public class ModUpgradeTemplateItem extends SmithingTemplateItem {
    private static final Component GEXIUM_UPGRADE_APPLIES_TO;
    private static final Component GEXIUM_UPGRADE_INGREDIENTS;
    private static final Component GEXIUM_UPGRADE;
    private static final Component GEXIUM_UPGRADE_BASE_SLOT_DESCRIPTION;
    private static final Component GEXIUM_UPGRADE_ADDITIONS_SLOT_DESCRIPTION;
    private static final ResourceLocation EMPTY_SLOT_HELMET;
    private static final ResourceLocation EMPTY_SLOT_CHESTPLATE;
    private static final ResourceLocation EMPTY_SLOT_LEGGINGS;
    private static final ResourceLocation EMPTY_SLOT_BOOTS;
    private static final ResourceLocation EMPTY_SLOT_HOE;
    private static final ResourceLocation EMPTY_SLOT_AXE;
    private static final ResourceLocation EMPTY_SLOT_SWORD;
    private static final ResourceLocation EMPTY_SLOT_SHOVEL;
    private static final ResourceLocation EMPTY_SLOT_PICKAXE;
    private static final ResourceLocation EMPTY_SLOT_INGOT;

    static {
        GEXIUM_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item",
                new ResourceLocation("smithing_template.gexium_upgrade.applies_to"))).withStyle(ChatFormatting.BLUE);
        GEXIUM_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item",
                new ResourceLocation("smithing_template.gexium_upgrade.ingredients"))).withStyle(ChatFormatting.BLUE);
        GEXIUM_UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade",
                new ResourceLocation("gexium_upgrade"))).withStyle(ChatFormatting.GRAY);
        GEXIUM_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item",
                new ResourceLocation("smithing_template.gexium_upgrade.base_slot_description")));
        GEXIUM_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item",
                new ResourceLocation("smithing_template.gexium_upgrade.additions_slot_description")));
        EMPTY_SLOT_HELMET = new ResourceLocation("item/empty_armor_slot_helmet");
        EMPTY_SLOT_CHESTPLATE = new ResourceLocation("item/empty_armor_slot_chestplate");
        EMPTY_SLOT_LEGGINGS = new ResourceLocation("item/empty_armor_slot_leggings");
        EMPTY_SLOT_BOOTS = new ResourceLocation("item/empty_armor_slot_boots");
        EMPTY_SLOT_HOE = new ResourceLocation("item/empty_slot_hoe");
        EMPTY_SLOT_AXE = new ResourceLocation("item/empty_slot_axe");
        EMPTY_SLOT_SWORD = new ResourceLocation("item/empty_slot_sword");
        EMPTY_SLOT_SHOVEL = new ResourceLocation("item/empty_slot_shovel");
        EMPTY_SLOT_PICKAXE = new ResourceLocation("item/empty_slot_pickaxe");
        EMPTY_SLOT_INGOT = new ResourceLocation("item/empty_slot_ingot");
    }

    public ModUpgradeTemplateItem(Component pAppliesTo, Component pIngredients, Component pUpdradeDescription, Component pBaseSlotDescription, Component pAdditionsSlotDescription, List<ResourceLocation> pBaseSlotEmptyIcons, List<ResourceLocation> pAdditonalSlotEmptyIcons) {
        super(pAppliesTo, pIngredients, pUpdradeDescription, pBaseSlotDescription, pAdditionsSlotDescription, pBaseSlotEmptyIcons, pAdditonalSlotEmptyIcons);
    }

    public static ModUpgradeTemplateItem createGexiumUpgrade() {
        return new ModUpgradeTemplateItem(GEXIUM_UPGRADE_APPLIES_TO, GEXIUM_UPGRADE_INGREDIENTS, GEXIUM_UPGRADE, GEXIUM_UPGRADE_BASE_SLOT_DESCRIPTION, GEXIUM_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, createGexiumUpgradeIconList(), createGexiumUpgradeMaterialList());
    }

    private static List<ResourceLocation> createGexiumUpgradeIconList() {
        return List.of(EMPTY_SLOT_HELMET, EMPTY_SLOT_SWORD, EMPTY_SLOT_CHESTPLATE, EMPTY_SLOT_PICKAXE, EMPTY_SLOT_LEGGINGS, EMPTY_SLOT_AXE, EMPTY_SLOT_BOOTS, EMPTY_SLOT_HOE, EMPTY_SLOT_SHOVEL);
    }

    private static List<ResourceLocation> createGexiumUpgradeMaterialList() {
        return List.of(EMPTY_SLOT_INGOT);
    }
}
