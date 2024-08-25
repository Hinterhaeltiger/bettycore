package dev.cnbetty.core.listeners;

import dev.cnbetty.core.Core;
import dev.cnbetty.core.chat.MessageSentListener;
import dev.cnbetty.core.nms.PacketEvent;
import org.bukkit.Bukkit;

public class EventRegistry {
    public static void registerAll(Core plugin) {
        Bukkit.getPluginManager().registerEvents(new MessageSentListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new PacketEvent(), plugin);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new ServerListPingListener(), plugin);
    }
}
