package de.thepawlow.permkit.commands;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.CommandManager;
import com.hypixel.hytale.server.core.command.system.CommandSender;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.entity.UUIDComponent;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.permissions.PermissionsModule;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

public class PermissionMenuDebugStatusCommand extends AbstractPlayerCommand {

    public PermissionMenuDebugStatusCommand() {
        super("status", "debug only");
    }

    @Override
    protected void execute(@NonNullDecl CommandContext commandContext, @NonNullDecl Store<EntityStore> store, @NonNullDecl Ref<EntityStore> ref, @NonNullDecl PlayerRef playerRef, @NonNullDecl World world) {
        Player player = store.getComponent(ref, Player.getComponentType());
        assert player != null;

        UUIDComponent uuidComponent = store.getComponent(ref, UUIDComponent.getComponentType());
        assert uuidComponent != null;

//        player.sendMessage(Message.raw("Groups:"));
//        var assignedGroups = PermissionsModule.get().getGroupsForUser(uuidComponent.getUuid());
//        for (String group : assignedGroups) {
//            player.sendMessage(Message.raw(group));
//        }

        var commandEntries = CommandManager.get().getCommandRegistration();

        player.sendMessage(Message.raw("Permissions-Categories:"));
//        for (Map.Entry<String, AbstractCommand> entry : commandEntries.entrySet()) {
//            var permissionString = entry.getValue().getPermission();
//            player.sendMessage(Message.raw(entry.getKey()));
//        }

        commandEntries.entrySet().stream().limit(3).forEach(entry -> {
            var permissionString = entry.getValue().getPermission();
            player.sendMessage(Message.raw(entry.getKey()));
            player.sendMessage(Message.raw(permissionString));
        });
    }

    @Override
    public boolean hasPermission(@NonNullDecl CommandSender sender) {
        System.out.println(sender.getDisplayName() + " | Must have: " + this.getPermission());

        return PermissionsModule.get().hasPermission(sender.getUuid(), this.getPermission());
    }
}