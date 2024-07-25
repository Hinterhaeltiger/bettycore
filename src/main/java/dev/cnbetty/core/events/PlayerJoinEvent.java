package dev.cnbetty.core.events;

import dev.cnbetty.core.customitems.CustomItem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class PlayerJoinEvent implements Listener {
    @EventHandler
    public void onPlayerJoin(org.bukkit.event.player.PlayerJoinEvent evt) {

        List<Component> lore = new ArrayList<>();
        lore.add(Component.text("hello").color(TextColor.color(0, 255, 0)));
        lore.add(Component.text("world").color(TextColor.color(0, 255, 255)));
        CustomItem testitem = null;

        testitem = new CustomItem("testitem", Component.text("test item"), lore, Material.ACACIA_BOAT, 1, 99);

        testitem.give(evt.getPlayer(), 23);
    }
}
