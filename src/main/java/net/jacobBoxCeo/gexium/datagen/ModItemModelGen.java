package net.jacobBoxCeo.gexium.datagen;

import net.jacobBoxCeo.gexium.Gexium;
import net.jacobBoxCeo.gexium.blocks.ModBlocks;
import net.jacobBoxCeo.gexium.items.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelGen extends ItemModelProvider
{
    public ModItemModelGen(PackOutput output, ExistingFileHelper existingFileHelper)
    {
        super(output, Gexium.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels()
    {
        for(RegistryObject<Item> item : ModItems.ITEMS.getEntries())
        {
            if (!checkIsBlock(item))
            {
                simpleItem(item);
            }
        }
    }

    private static boolean checkIsBlock(RegistryObject<Item> item)
    {
        boolean isBlock = false;
        for (RegistryObject<Block> block : ModBlocks.BLOCKS.getEntries())
        {
            if (item.equals(block))
            {
                isBlock = true;
                break;
            }
        }
        return isBlock;
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item)
    {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Gexium.MODID,"item/" + item.getId().getPath()));
    }
}
