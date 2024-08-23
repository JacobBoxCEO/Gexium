package net.jacobBoxCeo.gexium.init;

import net.jacobBoxCeo.gexium.Gexium;
import net.jacobBoxCeo.gexium.blocks.BadToTheBoneBlock;
import net.jacobBoxCeo.gexium.blocks.RitualAltarBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Gexium.MOD_ID);
    public static final RegistryObject<Block> GEXIUM_BLOCK = registerBlock("gexium_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)));
    public static final RegistryObject<Block> BAD_TO_THE_BLOCK = registerBlock("evil_skeleton_block",
            () -> new BadToTheBoneBlock(BlockBehaviour.Properties.copy(Blocks.BONE_BLOCK).explosionResistance(1200)));
    public static final RegistryObject<Block> RITUAL_ALTAR = registerBlock("ritual_altar",
            () -> new RitualAltarBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
