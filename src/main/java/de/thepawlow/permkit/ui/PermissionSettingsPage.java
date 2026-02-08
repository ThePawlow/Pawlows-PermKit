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
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

import javax.annotation.Nonnull;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PermissionSettingsPage extends InteractiveCustomUIPage<PermissionSettingsPage.BindingData> {
    //region Construction
    public enum Actions {
        Save,
        Discard
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

    public PermissionSettingsPage(@Nonnull PlayerRef playerRef) {
        // Why CantClose? Changes of Settings are made - We want to be 100% it's intentionally
        super(playerRef, CustomPageLifetime.CantClose, BindingData.CODEC);
    }

    @Override
    public void build(@Nonnull Ref<EntityStore> ref, @Nonnull UICommandBuilder uiCommandBuilder, @Nonnull UIEventBuilder uiEventBuilder, @Nonnull Store<EntityStore> store) {
        uiCommandBuilder.append("UI/settings/Page.ui");
        
        uiEventBuilder.addEventBinding(
            CustomUIEventBindingType.Activating,
            "#ButtonSave",
            new EventData().append("Action", Actions.Save)
        );

        uiEventBuilder.addEventBinding(
            CustomUIEventBindingType.Activating,
            "#ButtonDiscard",
            new EventData().append("Action", Actions.Discard)
        );
    }
    //endregion

    //region Event Handling
    @Override
    public void handleDataEvent(@NonNullDecl Ref<EntityStore> ref, @NonNullDecl Store<EntityStore> store, @NonNullDecl BindingData data) {
        Player player = store.getComponent(ref, Player.getComponentType());
        assert player != null;

        switch (data.action){
            case Actions.Save:
                this.interactionSave(ref, store);
                break;
            case Actions.Discard:
                this.interactionDiscard(ref, store);
                break;
            default:
                player.sendMessage(Message.raw("Pressed invalid Action"));
                break;
        }
    }

    private void interactionDiscard(Ref<EntityStore> ref, Store<EntityStore> store) {
        Player player = store.getComponent(ref, Player.getComponentType());
        assert player != null;

        var timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        player.sendMessage(Message.raw("[" + timestamp + "] " + "Pressing Discard"));

        player.getPageManager().openCustomPage(ref, store, new PermissionMenu(playerRef));
        sendUpdate(new UICommandBuilder(), new UIEventBuilder(), false);
    }

    private void interactionSave(Ref<EntityStore> ref, Store<EntityStore> store) {
        Player player = store.getComponent(ref, Player.getComponentType());
        assert player != null;

        var timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        player.sendMessage(Message.raw("[" + timestamp + "] " + "Pressing Save"));

        sendUpdate(new UICommandBuilder(), new UIEventBuilder(), false);
    }
    //endregion
}
