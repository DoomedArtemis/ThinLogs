package de.artemis.thinlogs.common.data;

import de.artemis.thinlogs.ThinLogs;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ThinLogs.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataProvider {

    @SubscribeEvent
    public static void onDataGen(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(true, new ModelAndBlockStateProvider(generator, existingFileHelper));
        generator.addProvider(true, new ItemModelProvider(generator, existingFileHelper));
        generator.addProvider(true, new LanguageProvider(generator, "en_us"));
        generator.addProvider(true, new BlockLootTablesProvider(generator));
        generator.addProvider(true, new TagsProvider.ItemTagsProvider(generator, existingFileHelper));
        generator.addProvider(true, new TagsProvider.BlockTagsProvider(generator, existingFileHelper));
        generator.addProvider(true, new RecipesProvider(generator));
    }

    public static String getRegistryName(Item item) {
        return item.builtInRegistryHolder().key().location().toString();
    }

}
