package org.dungeonrealms.api.inventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.dungeonrealms.DungeonRealms;

/**
 * Created by Dr. Nick Doran on 8/6/2016.
 */
public abstract class Menu implements Listener {

    private Player player;
    private String name;
    private int size;
    private Inventory inventory;

    /**
     * @param name The inventories name.
     * @param size The inventories size.
     */
    public Menu(Player player, String name, int size) {
        this.player = player;
        this.name = name;
        this.size = size;
        this.inventory = Bukkit.createInventory(getPlayer(), size, name);
        Bukkit.getServer().getPluginManager().registerEvents(this, DungeonRealms.getInstance());
    }

    public Player getPlayer() {
        return player;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    private Inventory getInventory() {
        return inventory;
    }

    /**
     * @param slot      The slot the item's being set to.
     * @param itemStack The ItemStack itself.
     * @return The return of this object. (Builder)
     */
    public Menu setItem(int slot, ItemStack itemStack) {
        getInventory().setItem(slot, itemStack);
        return this;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getInventory().getTitle().equals(getName())) return;
        if (!(event.getWhoClicked() instanceof Player)) return;
        if (event.getRawSlot() > event.getInventory().getSize()) return;
        if (!event.getWhoClicked().getName().equalsIgnoreCase(getPlayer().getName())) return;
        event.setCancelled(true);
        onClick(event, event.getRawSlot());
    }

    @SuppressWarnings("AccessStaticViaInstance")
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (!event.getInventory().getTitle().equals(getName())) return;
        if (!event.getPlayer().getName().equalsIgnoreCase(getPlayer().getName())) return;
        event.getHandlers().unregisterAll(this);
    }


    @EventHandler
    abstract void onClick(InventoryClickEvent event, int slot);

    /**
     * @param player The player
     */
    public void openFor(Player player) {
        player.openInventory(getInventory());
    }

}
