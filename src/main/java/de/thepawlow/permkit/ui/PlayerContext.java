package de.thepawlow.permkit.ui;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import dev.jonrapp.hytaleReactiveUi.events.EventRouter;

import java.text.SimpleDateFormat;
import java.util.Date;

public record PlayerContext(
        Ref<EntityStore> ref,
        Store<EntityStore> store,
        PlayerRef playerRef,
        Player player
) {
    public static PlayerContext from(EventRouter.EventContext context) {
        var ref = context.getRef();
        var store = context.getStore();
        var playerRef = store.getComponent(
                ref,
                PlayerRef.getComponentType()
        );
        assert playerRef != null;
        var player = store.getComponent(ref, Player.getComponentType());
        assert player != null;
        return new PlayerContext(ref, store, playerRef, player);
    }

    public boolean doesPlayerHavePermission(String permission) {
        var timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(
                new Date()
        );

        player.sendMessage(
                Message.raw(
                        "[" +
                                timestamp +
                                "][DEBUG] doesPlayerHavePermission: " +
                                permission
                )
        );

        boolean hasPermission = player.hasPermission(permission);
        if (!hasPermission) {
            player.sendMessage(
                    Message.raw("You don't have the permission for this.")
            );
        }

        return hasPermission;
    }
}