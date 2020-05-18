package com.jaeheonshim.privmsg;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class PlayerManager {
    private Set<Player> players = new HashSet<Player>();
    private HashMap<Player, Player> senderToReplyRecipient = new HashMap<Player, Player>();

    private static PlayerManager instance;

    public static PlayerManager getInstance() {
        if (instance == null) {
            instance = new PlayerManager();
        }

        return instance;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public Player getPlayerByUsername(String username) {
        for (Player player : players) {
            if (player.getName().equalsIgnoreCase(username)) {
                return player;
            }
        }

        return getPlayerByUsernameBukkit(username);
    }

    public Player getPlayerByUsernameBukkit(String username) {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            if (player.getName().equalsIgnoreCase(username)) {
                addPlayer(player);
                return player;
            }
        }

        return null;
    }

    public void setReplyRecipient(Player player, Player reply) {
        senderToReplyRecipient.put(player, reply);
    }

    public Player getReplyRecipient(Player replier) {
        return senderToReplyRecipient.get(replier);
    }
}
