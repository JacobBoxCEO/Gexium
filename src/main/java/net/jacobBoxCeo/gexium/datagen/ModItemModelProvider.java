package net.jacobBoxCeo.gexium.datagen;

import net.jacobBoxCeo.gexium.Gexium;
import net.jacobBoxCeo.gexium.init.ModBlocks;
import net.jacobBoxCeo.gexium.init.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Gexium.MODID, existingFileHelper);
    }

    private static boolean checkIsBlock(RegistryObject<Item> item) {
        for (RegistryObject<Block> block : ModBlocks.BLOCKS.getEntries())
            if (item.equals(block)) return true;
        return false;
    }

    private static boolean checkIsTool(RegistryObject<Item> item) {
        if (item.get().canBeDepleted() && new ItemStack(item.get()).getEquipmentSlot() == null) {
            return true;
        }
        return false;
    }

    @Override
    protected void registerModels() {
        for (RegistryObject<Item> item : ModItems.ITEMS.getEntries()) {
            if (!checkIsBlock(item)) {
                if (checkIsTool(item))
                    toolItem(item);
                else
                    simpleItem(item);
            }
        }
    }

    private ItemModelBuilder toolItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(Gexium.MODID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Gexium.MODID, "item/" + item.getId().getPath()));
    }
}
