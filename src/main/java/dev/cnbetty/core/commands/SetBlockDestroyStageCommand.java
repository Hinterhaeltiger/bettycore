package dev.cnbetty.core.commands;

import dev.cnbetty.core.nms.packets.SetBlockDestroyStagePacketNMS;
import net.minecraft.core.BlockPos;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SetBlockDestroyStageCommand implements CommandExecutor, TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player) commandSender;
        byte i;

        if(strings.length == 1 && Integer.parseInt(strings[0]) < 11) {
            if (strings[0].equals("0")) {
                i = Byte.parseByte("10");
            } else {
                i = Byte.parseByte("" + (Integer.parseInt(strings[0]) - 1));
            }
            new SetBlockDestroyStagePacketNMS(player.getEntityId(), new BlockPos(player.getLocation().getBlockX(), player.getLocation().getBlockY() - 1, player.getLocation().getBlockZ()), i).send((CraftPlayer) player);
            return true;
        } else return false;
    }
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (strings.length > 1) {
            return Collections.emptyList();
        } else {
            List<String> returnlist = new ArrayList<>();
            for (int i = 0; i <= 10; i++) {
                returnlist.add(""+i);
            }

            return returnlist;
        }
    }
}
