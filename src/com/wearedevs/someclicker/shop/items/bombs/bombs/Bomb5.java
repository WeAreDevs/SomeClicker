package com.wearedevs.someclicker.shop.items.bombs.bombs;

import com.wearedevs.someclicker.BaseMod;
import com.wearedevs.someclicker.handlers.ShopHandler;
import com.wearedevs.someclicker.shop.ShopItem;

public class Bomb5 extends ShopItem {

	public void onPurchase() {
		BaseMod.clickBomb = 5000;
		
		ShopHandler.unlock(new Bomb6());
	}

	public int getPrice() {
		return 5000;
	}

	public String getName() {
		return "Click Bomb: Tier 5";
	}

}
