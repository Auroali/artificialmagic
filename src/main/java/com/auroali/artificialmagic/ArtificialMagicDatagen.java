package com.auroali.artificialmagic;

import com.auroali.artificialmagic.common.registry.AFItems;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class ArtificialMagicDatagen implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		fabricDataGenerator.addProvider(new LangGenerator(fabricDataGenerator));
	}

	private static class LangGenerator extends FabricLanguageProvider {
		protected LangGenerator(FabricDataGenerator dataGenerator) {
			super(dataGenerator);
		}

		@Override
		public void generateTranslations(TranslationBuilder tb) {
			tb.add(AFItems.ARTIFICIAL_HEART, "Artificial Heart");
			tb.add(AFItems.MANA_BOTTLE, "Mana Bottle");
			tb.add(AFItems.MANA_CRYSTAL, "Mana Crystal");
			tb.add(AFItems.WAND, "Resonator");
		}
	}
}
