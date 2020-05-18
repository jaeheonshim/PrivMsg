package com.jaeheonshim.privmsg.commands;

import com.jaeheonshim.privmsg.PlayerManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.StringJoiner;

public class ReplyCommand {
    public static void execute(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("This command can only be executed by a player");
            return;
        }

        Player player = (Player) sender;
        Player respond = PlayerManager.getInstance().getReplyRecipient(player);

        if(respond == null) {
            player.sendMessage(ChatColor.RED + "You can only reply after you've sent a message or been sent a message.");
            return;
        }

        StringJoiner messageJoiner = new StringJoiner(" ");
        for(int i = 0; i < args.length; i++) {
            messageJoiner.add(args[i]);
        }

        player.sendMessage(ChatColor.GOLD + "[" + ChatColor.RED + " me " + ChatColor.GOLD + "-> " + ChatColor.RED + respond.getName() + ChatColor.GOLD + " ] " + ChatColor.WHITE + messageJoiner);
        respond.sendMessage(ChatColor.GOLD + "[ " + ChatColor.RED + player.getName() + ChatColor.GOLD + " ->" + ChatColor.RED + " me " + ChatColor.GOLD + "] " + ChatColor.WHITE + messageJoiner);

    }
}
