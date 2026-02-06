package de.thepawlow.permkit.commands;

import com.hypixel.hytale.builtin.instances.InstancesPlugin;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.Universe;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

public class DebugSpawnInstance extends AbstractPlayerCommand {
    public DebugSpawnInstance() {
        super("debuginstancespawn", "commands.permissionmenu.list.desc");
    }

    @Override
    protected void execute(@NonNullDecl CommandContext commandContext, @NonNullDecl Store<EntityStore> store, @NonNullDecl Ref<EntityStore> ref, @NonNullDecl PlayerRef playerRef, @NonNullDecl World world) {
        var pos = playerRef.getTransform();

        assert playerRef.getReference() != null;

        World instanceWorld = InstancesPlugin.get().spawnInstance("Tutorial", world, pos).join();
        Universe.get().sendMessage(Message.raw("Instance spawned: " + instanceWorld.getName()));
        InstancesPlugin.teleportPlayerToInstance(
                playerRef.getReference(),
                store,
                instanceWorld,
                pos // optional override for return point
        );

    }
}
