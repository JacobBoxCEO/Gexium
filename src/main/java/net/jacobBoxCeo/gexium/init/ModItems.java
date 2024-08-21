package net.jacobBoxCeo.gexium.init;

import net.jacobBoxCeo.gexium.Gexium;
import net.jacobBoxCeo.gexium.items.ModArmorMaterial;
import net.jacobBoxCeo.gexium.items.ModToolTiers;
import net.jacobBoxCeo.gexium.items.ModUpgradeTemplateItem;
import net.jacobBoxCeo.gexium.items.RitualDaggerItem;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Gexium.MOD_ID);

    public static final RegistryObject<Item> GEXIUM = ITEMS.register("gexium_ingot",
            () -> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> GEXIUM_SWORD = ITEMS.register("gexium_sword",
            () -> new SwordItem(ModToolTiers.GEXIUM, 4, -2.4f,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> GEXIUM_PICKAXE = ITEMS.register("gexium_pickaxe",
            () -> new PickaxeItem(ModToolTiers.GEXIUM, 2, -2.8f,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> GEXIUM_AXE = ITEMS.register("gexium_axe",
            () -> new AxeItem(ModToolTiers.GEXIUM, 6, -3,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> GEXIUM_SHOVEL = ITEMS.register("gexium_shovel",
            () -> new ShovelItem(ModToolTiers.GEXIUM, 2.5f, -3,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> GEXIUM_HOE = ITEMS.register("gexium_hoe",
            () -> new HoeItem(ModToolTiers.GEXIUM, -6, 0,
                    new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> GEXIUM_HELMET = ITEMS.register("gexium_helmet",
            () -> new ArmorItem(ModArmorMaterial.GEXIUM, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> GEXIUM_CHESTPLATE = ITEMS.register("gexium_chestplate",
            () -> new ArmorItem(ModArmorMaterial.GEXIUM, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> GEXIUM_LEGGINGS = ITEMS.register("gexium_leggings",
            () -> new ArmorItem(ModArmorMaterial.GEXIUM, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> GEXIUM_BOOTS = ITEMS.register("gexium_boots",
            () -> new ArmorItem(ModArmorMaterial.GEXIUM, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> RITUAL_DAGGER = ITEMS.register("ritual_dagger",
            () -> new RitualDaggerItem(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> GEXIUM_UPGRADE_TEMPLATE = ITEMS.register("gexium_upgrade_smithing_template",
            ModUpgradeTemplateItem::createGexiumUpgrade);
    public static final RegistryObject<Item> GEX_DISC = ITEMS.register("gex_music_disc",
        () -> new RecordItem(15, ModSounds.GEX, new Item.Properties().stacksTo(1).rarity(Rarity.EPIC), 137*20));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
