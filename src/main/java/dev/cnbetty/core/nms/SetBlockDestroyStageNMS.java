package dev.cnbetty.core.nms;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundBlockDestructionPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class SetBlockDestroyStageNMS {
    public static void send(Player player, int entityID, Location location, Byte destroyStage) {
        send((CraftPlayer) player, entityID, location, destroyStage);
    }

    public static void send(CraftPlayer craftPlayer, int entityID, Location location, Byte destroyStage) {
        send(craftPlayer.getHandle(), entityID, location, destroyStage);
    }

    public static void send(ServerPlayer serverPlayer, int entityID, Location location, Byte destroyStage) {
        send(serverPlayer.connection, entityID, location, destroyStage);
    }

    public static void send(ServerGamePacketListenerImpl listener, int entityID, Location location, Byte destroyStage) {
        BlockPos blockPos = new BlockPos(location.getBlockX(), location.getBlockY(), location.getBlockZ());
        ClientboundBlockDestructionPacket packet = new ClientboundBlockDestructionPacket(entityID, blockPos, destroyStage);
        listener.send(packet);
    }
}
