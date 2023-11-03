package com.auroali.artificialmagic;

import com.auroali.artificialmagic.common.components.entity.AugmentedComponent;
import com.auroali.artificialmagic.common.components.entity.ManaComponent;
import com.auroali.artificialmagic.common.mana.DrainType;
import com.auroali.artificialmagic.common.registry.*;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.command.api.CommandRegistrationCallback;
import org.quiltmc.qsl.lifecycle.api.event.ServerWorldTickEvents;
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
		AFAugmentations.register();
		AFFluids.register();
		AFBlocks.register();

		ServerWorldTickEvents.START.register((server, world) ->
			world.getPlayers().forEach(player -> {
				if(ManaComponent.KEY.get(player).canUse())
					player.sendMessage(Text.of("%f/%f".formatted(
						ManaComponent.KEY.get(player).getCurrentMana(),
						ManaComponent.KEY.get(player).getMaxMana()
					)), true);
				}
			)
		);
		CommandRegistrationCallback.EVENT.register((dispatcher, buildContext, environment) ->
			dispatcher.register(CommandManager.literal("mana")
				.then(CommandManager.literal("refill").executes(ctx -> {
					if (!ctx.getSource().m_bjpnvosg()) return -1;
					ManaComponent.KEY.get(ctx.getSource().getPlayer()).fillMana(1, DrainType.PERCENT);
					return 0;
				}))
				.then(CommandManager.literal("reset").executes(ctx -> {
					if (!ctx.getSource().m_bjpnvosg()) return -1;
					ManaComponent.KEY.get(ctx.getSource().getPlayer()).fillMana(1, DrainType.PERCENT);
					ManaComponent.KEY.get(ctx.getSource().getPlayer()).setCanUse(false);
					AugmentedComponent.KEY.get(ctx.getSource()).augmentations().clear();
					AugmentedComponent.KEY.sync(ctx.getSource());
					return 0;
				}))
			)
		);
	}

	public static Identifier id(String name) {
		return new Identifier(MODID, name);
	}
}
