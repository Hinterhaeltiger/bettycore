package dev.cnbetty.core.nms;

import dev.cnbetty.core.nms.packets.PacketNMS;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class ClientboundPacketEvent extends Event implements Cancellable {

    public static HandlerList handlerList = new HandlerList();

    public PacketNMS packet;
    public Player player;
    public boolean cancelled;

    public ClientboundPacketEvent(PacketNMS packet, Player player) {
        this.packet = packet;
        this.player = player;
        this.cancelled = false;
    }

    public static @NotNull HandlerList getHandlerList() {
        return ClientboundPacketEvent.handlerList;
    }

    public PacketNMS getPacket() {
        return this.packet;
    }

    public void setPacket(PacketNMS packet) {
        this.packet = packet;
    }

    public Player getPlayer() {
        return this.player;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return ClientboundPacketEvent.handlerList;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
