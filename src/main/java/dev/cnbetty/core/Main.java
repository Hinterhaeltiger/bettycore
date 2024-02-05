package dev.cnbetty.core;

import dev.cnbetty.core.commands.CoreIdentify;
import dev.cnbetty.core.logger.Logger;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Main extends JavaPlugin {
    public static final String version = "0.0.1-DEV";
    Logger logger = new Logger("core");
    @Override
    public void onEnable() {
        logger.info("plugin loaded.");

        this.getCommand("coreidentify").setExecutor(new CoreIdentify());
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

