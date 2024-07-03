package dev.cnbetty.core.commands;

import dev.cnbetty.core.Core;
import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.jetbrains.annotations.NotNull;

public class CoreVersionCommand implements BasicCommand {
    @Override
    public void execute(@NotNull CommandSourceStack commandSourceStack, @NotNull String[] strings) {
        commandSourceStack.getSender().sendMessage("CORE version " + Core.version + " installed.");
    }
}
