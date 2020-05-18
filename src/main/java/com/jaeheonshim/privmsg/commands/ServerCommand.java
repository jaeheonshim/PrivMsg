package com.jaeheonshim.privmsg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface ServerCommand {
    public abstract int argumentSize();
    public abstract void execute(CommandSender sender, Command command, String label, String[] args);
}
