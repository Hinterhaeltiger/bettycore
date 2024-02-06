package dev.cnbetty.core;

import dev.cnbetty.core.commands.CommandRegistry;
import dev.cnbetty.core.logger.Logger;
import dev.cnbetty.core.nms.PacketEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static final String version = "0.0.1-DEV";
    public static final Logger logger = new Logger("core");
    private static Main instance;

    public static Main getPlugin() {
        return instance;
    }

    @Override
    public void onEnable() {
        logger.info("plugin loaded.");

        instance = this;

        CommandRegistry.registerAll(instance);
        logger.info("commands loaded.");

        saveResource("config.yml", false);
        logger.info("config initialized");

        Bukkit.getPluginManager().registerEvents(new PacketEvent(), instance);
        logger.info("events loaded.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

