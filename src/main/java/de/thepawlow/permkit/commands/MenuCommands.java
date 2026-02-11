package de.thepawlow.permkit.commands;

import com.hypixel.hytale.server.core.command.system.CommandSender;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;
import com.hypixel.hytale.server.core.permissions.PermissionsModule;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

public class MenuCommands extends AbstractCommandCollection {

    public MenuCommands() {
        super("permkit", "An UI for managing Permissions");
        this.addSubCommand(new MenuDebugCommands());
        this.addSubCommand(new MenuShowCommand());
    }

    @Override
    public boolean hasPermission(@NonNullDecl CommandSender sender) {
        System.out.println(
            sender.getDisplayName() + " | Must have: " + this.getPermission()
        );

        return PermissionsModule.get().hasPermission(
            sender.getUuid(),
            this.getPermission()
        );
    }
}
