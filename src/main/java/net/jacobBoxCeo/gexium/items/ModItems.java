package net.jacobBoxCeo.gexium.items;

import net.jacobBoxCeo.gexium.Gexium;
import net.minecraft.world.item.*;
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
    public static final RegistryObject<Item> GEXITE_SWORD = ITEMS.register("gexite_sword",
            () -> new SwordItem(ModToolTiers.GEXITE, 4, 2, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> GEXITE_PICKAXE = ITEMS.register("gexite_pickaxe",
            () -> new PickaxeItem(ModToolTiers.GEXITE, 4, 2, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> GEXITE_AXE = ITEMS.register("gexite_axe",
            () -> new AxeItem(ModToolTiers.GEXITE, 4, 2, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> GEXITE_SHOVEL = ITEMS.register("gexite_shovel",
            () -> new ShovelItem(ModToolTiers.GEXITE, 4, 2, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> GEXITE_HOE = ITEMS.register("gexite_hoe",
            () -> new HoeItem(ModToolTiers.GEXITE, 4, 2, new Item.Properties().fireResistant()));

    public static void  register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
