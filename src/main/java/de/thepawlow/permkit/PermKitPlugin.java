package de.thepawlow.permkit;

import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import de.thepawlow.permkit.commands.PermissionMenuCommand;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

import java.util.logging.Level;

public class PermKitPlugin extends JavaPlugin {
    private static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();

    private static PermKitPlugin instance;

    public PermKitPlugin(@NonNullDecl JavaPluginInit init) {
        super(init);
        instance = this;
    }

    @Override
    protected void setup() {
        this.getCommandRegistry().registerCommand(new PermissionMenuCommand());
    }

    @Override
    protected void start() {
        var name = this.getManifest().getName();
        var version = this.getManifest().getVersion();
        var author = this.getManifest().getAuthors().getFirst();

        var pluginMessage = name + "@" + version;
        var authorMessage = "by " + author.getName();
        var divider = ConsoleColors.GREEN_BOLD_BRIGHT+ "=".repeat(pluginMessage.length() + 2);

        LOGGER.at(Level.INFO).log(divider);
        LOGGER.at(Level.INFO).log(ConsoleColors.GREEN_BOLD + " " + pluginMessage);
        LOGGER.at(Level.INFO).log(ConsoleColors.GREEN_BOLD + " " + authorMessage);
        LOGGER.at(Level.INFO).log(divider + ConsoleColors.RESET);
    }

    @Override
    protected void shutdown() {
        LOGGER.at(Level.INFO).log(ConsoleColors.RED + "Stopping" + ConsoleColors.RESET);
    }

    public static PermKitPlugin get() {
        return instance;
    }
}