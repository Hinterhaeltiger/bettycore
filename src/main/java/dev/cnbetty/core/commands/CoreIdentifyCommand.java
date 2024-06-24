package dev.cnbetty.core.commands;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.jetbrains.annotations.NotNull;

public class CoreIdentifyCommand implements BasicCommand {
    @Override
    public void execute(@NotNull CommandSourceStack commandSourceStack, @NotNull String[] strings) {
        commandSourceStack.getSender().sendMessage("CORE version " + Main.version + " installed.");
    }
}
