package dev.cnbetty.core.chat;

import io.papermc.paper.chat.ChatRenderer;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MessageSentListener implements Listener, ChatRenderer {
    public static List<String> extractWordsAfterAt(String input) {
        List<String> wordsAfterAt = new ArrayList<>();

        int atIndex = input.indexOf("@");
        while (atIndex != -1) {
            String substringAfterAt = input.substring(atIndex + 1);

            String word = substringAfterAt.split("\\s+")[0];
            wordsAfterAt.add(word);

            atIndex = input.indexOf("@", atIndex + 1);
        }

        return wordsAfterAt;
    }

    @EventHandler
    public void onChat(AsyncChatEvent event) {
        event.renderer(this);
    }

    @Override
    public @NotNull Component render(@NotNull Player source, @NotNull Component sourceDisplayName, @NotNull Component message, @NotNull Audience viewer) {
        /**
         * return sourceDisplayName.color(TextColor.color(0x636363))
         *                 .append(Component.text(": \"", TextColor.color(0xffffff)))
         *                 .append(message).color(TextColor.color(0x00face))
         *                 .append(Component.text("\"", TextColor.color(0xffffff)));
         */
        String messageString = PlainTextComponentSerializer.plainText().serialize(message);


        if (messageString.contains(" @")) {
            for (String suspectedPingedName : extractWordsAfterAt(messageString)) {
                Player pinged = Bukkit.getPlayer(suspectedPingedName);

                if (pinged == null) {
                    source.sendMessage(
                            Component.text("[MESSAGING] ").color(NamedTextColor.RED)
                                    .append(Component.text("Invalid player."))
                    );
                }

                Component replacement = Component.text("@" + pinged.getName()).color(TextColor.color(0x00face));
                message.replaceText(TextReplacementConfig.builder()
                        .matchLiteral("@" + pinged.getName())
                        .replacement(replacement).build());
            }
            return message;
        }
        return message;
    }
}
