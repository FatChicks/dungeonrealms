package org.dungeonrealms.game.shop;

import org.bukkit.inventory.ItemStack;

/**
 * Created by Dr. Nick Doran on 8/6/2016.
 */
public class ShopItem {

    private ItemStack item;
    private String base64original;
    private int price;

    /**
     * @param item           The item.
     * @param base64original The original base64 of item.
     * @param price          The price the player is sellling the item for.
     */
    public ShopItem(ItemStack item, String base64original, int price) {
        this.item = item;
        this.base64original = base64original;
        this.price = price;
    }

    public ItemStack getItem() {
        return item;
    }

    public String getBase64original() {
        return base64original;
    }

    public int getPrice() {
        return price;
    }
}
