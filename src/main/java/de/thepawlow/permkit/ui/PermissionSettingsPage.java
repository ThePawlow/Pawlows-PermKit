package de.thepawlow.permkit.ui;

import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.server.core.entity.entities.player.pages.InteractiveCustomUIPage;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.ui.builder.UIEventBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nonnull;

public class PermissionSettingsPage extends InteractiveCustomUIPage<PermissionSettingsPage.BindingData> {

    public PermissionSettingsPage(@Nonnull PlayerRef playerRef) {
        super(playerRef, CustomPageLifetime.CanDismiss, BindingData.CODEC);
    }

    @Override
    public void build(@Nonnull Ref<EntityStore> ref, @Nonnull UICommandBuilder uiCommandBuilder, @Nonnull UIEventBuilder uiEventBuilder, @Nonnull Store<EntityStore> store) {
        uiCommandBuilder.append("UI/PermissionSettingsPage.ui");
    }

    public static class BindingData {
        public InteractionActions action;

        public static final BuilderCodec<PermissionSettingsPage.BindingData> CODEC = BuilderCodec.builder(PermissionSettingsPage.BindingData.class, PermissionSettingsPage.BindingData::new)
//                .append(new KeyedCodec<>("Action", InteractionActionsCodec),
//                        (PermissionMenu.BindingData o, InteractionActions v) -> o.action = v,
//                        (PermissionMenu.BindingData o) -> o.action)
//                .add()
                .build();
    }
}
