package de.thepawlow.permkit.commands;

import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;

public class MenuDebugCommands extends AbstractCommandCollection {

    public MenuDebugCommands() {
        super("debug", "debug only");
        this.addSubCommand(new MenuDebugCommandStatus());
    }
}
