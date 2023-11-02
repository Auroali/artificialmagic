package com.auroali.artificialmagic.common.items;

import com.auroali.artificialmagic.common.components.entity.AugmentedComponent;
import com.auroali.artificialmagic.common.components.entity.ManaComponent;
import com.auroali.artificialmagic.common.registry.AFAugmentations;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ArtificialHeartItem extends Item {
	public ArtificialHeartItem(Settings settings) {
		super(settings);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		if(AugmentedComponent.KEY.get(user).hasAugmentation(AFAugmentations.HEART))
			return super.use(world, user, hand);
		ItemStack stack = user.getStackInHand(hand);
		if(!user.isCreative())
			stack.decrement(1);

		AugmentedComponent.KEY.get(user).addAugmentation(AFAugmentations.HEART);
		ManaComponent.KEY.get(user).setCanUse(true);

		return TypedActionResult.success(stack, world.isClient);
	}
}
