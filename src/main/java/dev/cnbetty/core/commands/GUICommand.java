package dev.cnbetty.core.commands;

import dev.cnbetty.core.gui.InventoryGUIHolder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class GUICommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            Inventory inventory = Bukkit.createInventory(new InventoryGUIHolder(), 9, "Example GUI");
            ItemStack stick = new ItemStack(Material.STICK);
            inventory.addItem(stick);
            player.openInventory(inventory);




            return false;
        } else {
            return true;
        }
    }
}
