package dev.cnbetty.core.nms.packets;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundBlockDestructionPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.entity.player.Player;
import org.bukkit.craftbukkit.entity.CraftPlayer;

public interface PacketNMS {

    static PacketNMS getPacket(Packet<?> packet) {
        return getPacket(packet);
    }

    static PacketNMS getPacket(Object packet) {
        if (packet instanceof ClientboundBlockDestructionPacket) {
            return new SetBlockDestroyStagePacketNMS(packet);
        } else {
            return null;
        }
    }

    Packet<?> toPackage();

    void send(ServerGamePacketListenerImpl listener);

    default void send(Player player) {
        send(player);
    }

    default void send(CraftPlayer craftPlayer) {
        send(craftPlayer.getHandle());
    }

    default void send(ServerPlayer serverPlayer) {
        send(serverPlayer.connection);
    }
}
