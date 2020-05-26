package com.jaeheonshim.privmsg.commands;

import com.jaeheonshim.privmsg.util.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.StringJoiner;

public class MessageCommand {
    public static void execute(CommandSender sender, Command command, String label, String[] args) {
       if(!(sender instanceof Player)) {
           Bukkit.getLogger().info("This command can only be executed by a player");
           return;
       }

       Player player = (Player) sender;
       String recipientUsername = args[0];
       Player recipient = PlayerManager.getInstance().getPlayerByUsername(recipientUsername);

       if(recipient == null || !recipient.isOnline()) {
           player.sendMessage(ChatColor.RED + "A player with that username is not online.");
           return;
       } else if(player.equals(recipient)) {
           player.sendMessage(ChatColor.RED + "You can't send a message to yourself!");
           return;
       }

       // set the reply recipient to the player the sender is sending a message to.
       PlayerManager.getInstance().setReplyRecipient(player, recipient);
       PlayerManager.getInstance().setReplyRecipient(recipient, player);

       StringJoiner message = new StringJoiner(" ");
       for(int i = 1; i < args.length; i++) {
          message.add(args[i]);
       }

       Bukkit.getLogger().info(player.getName() + " -> " + recipient.getName() + ": " + message);

       player.sendMessage(ChatColor.GOLD + "[" + ChatColor.RED + "me " + ChatColor.GOLD + "-> " + ChatColor.RED + recipient.getName() + ChatColor.GOLD + "] " + ChatColor.WHITE + message);
       recipient.sendMessage(ChatColor.GOLD + "[" + ChatColor.RED + player.getName() + ChatColor.GOLD + " ->" + ChatColor.RED + " me" + ChatColor.GOLD + "] " + ChatColor.WHITE + message);
    }
}
