package dev.cnbetty.core.events;

import dev.cnbetty.core.Main;
import dev.cnbetty.core.chat.MessageSentListener;
import dev.cnbetty.core.nms.PacketEvent;
import org.bukkit.Bukkit;

public class EventRegistry {
    public static void registerAll(Main plugin) {
        Bukkit.getPluginManager().registerEvents(new MessageSentListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new PacketEvent(), plugin);
    }
}
