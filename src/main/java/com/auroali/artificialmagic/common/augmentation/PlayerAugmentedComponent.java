package com.auroali.artificialmagic.common.augmentation;

import com.auroali.artificialmagic.ArtificialMagic;
import com.auroali.artificialmagic.common.components.entity.AugmentedComponent;
import com.auroali.artificialmagic.common.registry.AFAugmentations;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.Collection;

public class PlayerAugmentedComponent implements AugmentedComponent, AutoSyncedComponent, ServerTickingComponent {
	ObjectOpenHashSet<Augmentation> augmentations = new ObjectOpenHashSet<>();
	PlayerEntity holder;

	public PlayerAugmentedComponent(PlayerEntity entity) {
		this.holder = entity;
	}

	@Override
	public Collection<Augmentation> augmentations() {
		return augmentations;
	}

	@Override
	public boolean hasAugmentation(Augmentation augmentation) {
		return augmentations.contains(augmentation);
	}

	@Override
	public void addAugmentation(Augmentation augmentation) {
		if(hasAugmentation(augmentation))
			return;
		augmentations.add(augmentation);
		AugmentedComponent.KEY.sync(holder);
	}

	@Override
	public void writeSyncPacket(PacketByteBuf buf, ServerPlayerEntity recipient) {
		augmentations.forEach(aug -> buf.writeId(AFAugmentations.AUGMENTATIONS, aug));
	}

	@Override
	public void applySyncPacket(PacketByteBuf buf) {
		augmentations.clear();
		while(buf.isReadable()) {
			Augmentation augmentation = buf.readById(AFAugmentations.AUGMENTATIONS);
			if(augmentation == null) {
				ArtificialMagic.LOGGER.warn("Could not read augmentation from sync packet!");
				continue;
			}
			augmentations.add(augmentation);
		}
	}

	@Override
	public void readFromNbt(NbtCompound tag) {
		NbtList list = tag.getList("Augmentations", NbtElement.STRING_TYPE);
		for(int i = 0; i < list.size(); i++) {
			Identifier identifier = Identifier.tryParse(list.getString(i));
			Augmentation augmentation;
			if(identifier == null || (augmentation = AFAugmentations.AUGMENTATIONS.get(identifier)) == null) {
				ArtificialMagic.LOGGER.error("Unable to get augmentation entry for key {}! This entry will be skipped", identifier);
				continue;
			}
			augmentations.add(augmentation);
		}
	}

	@Override
	public void writeToNbt(NbtCompound tag) {
		NbtList list = new NbtList();
		augmentations.forEach(aug -> list.add(
			NbtString.of(AFAugmentations.AUGMENTATIONS.getId(aug).toString())
		));
		tag.put("Augmentations", list);
	}

	@Override
	public void serverTick() {
		augmentations.forEach(aug -> aug.tick(holder));
	}
}
