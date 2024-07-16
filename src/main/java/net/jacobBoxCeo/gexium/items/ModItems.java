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

    public static final RegistryObject<Item> GEXIUM = ITEMS.register("gexium_ingot",
            () -> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> GEXIUM_SWORD = ITEMS.register("gexium_sword",
            () -> new SwordItem(ModToolTiers.GEXIUM, 4, -2.4f, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> GEXIUM_PICKAXE = ITEMS.register("gexium_pickaxe",
            () -> new PickaxeItem(ModToolTiers.GEXIUM, 2, -2.8f, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> GEXIUM_AXE = ITEMS.register("gexium_axe",
            () -> new AxeItem(ModToolTiers.GEXIUM,6 , -3, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> GEXIUM_SHOVEL = ITEMS.register("gexium_shovel",
            () -> new ShovelItem(ModToolTiers.GEXIUM, 2.5f, -3, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> GEXIUM_HOE = ITEMS.register("gexium_hoe",
            () -> new HoeItem(ModToolTiers.GEXIUM, -6, 0, new Item.Properties().fireResistant()));

    public static void  register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
