package com.jaeheonshim.privmsg;

import com.jaeheonshim.privmsg.commands.MessageCommand;
import com.jaeheonshim.privmsg.commands.ReplyCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class MessagePlugin extends JavaPlugin {
    @Override
    public void onEnable() {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
       if(command.getName().equalsIgnoreCase("msg")) {
           if(args.length >= 2) {
               MessageCommand.execute(sender, command, label, args);
               return true;
           }
       }

       if(command.getName().equalsIgnoreCase("r")) {
           if(args.length >= 1) {
               ReplyCommand.execute(sender, command, label, args);
               return true;
           }
       }

       return false;
    }
}
