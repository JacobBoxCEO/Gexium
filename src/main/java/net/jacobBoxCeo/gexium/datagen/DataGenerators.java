package net.jacobBoxCeo.gexium.datagen;

import com.google.common.eventbus.Subscribe;
import net.jacobBoxCeo.gexium.Gexium;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = Gexium.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators
{
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event)
    {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new ModRecipesGen(packOutput));
        generator.addProvider(event.includeServer(), ModLootTableGen.create(packOutput));

        generator.addProvider(event.includeClient(), new ModBlockStateGen(packOutput, fileHelper));
        generator.addProvider(event.includeClient(), new ModItemModelGen(packOutput, fileHelper));

        ModBlockTagGen blockTagGen = generator.addProvider(event.includeServer(),
                new ModBlockTagGen(packOutput, lookupProvider, fileHelper));
        generator.addProvider(event.includeServer(), new ModItemTagGen(packOutput, lookupProvider, blockTagGen.contentsGetter(), fileHelper));

    }
}
