package dev.cnbetty.core.commands;

import dev.cnbetty.core.Main;

public class CommandRegistry {
    public static void registerAll(Main plugin) {
        plugin.getCommand("coreidentify").setExecutor(new CoreIdentify());
        plugin.getCommand("Test").setExecutor(new Test());
    }
}
