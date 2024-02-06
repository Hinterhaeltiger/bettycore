package dev.cnbetty.core.nms;

import net.minecraft.network.protocol.Packet;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class ClientboundPacketEvent extends Event implements Cancellable {

    public static HandlerList handlerList = new HandlerList();

    public Packet packet;
    public Player player;
    public boolean cancelled;

    public ClientboundPacketEvent(Packet packet, Player player) {
        this.packet = packet;
        this.player = player;
        this.cancelled = false;
    }

    public Packet getPacket() {
        return this.packet;
    }

    public void setPacket(Packet packet) {
        this.packet = packet;
    }

    public Player getPlayer() {
        return this.player;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return ClientboundPacketEvent.handlerList;
    }

    public static @NotNull HandlerList getHandlerList() {
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
