package dev.cnbetty.core;


import com.mojang.brigadier.Command;
import dev.cnbetty.core.commands.CommandRegistry;
import dev.cnbetty.core.commands.CoreIdentifyCommand;
import dev.cnbetty.core.logger.Logger;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;

import java.util.List;

public class CoreBootstrap implements PluginBootstrap {

    @Override
    public void bootstrap(BootstrapContext context) {
        //The bootstrap logic is run before anything else, so datapacks can use custom commands
        CommandRegistry.registerImportant(context);
        context.getLogger().info("Commands loaded!");
    }
}