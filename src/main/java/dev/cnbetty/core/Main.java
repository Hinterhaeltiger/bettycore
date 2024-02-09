package dev.cnbetty.core;

import dev.cnbetty.core.commands.CommandRegistry;
import dev.cnbetty.core.events.EventRegistry;
import dev.cnbetty.core.logger.Logger;
import dev.cnbetty.core.nms.PacketEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static final Logger logger = new Logger("core");
    public static String version;
    private static Main instance;
    public static PluginDescriptionFile pluginDescriptionFile;

    public static Main getPlugin() {
        return instance;
    }


    @Override
    public void onEnable() {
        logger.info("plugin loaded.");

        CommandRegistry.registerAll(this);
        instance = this;

        pluginDescriptionFile = instance.getDescription();
        version = pluginDescriptionFile.getVersion();
        logger.info("PDF loaded.");

        CommandRegistry.registerAll(instance);
        logger.info("commands loaded.");

        saveResource("config.yml", false);
        logger.info("config initialized");

        EventRegistry.registerAll(this);
        logger.info("events registered.");

        instance = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

