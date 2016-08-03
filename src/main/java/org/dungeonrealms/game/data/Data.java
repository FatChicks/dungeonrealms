package org.dungeonrealms.game.data;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import net.minecraft.server.v1_9_R1.NBTTagCompound;
import org.bukkit.craftbukkit.v1_9_R1.inventory.CraftItemStack;
import org.json.simple.JSONObject;

import java.util.Random;

/**
 * Created by Dr. Nick Doran on 5/19/2016.
 */
public class Data {

    public static void test(Player player) {
        ItemStack item = new ItemStack(Material.GOLD_AXE);
        net.minecraft.server.v1_9_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tag = new NBTTagCompound();

        JSONObject object = new JSONObject();

        object.put("existence", System.currentTimeMillis());
        object.put("name", "Vampyric Ancient Sword of Fire");

        JSONObject stats = new JSONObject();
        stats.put("damageMin", 70);
        stats.put("damageMax", 106);
        stats.put("strength", 7);
        stats.put("life_steal", 4);
        stats.put("fire_damage", 12);
        object.put("stats", stats.toJSONString());
        object.put("durability", 205);
        object.put("maxDurability", 1500);

        tag.setString("data", object.toJSONString());

        nmsStack.setTag(tag);
        player.getInventory().addItem(CraftItemStack.asBukkitCopy(nmsStack));
    }


}
