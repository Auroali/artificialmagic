package com.auroali.artificialmagic.common.mana;

import com.auroali.artificialmagic.common.components.entity.ManaComponent;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;

public class PlayerManaComponent implements ManaComponent, AutoSyncedComponent {
	final PlayerEntity holder;
	double mana = 1000;
	double maxMana = 1000;
	boolean canUse;
	public PlayerManaComponent(PlayerEntity player) {
		this.holder = player;
	}

	@Override
	public void readFromNbt(NbtCompound tag) {
		maxMana = tag.getDouble("MaxMana");
		mana = tag.getDouble("Mana");
		canUse = tag.getBoolean("CanUse");
	}

	@Override
	public void writeToNbt(NbtCompound tag) {
		tag.putDouble("MaxMana", maxMana);
		tag.putDouble("Mana", mana);
		tag.putBoolean("CanUse", canUse);
	}

	@Override
	public boolean drainMana(double amount, com.auroali.artificialmagic.common.mana.DrainType drainType, boolean simulate) {
		double manaToDrain = switch (drainType) {
			case PERCENT -> amount * getAbsoluteMaxMana();
			case FIXED_AMOUNT -> amount;
		};
		double newAmount = mana - manaToDrain;
		if(!simulate) {
			mana = MathHelper.clamp(newAmount, 0, getAbsoluteMaxMana());
			ManaComponent.KEY.sync(holder);
		}
		return newAmount >= getAbsoluteMaxMana() - getMaxMana();
	}

	@Override
	public void fillMana(double amount, DrainType drainType) {
		this.drainMana(-amount, drainType, false);
	}

	@Override
	public double getMaxMana() {
		return maxMana * getUsableCapacity();
	}

	@Override
	public double getAbsoluteMaxMana() {
		return maxMana;
	}

	@Override
	public double getCurrentMana() {
		return Math.max(0, getMaxMana() - (getAbsoluteMaxMana() - mana));
	}

	@Override
	public double getUsableCapacity() {
		return 1;
	}

	@Override
	public boolean canUse() {
		return canUse;
	}

	@Override
	public void setCanUse(boolean canUse) {
		this.canUse = canUse;
		ManaComponent.KEY.sync(holder);
	}
}
