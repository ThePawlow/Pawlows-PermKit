package de.thepawlow.permkit.commands;

import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;
import com.hypixel.hytale.server.core.permissions.HytalePermissions;

public class PermissionMenuCommand extends AbstractCommandCollection {

    public PermissionMenuCommand() {
        super("permkit", "An UI for managing Permissions");
        this.addSubCommand(new PermissionMenuShowCommand());
        this.requirePermission(HytalePermissions.fromCommand("op.add"));
    }

//    @Override
//    protected void execute(@Nonnull CommandContext commandContext, @Nonnull Store<EntityStore> store, @Nonnull Ref<EntityStore> ref, @Nonnull PlayerRef playerRef, @Nonnull World world) {
//        Player player = store.getComponent(ref, Player.getComponentType()); // also a component
//        UUIDComponent component = store.getComponent(ref, UUIDComponent.getComponentType());
//        TransformComponent transform = store.getComponent(ref, TransformComponent.getComponentType());
//        player.sendMessage(Message.raw("Player#getUuid() : " + player.getUuid())); // returns UUID from UUIDComponent
//        player.sendMessage(Message.raw("UUIDComponent : " + component.getUuid()));
//        player.sendMessage(Message.raw("equal : " + player.getUuid().equals(component.getUuid()))); // they're both the same
//        player.sendMessage(Message.raw("Transform : " + transform.getPosition()));
//        HytaleServer.get().shutdownServer(ShutdownReason.CRASH);
//    }
}