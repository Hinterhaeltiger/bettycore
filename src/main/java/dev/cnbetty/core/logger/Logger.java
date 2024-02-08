package dev.cnbetty.core.logger;

import dev.cnbetty.core.stringformatter.StringColor;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

public final class Logger {
    private static String plugin;
    private static ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

    public Logger(String pluginname) {
        this.plugin = pluginname.toUpperCase();
    }

    public static void log(LogLevel level, String message) {
        switch (level) {
            case INFO:
                console.sendMessage(StringColor.WHITE + "{[" + StringColor.GRAY + "INFO" + StringColor.WHITE + "from" + plugin + " through CORE]: " + message);
                break;
            case WARNING:
                console.sendMessage(StringColor.YELLOW + "{[" + StringColor.GOLD + "INFO" + StringColor.YELLOW + "from" + plugin + " through CORE]: " + message);
                break;
            case ERROR:
                console.sendMessage(StringColor.RED + "{[" + StringColor.DARK_RED + "INFO" + StringColor.RED + " from" + plugin + " through CORE]: " + message);
                break;
        }
    }

    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    public void warn(String message) {
        log(LogLevel.WARNING, message);
    }

    public void error(String message) {
        log(LogLevel.ERROR, message);
    }

    public enum LogLevel {
        INFO,
        WARNING,
        ERROR
    }
}