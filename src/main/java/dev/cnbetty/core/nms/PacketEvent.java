package dev.cnbetty.core.nms;

import dev.cnbetty.core.Core;
import dev.cnbetty.core.nms.packets.PacketNMS;
import io.netty.channel.*;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;

public class PacketEvent implements Listener {

     final private Map<CraftPlayer, ChannelDuplexHandler> playerHandlers = new HashMap<>();

    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {
        injectPlayer((CraftPlayer) event.getPlayer());
    }

    @EventHandler
    public void playerLeave(PlayerQuitEvent event) {
        removePlayer((CraftPlayer) event.getPlayer());
    }

    private void injectPlayer(CraftPlayer player) {
        if (playerHandlers.containsKey(player)) {
            return;
        }
        ChannelDuplexHandler channelDuplexHandler = new ChannelDuplexHandler() {
            @Override
            public void channelRead(ChannelHandlerContext channelHandlerContext, Object packet) {
                final Object finalPacket = packet;
                Bukkit.getScheduler().runTask(Core.getInstance(), () -> {
                    try {
                        Object p = finalPacket;
                        PacketNMS packetNMS = PacketNMS.getPacket(p);
                        if (packetNMS != null) {
                            ServerboundPacketEvent serverboundPacketEvent = new ServerboundPacketEvent(packetNMS, player);
                            Bukkit.getPluginManager().callEvent(serverboundPacketEvent);
                            if (serverboundPacketEvent.isCancelled()) {
                                return;
                            }
                            p = serverboundPacketEvent.getPacket().toPackage();
                        }
                        super.channelRead(channelHandlerContext, p);
                    } catch (Exception e) {
                        //
                    }
                });
            }

            @Override
            public void write(ChannelHandlerContext channelHandlerContext, Object packet, ChannelPromise channelPromise) {
                final Object finalPacket = packet;
                Bukkit.getScheduler().runTask(Core.getInstance(), () -> {
                    try {
                        Object p = finalPacket;
                        PacketNMS packetNMS = PacketNMS.getPacket(p);
                        if (packetNMS != null) {
                            ClientboundPacketEvent clientboundPacketEvent = new ClientboundPacketEvent(packetNMS, player);
                            Bukkit.getPluginManager().callEvent(clientboundPacketEvent);
                            if (clientboundPacketEvent.isCancelled()) {
                                return;
                            }
                            p = clientboundPacketEvent.getPacket().toPackage();
                        }
                        super.write(channelHandlerContext, p, channelPromise);
                    } catch (Exception e) {
                        //
                    }
                });
            }
        };
        ChannelPipeline pipeline = player.getHandle().connection.connection.channel.pipeline();
        pipeline.addBefore("packet_handler", player.getName(), channelDuplexHandler);

        playerHandlers.put(player, channelDuplexHandler);
    }

    private void removePlayer(CraftPlayer player) {
        Channel channel = (player).getHandle().connection.connection.channel;
        channel.eventLoop().submit(() -> {
            try {
                if (!channel.isActive() || !channel.isOpen()) {
                    channel.pipeline().remove(player.getName());
                    return null;
                }

                ChannelDuplexHandler handler = playerHandlers.remove(player);
                if (handler != null) {
                    channel.pipeline().remove(handler);
                }
            } catch (Exception e) {
                //
            }
            return null;
        });
    }
}
