package dev.cnbetty.core.nms.packets;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundBlockDestructionPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public interface PacketNMS {

    Packet<?> toPackage();
    void send(ServerGamePacketListenerImpl listener);

    default void send(Player player) {
        send((CraftPlayer) player);
    }
    default void send(CraftPlayer craftPlayer) {
        send(craftPlayer.getHandle());
    }
    default void send(ServerPlayer serverPlayer) {
        send(serverPlayer.connection);
    }

    static PacketNMS getPacket(Packet<?> packet) {
        return getPacket(packet);
    }

    static PacketNMS getPacket(Object packet) {
        if(packet instanceof ClientboundBlockDestructionPacket) {
            return new SetBlockDestroyStagePacketNMS(packet);
        } else {
            return null;
        }
    }
}
