package com.auroali.artificialmagic.common.registry;

import com.auroali.artificialmagic.ArtificialMagic;
import net.minecraft.fluid.Fluid;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;

public class AFTags {
	public static class FluidTags {
		public static final TagKey<Fluid> MANA = TagKey.of(Registry.FLUID.getKey(), ArtificialMagic.id("mana"));
	}
}
