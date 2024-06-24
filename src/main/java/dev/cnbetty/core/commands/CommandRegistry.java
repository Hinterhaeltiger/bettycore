package dev.cnbetty.core.commands;

import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.jetbrains.annotations.NotNull;

public class CommandRegistry {
    public static void registerImportant(BootstrapContext context) {
        @NotNull LifecycleEventManager<BootstrapContext> manager = context.getLifecycleManager();

        //plugin.getCommand("opengui").setExecutor(new GUICommand());
        manager.registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            final Commands commands = event.registrar();
            commands.register("coreidentify", "Command for identifying the Core plugin's version", new CoreIdentifyCommand());
        });
        /*manager.registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            final Commands commands = event.registrar();
            commands.register("setblockdestroystage", "Command for setting a block's destroy stage using an NMS packet", new SetBlockDestroyStageCommand());
        });*/

        /*manager.registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            final Commands commands = event.registrar();
            SetBlockDestroyStageCommand.register(comma);
        });*/





        /*plugin.getCommand("setblockdestroystage").setExecutor(new SetBlockDestroyStageCommand());
        plugin.getCommand("msg").setExecutor(new WhisperCommand());
        plugin.getCommand("msg").setTabCompleter(new WhisperCommand());*/





    }
}
