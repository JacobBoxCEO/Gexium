package net.jacobBoxCeo.firstModYippee.items;

import net.jacobBoxCeo.firstModYippee.FirstModYippee;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, FirstModYippee.MODID);

    public static final RegistryObject<Item> GEXITE = ITEMS.register("gexite_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GEXITE_SCRAP = ITEMS.register("gexite_scrap",
            () -> new Item(new Item.Properties()));

    public static void  register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
