package dev.cnbetty.core.config;

import com.moandjiezana.toml.Toml;
import dev.cnbetty.velocitycore.VelocityCore;

import java.io.File;

public class PluginConfig {

    private static String configpath = PluginConfigFile.getConfigPath();


    public static String getTeststring() {
        VelocityCore.getLogger().info("reading file " + PluginConfigFile.getConfigPath());
        File tomlfile = new File(configpath);

        Toml configtoml = new Toml().read(tomlfile);
        String teststring = configtoml.getString("msg");
        return teststring;
    }
}
