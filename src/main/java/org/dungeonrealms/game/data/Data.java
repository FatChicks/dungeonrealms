package org.dungeonrealms.game.data;

import com.comphenix.protocol.wrappers.nbt.NbtCompound;
import com.comphenix.protocol.wrappers.nbt.NbtFactory;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_9_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Dr. Nick Doran on 5/19/2016.
 */
public class Data {

    public static void test(Player player) {
        ItemStack item = new ItemStack(Material.MELON_SEEDS);

        CraftItemStack craftStack = CraftItemStack.asCraftCopy(item);
        NbtCompound compound = NbtFactory.asCompound(NbtFactory.fromItemTag(craftStack));

        compound.put("hi", 222);

        System.out.println(compound.getInteger("hi"));

        player.getInventory().addItem(craftStack);

    }


}
