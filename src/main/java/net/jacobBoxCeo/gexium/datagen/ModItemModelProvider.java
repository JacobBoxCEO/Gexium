package net.jacobBoxCeo.gexium.datagen;

import net.jacobBoxCeo.gexium.Gexium;
import net.jacobBoxCeo.gexium.blocks.ModBlocks;
import net.jacobBoxCeo.gexium.items.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider
{
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper)
    {
        super(output, Gexium.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels()
    {
        for(RegistryObject<Item> item : ModItems.ITEMS.getEntries())
        {
            if (checkIsBlock(item))
            {
                if(checkIsTool(item))
                {
                    toolItem(item);
                }
                else
                {
                    simpleItem(item);
                }
            }
        }
    }

    private static boolean checkIsBlock(RegistryObject<Item> item)
    {
        boolean isValid = true;
        if (item.getKey().equals(ModBlocks.BLOCKS.getRegistryKey()))
        {
            isValid = false;
        }
        return isValid;
    }
    private static boolean checkIsTool(RegistryObject<Item> item)
    {
        boolean isValid = false;
        if(item.get().canBeDepleted())
        {
            isValid = true;
        }
        return isValid;
    }
    private ItemModelBuilder toolItem(RegistryObject<Item> item)
    {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(Gexium.MODID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item)
    {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Gexium.MODID,"item/" + item.getId().getPath()));
    }
}
