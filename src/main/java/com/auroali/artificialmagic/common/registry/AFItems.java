package com.auroali.artificialmagic.common.registry;

import com.auroali.artificialmagic.ArtificialMagic;
import com.auroali.artificialmagic.common.items.ArtificialHeartItem;
import com.auroali.artificialmagic.common.items.ManaBottle;
import com.auroali.artificialmagic.common.items.WandItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class AFItems {
	public static final WandItem WAND = new WandItem(new QuiltItemSettings().maxCount(1));
	public static final Item MANA_CRYSTAL = new Item(new QuiltItemSettings());
	public static final ArtificialHeartItem ARTIFICIAL_HEART = new ArtificialHeartItem(new QuiltItemSettings());
	public static final ManaBottle MANA_BOTTLE = new ManaBottle(new QuiltItemSettings().maxCount(16));
	public static final BucketItem MANA_BUCKET = new BucketItem(AFFluids.MANA_STILL, new QuiltItemSettings().maxCount(1));
	public static void register() {
		Registry.register(Registry.ITEM, ArtificialMagic.id("wand"), WAND);
		Registry.register(Registry.ITEM, ArtificialMagic.id("mana_crystal"), MANA_CRYSTAL);
		Registry.register(Registry.ITEM, ArtificialMagic.id("artificial_heart"), ARTIFICIAL_HEART);
		Registry.register(Registry.ITEM, ArtificialMagic.id("mana_bottle"), MANA_BOTTLE);
		Registry.register(Registry.ITEM, ArtificialMagic.id("mana_bucket"), MANA_BUCKET);
	}
}
