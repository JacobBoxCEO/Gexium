package net.jacobBoxCeo.gexium.datagen;

import net.jacobBoxCeo.gexium.blocks.ModBlocks;
import net.jacobBoxCeo.gexium.items.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;

public class ModRecipesGen extends RecipeProvider implements IConditionBuilder
{

    public ModRecipesGen(PackOutput pOutput)
    {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer)
    {
        threeByThreePackingRecipe(RecipeCategory.MISC, ModBlocks.GEXITE_BLOCK, ModItems.GEXITE, consumer);
        threeByThreePackingRecipe(RecipeCategory.MISC, ModBlocks.GEXIUM_BLOCK, ModItems.GEXIUM, consumer);
    }
    static private void threeByThreePackingRecipe(RecipeCategory category, RegistryObject<Block> block, RegistryObject<Item> item, Consumer consumer)
    {
        ShapedRecipeBuilder.shaped(category, block.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', item.get())
                .unlockedBy(getHasName(item.get()), has(item.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(category, item.get(), 9)
                .requires(block.get())
                .unlockedBy(getHasName(block.get()), has(block.get()))
                .save(consumer);
    }
}
