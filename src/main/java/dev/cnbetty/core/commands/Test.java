package dev.cnbetty.core.commands;

import dev.cnbetty.core.nms.SetBlockDestroyStageNMS;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Test implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player) commandSender;
        Byte i = Byte.valueOf(strings[0]);
        SetBlockDestroyStageNMS.send(player, player.getEntityId(),player.getLocation(), i);
        return true;
    }
}
