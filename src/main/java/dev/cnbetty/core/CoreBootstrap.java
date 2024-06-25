package dev.cnbetty.core;


import dev.cnbetty.core.commands.CommandRegistry;
import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;

public class CoreBootstrap implements PluginBootstrap {

    @Override
    public void bootstrap(BootstrapContext context) {
        //The bootstrap logic is run before anything else, so datapacks can use custom commands
        CommandRegistry.registerImportant(context);
        context.getLogger().info("Commands loaded!");
    }
}