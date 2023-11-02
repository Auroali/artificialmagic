package com.auroali.artificialmagic.common.components.entity;

import com.auroali.artificialmagic.ArtificialMagic;
import com.auroali.artificialmagic.common.augmentation.Augmentation;
import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;

import java.util.Collection;

public interface AugmentedComponent extends Component {
	ComponentKey<AugmentedComponent> KEY = ComponentRegistry.getOrCreate(ArtificialMagic.id("augmentations"), AugmentedComponent.class);
	Collection<Augmentation> augmentations();
	boolean hasAugmentation(Augmentation augmentation);
	void addAugmentation(Augmentation augmentation);
}
