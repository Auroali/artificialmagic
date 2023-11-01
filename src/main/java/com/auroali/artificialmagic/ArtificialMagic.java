package com.auroali.artificialmagic;

import com.auroali.artificialmagic.common.registry.AFItems;
import com.auroali.artificialmagic.common.registry.AFSounds;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArtificialMagic implements ModInitializer {
	public static final String MODID = "artificialmagic";
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("Artificial Magic");

	@Override
	public void onInitialize(ModContainer mod) {
		AFItems.register();
		AFSounds.register();
	}

	public static Identifier id(String name) {
		return new Identifier(MODID, name);
	}
}
