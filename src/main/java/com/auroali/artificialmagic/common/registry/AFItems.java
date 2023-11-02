package com.auroali.artificialmagic.common.registry;

import com.auroali.artificialmagic.ArtificialMagic;
import com.auroali.artificialmagic.common.items.ArtificialHeartItem;
import com.auroali.artificialmagic.common.items.ManaBottle;
import com.auroali.artificialmagic.common.items.WandItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class AFItems {
	public static final WandItem WAND = new WandItem(new Item.Settings().maxCount(1));
	public static final Item MANA_CRYSTAL = new Item(new Item.Settings());
	public static final ArtificialHeartItem ARTIFICIAL_HEART = new ArtificialHeartItem(new Item.Settings());
	public static final ManaBottle MANA_BOTTLE = new ManaBottle(new Item.Settings().maxCount(16));

	public static void register() {
		Registry.register(Registry.ITEM, ArtificialMagic.id("wand"), WAND);
		Registry.register(Registry.ITEM, ArtificialMagic.id("mana_crystal"), MANA_CRYSTAL);
		Registry.register(Registry.ITEM, ArtificialMagic.id("artificial_heart"), ARTIFICIAL_HEART);
		Registry.register(Registry.ITEM, ArtificialMagic.id("mana_bottle"), MANA_BOTTLE);
	}
}
