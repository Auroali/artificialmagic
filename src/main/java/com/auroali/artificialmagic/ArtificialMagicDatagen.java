package com.auroali.artificialmagic;

import com.auroali.artificialmagic.common.registry.AFBlocks;
import com.auroali.artificialmagic.common.registry.AFFluids;
import com.auroali.artificialmagic.common.registry.AFItems;
import com.auroali.artificialmagic.common.registry.AFTags;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.fluid.Fluid;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.registry.Registry;

public class ArtificialMagicDatagen implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		fabricDataGenerator.addProvider(new LangGenerator(fabricDataGenerator));
		fabricDataGenerator.addProvider(new FluidTag(fabricDataGenerator, Registry.FLUID));
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
			tb.add(AFItems.MANA_BUCKET, "Mana Bucket");
			tb.add(AFBlocks.MANA, "Mana");
		}
	}
	private static class FluidTag extends FabricTagProvider<Fluid> {
		public FluidTag(FabricDataGenerator dataGenerator, Registry<Fluid> registry) {
			super(dataGenerator, registry);
		}

		@Override
		protected void generateTags() {
			getOrCreateTagBuilder(FluidTags.WATER)
				.add(AFFluids.MANA_STILL)
				.add(AFFluids.MANA_FLOWING);
			getOrCreateTagBuilder(AFTags.FluidTags.MANA)
				.add(AFFluids.MANA_STILL)
				.add(AFFluids.MANA_FLOWING);
		}
	}
}
