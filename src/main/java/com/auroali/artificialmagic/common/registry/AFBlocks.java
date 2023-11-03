package com.auroali.artificialmagic.common.registry;

import com.auroali.artificialmagic.ArtificialMagic;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.registry.Registry;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;

public class AFBlocks {
	public static final FluidBlock MANA = new FluidBlock(AFFluids.MANA_STILL, QuiltBlockSettings.copy(Blocks.WATER));
	public static void register() {
		Registry.register(Registry.BLOCK, ArtificialMagic.id("mana"), MANA);
	}
}
