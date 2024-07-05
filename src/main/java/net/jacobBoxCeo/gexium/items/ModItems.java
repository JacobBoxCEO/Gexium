package net.jacobBoxCeo.gexium.items;

import net.jacobBoxCeo.gexium.Gexium;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Gexium.MODID);

    public static final RegistryObject<Item> GEXITE = ITEMS.register("gexite_ingot",
            () -> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> GEXIUM = ITEMS.register("gexium_ingot",
            () -> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> SILLIUM = ITEMS.register("sillium_crystal",
            () -> new Item(new Item.Properties()));

    public static void  register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
