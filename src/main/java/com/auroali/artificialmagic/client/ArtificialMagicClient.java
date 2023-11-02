package com.auroali.artificialmagic.client;

import com.auroali.artificialmagic.client.render.hud.ManaHud;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

public class ArtificialMagicClient implements ClientModInitializer {
	@Override
	public void onInitializeClient(ModContainer mod) {
		HudRenderCallback.EVENT.register(ManaHud::render);
	}
}
