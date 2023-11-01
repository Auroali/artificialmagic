package com.auroali.artificialmagic.common.registry;

import com.auroali.artificialmagic.ArtificialMagic;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.registry.Registry;

public class AFSounds {
	public static final SoundEvent TUNING_FORK = new SoundEvent(ArtificialMagic.id("tuning_fork"));

	public static void register() {
		Registry.register(Registry.SOUND_EVENT, TUNING_FORK.getId(), TUNING_FORK);
	}
}
