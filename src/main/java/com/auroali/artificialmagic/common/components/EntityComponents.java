package com.auroali.artificialmagic.components;

import com.auroali.artificialmagic.components.entity.ManaComponent;
import com.auroali.artificialmagic.common.mana.PlayerManaComponent;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;

public class EntityComponents implements EntityComponentInitializer {
	@Override
	public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
		registry.registerForPlayers(ManaComponent.KEY, PlayerManaComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
	}
}
