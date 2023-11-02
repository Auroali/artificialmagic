package com.auroali.artificialmagic.client.render.hud;

import com.auroali.artificialmagic.ArtificialMagic;
import com.auroali.artificialmagic.client.render.ElementAnchor;
import com.auroali.artificialmagic.common.components.entity.ManaComponent;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class ManaHud extends DrawableHelper {
	public static final Identifier HUD_RES = ArtificialMagic.id("textures/gui/mana_hud.png");
	public static void render(MatrixStack stack, float tickDelta) {
		ManaComponent mana = ManaComponent.KEY.get(MinecraftClient.getInstance().player);
		if(!mana.canUse()) return;
		ElementAnchor anchor = ElementAnchor.BOTTOM_LEFT;
		RenderSystem.setShaderTexture(0, HUD_RES);

		int x = anchor.getX(MinecraftClient.getInstance().getWindow().getScaledWidth(), 4);
		int y = anchor.getY(MinecraftClient.getInstance().getWindow().getScaledHeight(), 4);
		if(anchor.isBottom())
			y -= 17;
		if(anchor.isRight()) {
			x -= 21;
		}

		float manaPercent = MathHelper.clamp((float)mana.getCurrentMana() / (float)mana.getMaxMana(), 0, 1);
		drawTexture(stack, x, y, 0, 0, 21, 17, 256, 256);
		drawTexture(stack, x, y + (int)(17 * (1 - mana.getUsableCapacity())), 21, (int)(17 * (1 - mana.getUsableCapacity())), 21, (int) (17 * mana.getUsableCapacity()), 256, 256);
		// display current mana
		drawTexture(stack, x + (anchor.isLeft() ? 22 : -50), y + 6, 0, 17, 49, 5, 256, 256);
		drawTexture(stack, x + (anchor.isLeft() ? 22 : -50), y + 6, 0, 22, (int) (49 * manaPercent), 5, 256, 256);
	}
}
