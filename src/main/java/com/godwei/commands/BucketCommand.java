package com.godwei.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import net.minecraft.server.command.ServerCommandSource;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public final class BucketCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("dispenser")
                .requires(source -> source.hasPermissionLevel(2))
                .then(argument("allow", BoolArgumentType.bool()))
        );
    }

    public static boolean isAllowed(ServerCommandSource source, boolean allow){
        return allow;
    }
}
