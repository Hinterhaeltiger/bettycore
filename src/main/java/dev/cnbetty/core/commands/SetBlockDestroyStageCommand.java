package dev.cnbetty.core.commands;

import dev.cnbetty.core.nms.packets.SetBlockDestroyStagePacketNMS;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.minecraft.core.BlockPos;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class SetBlockDestroyStageCommand {
    public static void execute(CommandSourceStack commandSourceStack, int stage) {
        CommandSender sender = commandSourceStack.getSender();

        if (!(sender instanceof Player)) {
            sender.sendPlainMessage("You have to be a player to use this command!");
            return;
        }
        byte i;
        Player player = ((Player) sender).getPlayer();
        if (stage == 0) {
            i = Byte.parseByte("10");
        } else {
            i = Byte.parseByte("" + (stage - 1));
        }
        new SetBlockDestroyStagePacketNMS(player.getEntityId(), new BlockPos(player.getLocation().getBlockX(), player.getLocation().getBlockY() - 1, player.getLocation().getBlockZ()), i).send((CraftPlayer) player);

    }
}
