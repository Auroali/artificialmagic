package com.auroali.artificialmagic.common.items;

import com.auroali.artificialmagic.common.registry.AFSounds;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class WandItem extends Item {
	public WandItem(Settings settings) {
		super(settings);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		if(!user.world.isClient)
			world.playSound(null, user.getX(), user.getY(), user.getZ(), AFSounds.TUNING_FORK, SoundCategory.PLAYERS, 0.5f, 1f);
		return super.use(world, user, hand);
	}

	@Override
	public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		attacker.world.playSound(null, attacker.getX(), attacker.getY(), attacker.getZ(), AFSounds.TUNING_FORK, SoundCategory.PLAYERS, 0.5f, 1f);
		return super.postHit(stack, target, attacker);
	}
}
