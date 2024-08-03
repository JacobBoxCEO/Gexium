package net.jacobBoxCeo.gexium.datagen;

import net.jacobBoxCeo.gexium.Gexium;
import net.jacobBoxCeo.gexium.init.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGen extends ItemTagsProvider {

    public ModItemTagGen(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_,
                         CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, Gexium.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.GEXIUM_HELMET.get(),
                        ModItems.GEXIUM_CHESTPLATE.get(),
                        ModItems.GEXIUM_LEGGINGS.get(),
                        ModItems.GEXIUM_BOOTS.get());
    }
}
