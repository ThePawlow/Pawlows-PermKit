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
import de.thepawlow.permkit.PermKitPermissions;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

import javax.annotation.Nonnull;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PermissionManagePage extends InteractiveCustomUIPage<PermissionManagePage.BindingData> {
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

    public PermissionManagePage(@Nonnull PlayerRef playerRef) {
        super(playerRef, CustomPageLifetime.CanDismiss, BindingData.CODEC);
    }

    @Override
    public void build(@Nonnull Ref<EntityStore> ref, @Nonnull UICommandBuilder uiCommandBuilder, @Nonnull UIEventBuilder uiEventBuilder, @Nonnull Store<EntityStore> store) {
        uiCommandBuilder.append("UI/PermissionManagePage.ui");
        
//        uiEventBuilder.addEventBinding(
//            CustomUIEventBindingType.Activating,
//            "#ButtonSave",
//            new EventData().append("Action", Actions.Save)
//        );
//
//        uiEventBuilder.addEventBinding(
//            CustomUIEventBindingType.Activating,
//            "#ButtonDiscard",
//            new EventData().append("Action", Actions.Discard)
//        );
        var dynamicEntryList = "#DynamicEntryList";
        uiCommandBuilder.clear(dynamicEntryList);
        for (int i = 0; i < 20; i++) {
            uiCommandBuilder.append(dynamicEntryList, "UI/dynamic/EntryItem.ui");
        }
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
