package com.auroali.artificialmagic.common.registry;

import com.auroali.artificialmagic.ArtificialMagic;
import com.auroali.artificialmagic.common.augmentation.Augmentation;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.util.registry.Registry;

public class AFAugmentations {
	public static final Registry<Augmentation> AUGMENTATIONS = FabricRegistryBuilder
		.createSimple(Augmentation.class, ArtificialMagic.id("augmentations"))
		.buildAndRegister();

	public static final Augmentation HEART = new Augmentation();
	public static void register() {
		Registry.register(AUGMENTATIONS, ArtificialMagic.id("heart"), HEART);
	}
}
