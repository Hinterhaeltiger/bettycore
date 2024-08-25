package dev.cnbetty.core.listeners;


import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerListPingListener implements Listener {
    @EventHandler
    public void onPing(ServerListPingEvent evt) {
        evt.motd(Component.text("custom motd"));
    }
}
