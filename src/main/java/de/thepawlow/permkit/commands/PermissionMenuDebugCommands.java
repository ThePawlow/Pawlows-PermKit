package de.thepawlow.permkit.commands;

import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;

public class PermissionMenuDebugCommands extends AbstractCommandCollection {

    public PermissionMenuDebugCommands() {
        super("debug", "debug only");
        this.addSubCommand(new PermissionMenuDebugStatusCommand());
    }
}