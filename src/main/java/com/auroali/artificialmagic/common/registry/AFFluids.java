package com.auroali.artificialmagic.common.registry;

import com.auroali.artificialmagic.ArtificialMagic;
import com.auroali.artificialmagic.common.fluids.ManaFluid;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.registry.Registry;

public class AFFluids {

	public static final ManaFluid MANA_STILL = new ManaFluid.Still();
	public static final ManaFluid MANA_FLOWING = new ManaFluid.Flowing();
	public static void register() {
		Registry.register(Registry.FLUID, ArtificialMagic.id("mana"), MANA_STILL);
		Registry.register(Registry.FLUID, ArtificialMagic.id("flowing_mana"), MANA_FLOWING);
	}
}
