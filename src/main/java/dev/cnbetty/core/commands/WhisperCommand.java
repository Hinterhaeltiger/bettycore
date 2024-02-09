package dev.cnbetty.core.commands;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class WhisperCommand implements CommandExecutor, TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (strings.length > 1) {
            Player recipient = Bukkit.getPlayer(strings[0]);

            String message = "";
            for (int i = 1; i < strings.length; i++) {
                message += strings[i] + " ";
            }

            recipient.sendMessage(Component.text(commandSender.getName() + " whispers to you: " + message));
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (strings.length == 0) {
            List<String> playerlist = null;
            for (Player player : Bukkit.getOnlinePlayers()) {
                playerlist.add(String.valueOf(player));
            }
            return playerlist;
        } else if (strings.length > 1) {
            List<String> emptylist = new ArrayList<>();
            return emptylist;
        }
        return null;
    }
}
