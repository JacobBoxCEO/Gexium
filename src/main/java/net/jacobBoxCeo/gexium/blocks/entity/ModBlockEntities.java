package net.jacobBoxCeo.gexium.blocks.entity;

import net.jacobBoxCeo.gexium.Gexium;
import net.jacobBoxCeo.gexium.init.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Gexium.MOD_ID);

    public static final RegistryObject<BlockEntityType<RitualAltarBlockEntity>> RITUAL_ALTAR_BE =
            BLOCK_ENTITIES.register("ritual_altar_be", () ->
                    BlockEntityType.Builder.of(RitualAltarBlockEntity::new,
                            ModBlocks.RITUAL_ALTAR.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
