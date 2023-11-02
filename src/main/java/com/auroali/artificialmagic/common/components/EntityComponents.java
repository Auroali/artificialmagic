package com.auroali.artificialmagic.common.components;

import com.auroali.artificialmagic.common.augmentation.PlayerAugmentedComponent;
import com.auroali.artificialmagic.common.components.entity.AugmentedComponent;
import com.auroali.artificialmagic.common.components.entity.ManaComponent;
import com.auroali.artificialmagic.common.mana.PlayerManaComponent;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;

public class EntityComponents implements EntityComponentInitializer {
	@Override
	public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
		registry.registerForPlayers(ManaComponent.KEY, PlayerManaComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
		registry.registerForPlayers(AugmentedComponent.KEY, PlayerAugmentedComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
	}
}
