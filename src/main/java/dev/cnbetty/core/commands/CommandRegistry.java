package dev.cnbetty.core.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.jetbrains.annotations.NotNull;

public class CommandRegistry {
    public static void registerImportant(BootstrapContext context) {
        @NotNull final LifecycleEventManager<BootstrapContext> manager = context.getLifecycleManager();
        manager.registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            final Commands commands = event.registrar();
            commands.register(
                    //register block destroy stage command
                    Commands.literal("setblockdestroystage")
                            .then(
                                    Commands.argument("destroystage", IntegerArgumentType.integer(0, 10))
                                            .executes(commandContext -> {
                                                int stage = commandContext.getArgument("destroystage", Integer.class);
                                                SetBlockDestroyStageCommand.execute(commandContext.getSource(), stage);
                                                return Command.SINGLE_SUCCESS;
                                            })).build()
            );
            /*commands.register(
                    Commands.literal("giftitem")
                            .then(
                                    Commands.argument("recipient", ArgumentTypes.players())
                                            .then(
                                                    Commands.argument()
                                                            .executes(commandContext -> {
                                                                final TypedKey<String>

                                                                GiftItemCommand.execute(commandContext.getSource(), commandContext.getArgument("recipient", Player.class), commandContext.getArgument("customitem", CustomItem.class));
                                                                return Command.SINGLE_SUCCESS;
                                                            })
                                            )
                            ).build());*/
        });
    }
}
