package dev.cnbetty.core.events;

import dev.cnbetty.core.Main;
import dev.cnbetty.core.chat.MessageSentListener;

public class EventRegistry {
    public static void registerAll(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(new MessageSentListener(), plugin);
    }
}
