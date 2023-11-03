package com.auroali.artificialmagic.client;

import com.auroali.artificialmagic.ArtificialMagic;
import com.auroali.artificialmagic.client.render.hud.ManaHud;
import com.auroali.artificialmagic.common.registry.AFFluids;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.fabric.mixin.client.texture.SpriteAtlasTextureMixin;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.texture.SpriteAtlasTexture;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.block.extensions.api.client.BlockRenderLayerMap;

public class ArtificialMagicClient implements ClientModInitializer {
	@Override
	public void onInitializeClient(ModContainer mod) {
		HudRenderCallback.EVENT.register(ManaHud::render);
		FluidRenderHandlerRegistry.INSTANCE.register(AFFluids.MANA_STILL, AFFluids.MANA_FLOWING, new SimpleFluidRenderHandler(
			ArtificialMagic.id("block/mana"),
			ArtificialMagic.id("block/mana_flowing")
		));

		BlockRenderLayerMap.put(RenderLayer.getTranslucent(), AFFluids.MANA_STILL, AFFluids.MANA_FLOWING);

		// Register the flowing mana texture
		ClientSpriteRegistryCallback.event(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE).register((atlasTexture, registry) -> {
			registry.register(ArtificialMagic.id("block/mana_flowing"));
		});
	}
}
