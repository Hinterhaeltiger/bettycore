package dev.cnbetty.core.commands;

import com.google.common.base.Functions;
import com.google.common.collect.Lists;
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
        Byte i;

        if(strings.length == 1 && Integer.parseInt(strings[0]) < 11) {
            if (strings[0] == "0") {
                i = Byte.parseByte("10");
            } else {
                i = Byte.valueOf("" + (Integer.parseInt(strings[0]) - 1));
            }
            new SetBlockDestroyStagePacketNMS(player.getEntityId(), new BlockPos(player.getLocation().getBlockX(), player.getLocation().getBlockY() - 1, player.getLocation().getBlockZ()), i).send((CraftPlayer) player);
            return true;
        } else return false;
    }
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        List<Integer> completorlist = new ArrayList<>();

        if (strings.length > 1) {
            return Collections.emptyList();
        } else {
            List<Integer> numbers = new ArrayList<>();
            for (int i = 0; i <= 10; i++) {
                numbers.add(i);
            }

            // Convert the list of numbers to a list of strings
            List<String> stringNumbers = new ArrayList<>();
            for (Integer number : numbers) {
                stringNumbers.add(number.toString());
            }

            // Sort the list of strings numerically
            Collections.sort(stringNumbers, (a, b) -> Integer.compare(Integer.parseInt(a), Integer.parseInt(b)));

            return stringNumbers;
        }
    }
}
