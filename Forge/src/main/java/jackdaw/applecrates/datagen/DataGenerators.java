package jackdaw.applecrates.datagen;

import jackdaw.applecrates.AppleCrates;
import jackdaw.applecrates.api.datagen.*;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = AppleCrates.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        generatedCrates(AppleCrates.MODID, event);
    }

    public static void generatedCrates(String modid, GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        if (event.includeServer()) {
            //datapack  server
            generator.addProvider(new CrateTag(generator, event.getExistingFileHelper()));
            generator.addProvider(new CrateRecipes(generator));
            generator.addProvider(new CrateLoot(generator));
        }

        if (event.includeClient()) {
            //resourcepack  client
            generator.addProvider(new CrateModels(generator, event.getExistingFileHelper()));
            generator.addProvider(new CrateStates(generator, event.getExistingFileHelper()));
            generator.addProvider(new CrateItems(generator, event.getExistingFileHelper()));
            generator.addProvider(new CrateLanguage(modid, generator, "en_uk"));
            generator.addProvider(new CrateLanguage(modid, generator, "en_us"));
            generator.addProvider(new CrateLanguage(modid, generator, "fr_fr"));
            generator.addProvider(new CrateLanguage(modid, generator, "de_de"));
            generator.addProvider(new CrateLanguage(modid, generator, "en_ca"));
            generator.addProvider(new CrateLanguage(modid, generator, "fr_ca"));
        }

    }
}
