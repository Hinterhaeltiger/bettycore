package dev.cnbetty.core;

import dev.cnbetty.core.config.PluginConfig;
import dev.cnbetty.core.events.EventRegistry;
import io.papermc.paper.plugin.configuration.PluginMeta;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Core extends JavaPlugin {
    public static final Logger logger = Bukkit.getLogger();
    public static String version;
    private static PluginMeta pluginMeta;
    private static Core instance;

    public static Core getInstance() {
        return instance;
    }

    public static String getVersion() {
        return version;
    }

    @Override
    public void onEnable() {


        logger.info("plugin loaded.");

        instance = this;


        version = getVersion();
        logger.info("PDF loaded.");
        logger.info(version);

        logger.info("commands loaded.");

        saveResource("config.yml", false);
        logger.info("config initialized");

        EventRegistry.registerAll(this);
        logger.info("events registered.");

        pluginMeta = getPluginMeta();
        version = pluginMeta.getVersion();
        instance = this;


        logger.info("toml message: " + PluginConfig.getKey());
    }

    @Override
    public void onDisable() {
        logger.info("plugin unloaded");
    }
}

