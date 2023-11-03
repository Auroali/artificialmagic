package com.auroali.artificialmagic.mixin;

import com.auroali.artificialmagic.common.registry.AFFluids;
import com.auroali.artificialmagic.common.registry.AFItems;
import com.auroali.artificialmagic.common.registry.AFTags;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.GlassBottleItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(GlassBottleItem.class)
public abstract class GlassBottleItemMixin {
	@Shadow
	protected abstract ItemStack fill(ItemStack stack, PlayerEntity player, ItemStack outputStack);

	@Inject(
		method = "use",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/World;getFluidState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/fluid/FluidState;",
			shift = At.Shift.BEFORE
		),
		locals = LocalCapture.CAPTURE_FAILEXCEPTION,
		cancellable = true
	)
	public void injectManaBottleUse(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir, List list, ItemStack itemStack, HitResult hitResult, BlockPos blockPos) {
		if(world.getFluidState(blockPos).isIn(AFTags.FluidTags.MANA)) {
			world.playSound(user, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0F, 1.0F);
			world.emitGameEvent(user, GameEvent.FLUID_PICKUP, blockPos);
			cir.setReturnValue(TypedActionResult.success(fill(itemStack, user, new ItemStack(AFItems.MANA_BOTTLE))));
		}
	}
}
