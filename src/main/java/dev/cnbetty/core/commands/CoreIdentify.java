package dev.cnbetty.core.commands;

import dev.cnbetty.core.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CoreIdentify implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("CORE version " + Main.version + " installed.");
        return true;
    }
}
