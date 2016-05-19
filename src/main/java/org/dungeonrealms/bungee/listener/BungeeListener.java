package org.dungeonrealms.bungee.listener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.dungeonrealms.DungeonRealms;
import org.dungeonrealms.api.events.ReceivePartyInformation;
import org.dungeonrealms.utils.Preconditions;

import java.util.logging.Logger;

/**
 * Created by Dr. Nick Doran on 5/19/2016.
 */
public class BungeeListener implements PluginMessageListener {

    private static final Logger log = Logger.getLogger(DungeonRealms.class.getName());

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!Preconditions.validChannel(channel)) return;

        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String var1 = in.readUTF();

        switch (var1.toLowerCase()) {
            case "party":
                //TODO: Break down byte stream.
                String playerName = in.readUTF();
                Bukkit.getServer().getPluginManager().callEvent(new ReceivePartyInformation(playerName));
                break;
        }


    }
}
