package net.jacobBoxCeo.gexium.items;

import net.jacobBoxCeo.gexium.Gexium;
import net.jacobBoxCeo.gexium.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolTiers
{
    public static final Tier GEXITE = TierSortingRegistry.registerTier(
            new ForgeTier(5,4096,12f,5f,25,
                    ModTags.Blocks.NEEDS_GEXITE_TOOL, () -> Ingredient.of(ModItems.GEXITE.get())),
            new ResourceLocation(Gexium.MODID, "gexite_ingot"), List.of(Tiers.NETHERITE), List.of()
    );
    public static final Tier GEXIUM = TierSortingRegistry.registerTier(
            new ForgeTier(6,8192,14f,7f,30,
                    ModTags.Blocks.NEEDS_GEXITE_TOOL, () -> Ingredient.of(ModItems.GEXIUM.get())),
            new ResourceLocation(Gexium.MODID, "gexium_ingot"), List.of(Tiers.NETHERITE), List.of()
    );
}
