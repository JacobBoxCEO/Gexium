package net.jacobBoxCeo.gexium.datagen;

import net.jacobBoxCeo.gexium.Gexium;
import net.jacobBoxCeo.gexium.items.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
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
        simpleItem(ModItems.GEXITE);
        simpleItem(ModItems.GEXIUM);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item)
    {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Gexium.MODID,"item/" + item.getId().getPath()));
    }
}
