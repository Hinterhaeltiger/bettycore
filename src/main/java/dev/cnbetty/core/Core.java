package dev.cnbetty.core;

import dev.cnbetty.core.config.PluginConfig;
import dev.cnbetty.core.events.EventRegistry;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.nio.file.Path;
import java.util.logging.Logger;

public class Core extends JavaPlugin {
    public static final Logger logger = Bukkit.getLogger();
    public static String version;
    public static PluginDescriptionFile pluginDescriptionFile;
    public static Path configfilepath;

    private static Core instance;
    @Override
    public void onEnable() {
        logger.info("plugin loaded.");

        instance = this;

        pluginDescriptionFile = this.getDescription();
        version = pluginDescriptionFile.getVersion();
        logger.info("PDF loaded.");
        logger.info(version);

        logger.info("commands loaded.");

        saveResource("config/settings.toml", false);
        configfilepath = Path.of(Bukkit.getWorldContainer().toURI().toString() + "plugins\\Core\\config\\config.toml");
        logger.info("config initialized at " + configfilepath.toString());

        EventRegistry.registerAll(this);
        logger.info("events registered.");

        instance = this;



        logger.info("toml message: " + PluginConfig.getKey());
    }
    @Override
    public void onDisable() {
        logger.info("plugin unloaded");
    }
    public static Core getInstance() {
        return instance;
    }
    public static String getVersion() { return version; }
}

