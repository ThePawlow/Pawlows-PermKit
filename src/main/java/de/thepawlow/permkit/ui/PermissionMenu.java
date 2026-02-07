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
import de.thepawlow.permkit.PermKitPermissions;

import javax.annotation.Nonnull;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PermissionMenu extends InteractiveCustomUIPage<PermissionMenu.BindingData> {
    //region Construction
    public enum Actions {
        Manage,
        Audit,
        Settings
    }

    private static FunctionCodec<String, Actions> ActionCodec = new FunctionCodec<>(
            Codec.STRING,
            Actions::valueOf,
            Enum::name
    );

    public static class BindingData {
        public Actions action;

        public static final BuilderCodec<BindingData> CODEC = BuilderCodec.builder(BindingData.class, BindingData::new)
                .append(new KeyedCodec<>("Action", ActionCodec),
                        (BindingData o, Actions v) -> o.action = v,
                        (BindingData o) -> o.action)
                .add()
                .build();
    }

    public PermissionMenu(@Nonnull PlayerRef playerRef) {
        super(playerRef, CustomPageLifetime.CanDismiss, BindingData.CODEC);
    }

    @Override
    public void build(@Nonnull Ref<EntityStore> ref, @Nonnull UICommandBuilder uiCommandBuilder, @Nonnull UIEventBuilder uiEventBuilder, @Nonnull Store<EntityStore> store) {
        var version = PermKitPlugin.get().getManifest().getVersion();

        uiCommandBuilder.append("UI/PermissionMenu.ui");
        uiCommandBuilder.set("#Subtitle.Text", "V " + version.toString());

        uiEventBuilder.addEventBinding(
            CustomUIEventBindingType.Activating,
            "#ButtonManage",
            new EventData().append("Action", Actions.Manage)
        );

        uiEventBuilder.addEventBinding(
            CustomUIEventBindingType.Activating,
            "#ButtonAudit",
            new EventData().append("Action", Actions.Audit)
        );

        uiEventBuilder.addEventBinding(
            CustomUIEventBindingType.Activating,
            "#ButtonSettings",
            new EventData().append("Action", Actions.Settings)
        );
    }
    //endregion

    //region Event Handling
    @Override
    public void handleDataEvent(@Nonnull Ref<EntityStore> ref, @Nonnull Store<EntityStore> store, @Nonnull BindingData data) {
        Player player = store.getComponent(ref, Player.getComponentType());
        assert player != null;

        switch (data.action){
            case Actions.Manage:
                this.interactionManage(ref, store);
                break;
            case Actions.Audit:
                this.interactionAudit(ref, store);
                break;
            case Actions.Settings:
                this.interactionSettings(ref, store);
                break;
            default:
                player.sendMessage(Message.raw("Pressed invalid Action"));
                break;
        }
    }

    private void interactionSettings(Ref<EntityStore> ref, Store<EntityStore> store) {
        Player player = store.getComponent(ref, Player.getComponentType());
        assert player != null;

        if (!player.hasPermission(PermKitPermissions.UI.SETTINGS))
        {
            player.sendMessage(Message.raw("Cannot interact with ViewSettings right now."));
            sendUpdate(new UICommandBuilder(), new UIEventBuilder(), false);
            return;
        }

        var timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        player.sendMessage(Message.raw("[" + timestamp + "] " + "Pressing ViewSettings"));

        PermissionSettingsPage settingsPage = new PermissionSettingsPage(playerRef);
        player.getPageManager().openCustomPage(ref, store, settingsPage);
    }

    private void interactionAudit(Ref<EntityStore> ref, Store<EntityStore> store) {
        Player player = store.getComponent(ref, Player.getComponentType());
        assert player != null;

        if (!player.hasPermission(PermKitPermissions.UI.AUDIT))
        {
            player.sendMessage(Message.raw("Cannot interact with ViewAudit right now."));
            sendUpdate(new UICommandBuilder(), new UIEventBuilder(), false);
            return;
        }

        var timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        player.sendMessage(Message.raw("[" + timestamp + "] " + "Pressing ViewAuditLog"));
        sendUpdate(new UICommandBuilder(), new UIEventBuilder(), false);
    }

    private void interactionManage(Ref<EntityStore> ref, Store<EntityStore> store) {
        Player player = store.getComponent(ref, Player.getComponentType());
        assert player != null;

        if (!player.hasPermission(PermKitPermissions.UI.MANAGE))
        {
            player.sendMessage(Message.raw("Cannot interact with ViewMange right now."));
            sendUpdate(new UICommandBuilder(), new UIEventBuilder(), false);
            return;
        }

        var timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        player.sendMessage(Message.raw("[" + timestamp + "] " + "Pressing ViewManage"));
        sendUpdate(new UICommandBuilder(), new UIEventBuilder(), false);
    }
    //endregion
}