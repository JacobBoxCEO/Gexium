package net.jacobBoxCeo.gexium.datagen;

import net.jacobBoxCeo.gexium.init.ModBlocks;
import net.jacobBoxCeo.gexium.init.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;

public class ModRecipesProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipesProvider(PackOutput pOutput) {
        super(pOutput);
    }

    private static void packingRecipe(RegistryObject<Block> block, RegistryObject<Item> item, Consumer consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, block.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', item.get())
                .unlockedBy(getHasName(item.get()), has(item.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, item.get(), 9)
                .requires(block.get())
                .unlockedBy(getHasName(block.get()), has(block.get()))
                .save(consumer);
    }

    private static void gexiumSmithing(Consumer<FinishedRecipe> pFinishedRecipeConsumer, Item pIngredientItem, Item pResultItem) {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.GEXIUM_UPGRADE_TEMPLATE.get()),
                        Ingredient.of(pIngredientItem),
                        Ingredient.of(ModItems.GEXIUM.get()),
                        RecipeCategory.MISC,
                        pResultItem)
                .unlocks("has_gexium_ingot",
                        has(ModItems.GEXIUM.get()))
                .save(pFinishedRecipeConsumer,
                        getItemName(pResultItem) + "_smithing");
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        packingRecipe(ModBlocks.GEXIUM_BLOCK, ModItems.GEXIUM, consumer);
        gexiumSmithing(consumer, Items.NETHERITE_AXE, ModItems.GEXIUM_AXE.get());
        gexiumSmithing(consumer, Items.NETHERITE_PICKAXE, ModItems.GEXIUM_PICKAXE.get());
        gexiumSmithing(consumer, Items.NETHERITE_SWORD, ModItems.GEXIUM_SWORD.get());
        gexiumSmithing(consumer, Items.NETHERITE_SHOVEL, ModItems.GEXIUM_SHOVEL.get());
        gexiumSmithing(consumer, Items.NETHERITE_HOE, ModItems.GEXIUM_HOE.get());
        gexiumSmithing(consumer, Items.NETHERITE_HELMET, ModItems.GEXIUM_HELMET.get());
        gexiumSmithing(consumer, Items.NETHERITE_CHESTPLATE, ModItems.GEXIUM_CHESTPLATE.get());
        gexiumSmithing(consumer, Items.NETHERITE_LEGGINGS, ModItems.GEXIUM_LEGGINGS.get());
        gexiumSmithing(consumer, Items.NETHERITE_BOOTS, ModItems.GEXIUM_BOOTS.get());
        //ritual dagger recipe
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.RITUAL_DAGGER.get())
                .pattern("g  ")
                .pattern("s  ")
                .pattern("   ")
                .define('g', ModItems.GEXIUM.get())
                .define('s', Items.STICK)
                .unlockedBy(getHasName(ModItems.GEXIUM.get()), has(ModItems.GEXIUM.get()))
                .save(consumer);
    }
}
