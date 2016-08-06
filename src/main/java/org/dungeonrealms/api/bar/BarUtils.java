package org.dungeonrealms.api.bar;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.dungeonrealms.DungeonRealms;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Dr. Nick Doran on 5/20/2016.
 */
public class BarUtils {

    //TODO: Remove this class, Bar should be localized in the player object. Only testing.

    public static void init() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(DungeonRealms.getInstance(), () -> {

            Bukkit.getOnlinePlayers().forEach(player -> {
                sendActionBar(player, ChatColor.AQUA.toString() + ChatColor.BOLD + "LVL 1" +
                        ChatColor.BLACK + " - " +
                        ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "HP 100/100"
                        + ChatColor.BLACK + " - " + ChatColor.GREEN.toString() + ChatColor.BOLD + "XP 5%"
                );
            });

        }, 20, 0);
    }

    public static void sendNotification(Player player) {
        BossBar bar = Bukkit.createBossBar(
                ChatColor.GREEN.toString() + ChatColor.BOLD + "Friend Request From" +
                        ChatColor.GRAY + ": " + ChatColor.AQUA.toString() + ChatColor.BOLD + "Notch", BarColor.GREEN, BarStyle.SOLID);

        bar.addPlayer(player);
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1f, 63f);

        Bukkit.getScheduler().scheduleSyncDelayedTask(DungeonRealms.getInstance(), bar::removeAll, 40);
    }

    /**
     * @param player  The player.
     * @param message The message you're sending to him/her.
     */
    private static void sendActionBar(Player player, String message) {
        try {
            Class<?> c1 = Class.forName("org.bukkit.craftbukkit." + DungeonRealms.getInstance().getNmsVers() + ".entity.CraftPlayer");
            Object p = c1.cast(player);
            Object ppoc;
            Class<?> c4 = Class.forName("net.minecraft.server." + DungeonRealms.getInstance().getNmsVers() + ".PacketPlayOutChat");
            Class<?> c5 = Class.forName("net.minecraft.server." + DungeonRealms.getInstance().getNmsVers() + ".Packet");
            if ((DungeonRealms.getInstance().getNmsVers().equalsIgnoreCase("v1_8_R1") || !DungeonRealms.getInstance().getNmsVers().startsWith("v1_8_")) && !DungeonRealms.getInstance().getNmsVers().startsWith("v1_9_")) {
                Class<?> c2 = Class.forName("net.minecraft.server." + DungeonRealms.getInstance().getNmsVers() + ".ChatSerializer");
                Class<?> c3 = Class.forName("net.minecraft.server." + DungeonRealms.getInstance().getNmsVers() + ".IChatBaseComponent");
                Method m3 = c2.getDeclaredMethod("a", String.class);
                Object cbc = c3.cast(m3.invoke(c2, "{\"text\": \"" + message + "\"}"));
                ppoc = c4.getConstructor(new Class<?>[]{c3, byte.class}).newInstance(cbc, (byte) 2);
            } else {
                Class<?> c2 = Class.forName("net.minecraft.server." + DungeonRealms.getInstance().getNmsVers() + ".ChatComponentText");
                Class<?> c3 = Class.forName("net.minecraft.server." + DungeonRealms.getInstance().getNmsVers() + ".IChatBaseComponent");
                Object o = c2.getConstructor(new Class<?>[]{String.class}).newInstance(message);
                ppoc = c4.getConstructor(new Class<?>[]{c3, byte.class}).newInstance(o, (byte) 2);
            }
            Method m1 = c1.getDeclaredMethod("getHandle");
            Object h = m1.invoke(p);
            Field f1 = h.getClass().getDeclaredField("playerConnection");
            Object pc = f1.get(h);
            Method m5 = pc.getClass().getDeclaredMethod("sendPacket", c5);
            m5.invoke(pc, ppoc);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
