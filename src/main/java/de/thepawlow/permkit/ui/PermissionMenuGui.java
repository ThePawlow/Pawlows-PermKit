package de.thepawlow.permkit.ui;


import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.codec.function.FunctionCodec;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.protocol.packets.interface_.CustomUIEventBindingType;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.entity.entities.player.pages.InteractiveCustomUIPage;
import com.hypixel.hytale.server.core.ui.builder.EventData;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.ui.builder.UIEventBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import de.thepawlow.permkit.PermKitPlugin;

import javax.annotation.Nonnull;

public class PermissionMenuGui extends InteractiveCustomUIPage<PermissionMenuGui.BindingData> {

    public PermissionMenuGui(@Nonnull PlayerRef playerRef, @Nonnull CustomPageLifetime lifetime) {
        super(playerRef, lifetime, BindingData.CODEC);
    }

    private static FunctionCodec<String, InteractionActions> InteractionActionsCodec = new FunctionCodec<>(
            Codec.STRING,
            InteractionActions::valueOf,
            Enum::name
    );

    public static class BindingData {
        public InteractionActions action;

        public static final BuilderCodec<BindingData> CODEC = BuilderCodec.builder(BindingData.class, BindingData::new)
                .append(new KeyedCodec<>("Action", InteractionActionsCodec),
                        (BindingData o, InteractionActions v) -> o.action = v,
                        (BindingData o) -> o.action)
                .add()
                .build();
    }

    @Override
    public void build(@Nonnull Ref<EntityStore> ref, @Nonnull UICommandBuilder uiCommandBuilder, @Nonnull UIEventBuilder uiEventBuilder, @Nonnull Store<EntityStore> store) {
        var version = PermKitPlugin.get().getManifest().getVersion();
        var author = PermKitPlugin.get().getManifest().getAuthors().getFirst().getName();
        var footerTitle = "By " + author + " V" + version;

        uiCommandBuilder.append("UI/PermissionMenu.ui");
        // TODO - Get an instance of the Plugin to access its Version and Author
        uiCommandBuilder.set("#FooterTitle.Text", footerTitle);

        uiEventBuilder.addEventBinding(
            CustomUIEventBindingType.Activating,
            "#ButtonManage",
            new EventData().append("Action", InteractionActions.ViewManage)
        );

        uiEventBuilder.addEventBinding(
            CustomUIEventBindingType.Activating,
            "#ButtonAudit",
            new EventData().append("Action", InteractionActions.ViewAuditLog)
        );

        uiEventBuilder.addEventBinding(
            CustomUIEventBindingType.Activating,
            "#ButtonSettings",
            new EventData().append("Action", InteractionActions.ViewSettings)
        );
    }

    @Override
    public void handleDataEvent(@Nonnull Ref<EntityStore> ref, @Nonnull Store<EntityStore> store, @Nonnull BindingData data) {
        Player player = store.getComponent(ref, Player.getComponentType());
        assert player != null;

        switch (data.action){
            case InteractionActions.ViewManage:
                this.handleInteractionActionManage(ref, store);
                break;
            case InteractionActions.ViewAuditLog:
                this.handleInteractionActionAudit(ref, store);
                break;
            case InteractionActions.ViewSettings:
                this.handleInteractionActionSettings(ref, store);
                break;
            default:
                player.sendMessage(Message.raw("Pressed invalid Action"));
                break;
        }

        sendUpdate(new UICommandBuilder(), new UIEventBuilder(), false);
    }

    private void handleInteractionActionSettings(Ref<EntityStore> ref, Store<EntityStore> store) {
        Player player = store.getComponent(ref, Player.getComponentType());
        assert player != null;

        player.sendMessage(Message.raw("Pressing ViewSettings"));
    }

    private void handleInteractionActionAudit(Ref<EntityStore> ref, Store<EntityStore> store) {
        Player player = store.getComponent(ref, Player.getComponentType());
        assert player != null;

        player.sendMessage(Message.raw("Pressing ViewAuditLog"));
    }

    private void handleInteractionActionManage(Ref<EntityStore> ref, Store<EntityStore> store) {
        Player player = store.getComponent(ref, Player.getComponentType());
        assert player != null;

        player.sendMessage(Message.raw("Pressing ViewManage"));
    }
}