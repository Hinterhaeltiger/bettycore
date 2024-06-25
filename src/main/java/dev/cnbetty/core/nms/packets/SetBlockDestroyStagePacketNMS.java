package dev.cnbetty.core.nms.packets;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundBlockDestructionPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;


public class SetBlockDestroyStagePacketNMS implements PacketNMS {

    int entityID;
    BlockPos blockPos;
    Byte destroyStage;

    public SetBlockDestroyStagePacketNMS(int entityID, BlockPos blockPos, Byte destroyStage) {
        this.entityID = entityID;
        this.blockPos = blockPos;
        this.destroyStage = destroyStage;
    }

    public SetBlockDestroyStagePacketNMS(Object packet) {
        if (packet instanceof ClientboundBlockDestructionPacket clientboundBlockDestructionPacket) {
            this.entityID = clientboundBlockDestructionPacket.getId();
            this.blockPos = clientboundBlockDestructionPacket.getPos();
            this.destroyStage = (byte) clientboundBlockDestructionPacket.getProgress();
        }
    }

    @Override
    public Packet<?> toPackage() {
        return new ClientboundBlockDestructionPacket(entityID, blockPos, destroyStage);
    }

    @Override
    public void send(ServerGamePacketListenerImpl listener) {
        listener.send(toPackage());
    }

    @Override
    public void send(Player player) {
        send((CraftPlayer) player);
    }

    @Override
    public void send(CraftPlayer craftPlayer) {
        send(craftPlayer.getHandle());
    }

    @Override
    public void send(ServerPlayer serverPlayer) {
        send(serverPlayer.connection);
    }

    public int getEntityID() {
        return entityID;
    }

    public void setEntityID(int entityID) {
        this.entityID = entityID;
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    public void setBlockPos(BlockPos blockPos) {
        this.blockPos = blockPos;
    }

    public Byte getDestroyStage() {
        return destroyStage;
    }

    public void setDestroyStage(Byte destroyStage) {
        this.destroyStage = destroyStage;
    }
}
