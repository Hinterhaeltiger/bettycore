package dev.cnbetty.core.commands;

import dev.cnbetty.core.Main;

public class CommandRegistry {
    public static void registerAll(Main plugin) {
        plugin.getCommand("coreidentify").setExecutor(new CoreIdentify());
        //plugin.getCommand("opengui").setExecutor(new GUICommand());
        plugin.getCommand("setblockdestroystage").setExecutor(new SetBlockDestroyStageCommand());
        plugin.getCommand("msg").setExecutor(new WhisperCommand());
        plugin.getCommand("msg").setTabCompleter(new WhisperCommand());

    }
}
