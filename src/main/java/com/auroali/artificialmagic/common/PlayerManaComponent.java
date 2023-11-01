package com.auroali.artificialmagic.common.mana;

import com.auroali.artificialmagic.components.entity.ManaComponent;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;

public class PlayerManaComponent implements ManaComponent, AutoSyncedComponent {
	final PlayerEntity holder;
	double mana = 1000;
	double maxMana = 1000;
	public PlayerManaComponent(PlayerEntity player) {
		this.holder = player;
	}

	@Override
	public void readFromNbt(NbtCompound tag) {
		maxMana = tag.getDouble("MaxMana");
		mana = tag.getDouble("Mana");
	}

	@Override
	public void writeToNbt(NbtCompound tag) {
		tag.putDouble("MaxMana", maxMana);
		tag.putDouble("Mana", mana);
	}

	@Override
	public boolean drainMana(double amount, com.auroali.artificialmagic.common.mana.DrainType drainType, boolean simulate) {
		double manaToDrain = switch (drainType) {
			case PERCENT -> amount * getAbsoluteMaxMana();
			case FIXED_AMOUNT -> amount;
		};
		double newAmount = mana - manaToDrain;
		if(!simulate)
			mana = Math.max(0, newAmount);
		return newAmount > 0;
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
		return Math.max(0, Math.min(getMaxMana(), mana) - (getAbsoluteMaxMana() - mana));
	}

	@Override
	public double getUsableCapacity() {
		return 0.25;
	}
}
