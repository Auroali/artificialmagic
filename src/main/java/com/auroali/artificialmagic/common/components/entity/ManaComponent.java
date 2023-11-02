package com.auroali.artificialmagic.common.components.entity;

import com.auroali.artificialmagic.ArtificialMagic;
import com.auroali.artificialmagic.common.mana.DrainType;
import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;

public interface ManaComponent extends Component {
	ComponentKey<ManaComponent> KEY = ComponentRegistry.getOrCreate(ArtificialMagic.id("mana"), ManaComponent.class);
	boolean drainMana(double amount, DrainType drainType, boolean simulate);
	default boolean drainMana(double amount, DrainType drainType) {
		return this.drainMana(amount, drainType, false);
	}
	void fillMana(double amount, DrainType drainType);
	/**
	 * Get the max amount of mana that can be held
	 * @return the max amount of mana that can be held, accounting for the usable capacity
	 */
	double getMaxMana();
	/**
	 * Get the max amount of mana that can be held
	 * @return the max amount of mana that can be held
	 */
	double getAbsoluteMaxMana();
	/**
	 * Get the amount of mana remaining
	 * @return the remaining mana amount, accounting for the usable capacity
	 */
	double getCurrentMana();
	/**
	 * Gets how much of the max mana capacity is usable
	 * @return the percent usable capacity
	 */
	double getUsableCapacity();

	boolean canUse();
	void setCanUse(boolean canUse);
}
