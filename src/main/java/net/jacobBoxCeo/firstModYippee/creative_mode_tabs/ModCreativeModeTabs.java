package net.jacobBoxCeo.firstModYippee.creative_mode_tabs;

import net.jacobBoxCeo.firstModYippee.Gexium;
import net.jacobBoxCeo.firstModYippee.blocks.ModBlocks;
import net.jacobBoxCeo.firstModYippee.items.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs
{
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Gexium.MODID);
    public static final RegistryObject<CreativeModeTab> SILLY_MOD_TAB = CREATIVE_MODE_TABS.register("silly_mod_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.GEXITE.get()))
                    .title(Component.translatable("creativetab.silly_mod_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.GEXITE.get());
                        output.accept(ModBlocks.GEXITE_BLOCK.get());
                        output.accept(ModItems.GEXIUM.get());
                        output.accept(ModBlocks.GEXIUM_BLOCK.get());
                        output.accept(ModItems.SILLIUM.get());
                    })
                    .build());
    public static void register(IEventBus eventBus)
    {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
