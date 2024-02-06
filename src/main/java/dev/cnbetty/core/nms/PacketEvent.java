package dev.cnbetty.core.nms;

import dev.cnbetty.core.Main;
import io.netty.channel.*;
import net.minecraft.network.protocol.Packet;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PacketEvent implements Listener {

    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {
        injectPlayer(event.getPlayer());
    }

    @EventHandler
    public void playerLeave(PlayerQuitEvent event) {
        removePlayer(event.getPlayer());
    }

    private void injectPlayer(Player player) {
        ChannelDuplexHandler channelDuplexHandler = new ChannelDuplexHandler(){
            @Override
            public void channelRead(ChannelHandlerContext channelHandlerContext, Object packet) {
                try {
                    ServerboundPacketEvent serverboundPacketEvent = new ServerboundPacketEvent((Packet) packet, player);
                    //Bukkit.getPluginManager().callEvent(serverboundPacketEvent);
                    if(serverboundPacketEvent.isCancelled()) {
                        return;
                    }
                    packet = serverboundPacketEvent.getPacket();
                    super.channelRead(channelHandlerContext, packet);
                } catch (Exception e) {
                    Main.logger.error(e.getMessage());
                }
            }

            @Override
            public void write(ChannelHandlerContext channelHandlerContext, Object packet, ChannelPromise channelPromise) {
                try {
                    ClientboundPacketEvent clientboundPacketEvent = new ClientboundPacketEvent((Packet) packet, player);
                    //Bukkit.getPluginManager().callEvent(clientboundPacketEvent);
                    if(clientboundPacketEvent.isCancelled()) {
                        return;
                    }
                    packet = clientboundPacketEvent.getPacket();
                    super.write(channelHandlerContext, packet, channelPromise);
                } catch (Exception e) {
                    Main.logger.error(e.getMessage());
                }
            }
        };
        ChannelPipeline pipeline = ((CraftPlayer) player).getHandle().connection.connection.channel.pipeline();
        pipeline.addBefore("packet_handler", player.getName(), channelDuplexHandler);
    }

    private void removePlayer(Player player) {
        Channel channel = ((CraftPlayer) player).getHandle().connection.connection.channel;
        channel.eventLoop().submit(() -> {
            channel.pipeline().remove(player.getName());
            return null;
        });
    }
}
