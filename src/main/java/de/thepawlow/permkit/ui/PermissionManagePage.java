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

public class PermissionManagePage extends InteractiveCustomUIPage<PermissionManagePage.BindingData> {
    //region Construction
    public enum Views {
        Overview("Overview.ui"),
        Discard("Overview.ui"),
        Category("Category.ui"),
        Permissions("Permissions.ui");

        private final String uiFileName;

        Views(String uiFileName) {
            this.uiFileName = uiFileName;
        }

        public String getDocumentPath() {
            return "UI/manage/pages/" + uiFileName;
        }
    }

    public Views CurrentView = Views.Overview;

    public String SelectorDynamicContent = "#DynamicContent";

    private static FunctionCodec<String, Views> ViewCodec = new FunctionCodec<>(
            Codec.STRING,
            Views::valueOf,
            Enum::name
    );

    public static class BindingData {
        public Views action;

        public static final BuilderCodec<BindingData> CODEC = BuilderCodec.builder(BindingData.class, BindingData::new)
                .append(new KeyedCodec<>("Action", ViewCodec),
                        (BindingData o, Views v) -> o.action = v,
                        (BindingData o) -> o.action)
                .add()
                .build();
    }

    public PermissionManagePage(@Nonnull PlayerRef playerRef) {
        super(playerRef, CustomPageLifetime.CanDismiss, BindingData.CODEC);
    }

    @Override
    public void build(@Nonnull Ref<EntityStore> ref, @Nonnull UICommandBuilder uiCommandBuilder, @Nonnull UIEventBuilder uiEventBuilder, @Nonnull Store<EntityStore> store) {
        uiCommandBuilder.append("UI/manage/Page.ui");

        uiEventBuilder.addEventBinding(
                CustomUIEventBindingType.Activating,
                "#ButtonCategory",
                new EventData().append("Action", Views.Category)
        );

        uiEventBuilder.addEventBinding(
                CustomUIEventBindingType.Activating,
                "#ButtonPermissions",
                new EventData().append("Action", Views.Permissions)
        );

        uiEventBuilder.addEventBinding(
                CustomUIEventBindingType.Activating,
                "#ButtonPermissions",
                new EventData().append("Action", Views.Discard)
        );

        buildDynamicPage(uiCommandBuilder);
    }
    //endregion

    //region Event Handling
    @Override
    public void handleDataEvent(@NonNullDecl Ref<EntityStore> ref, @NonNullDecl Store<EntityStore> store, @NonNullDecl BindingData data) {
        Player player = store.getComponent(ref, Player.getComponentType());
        assert player != null;

        switch (data.action){
            case Views.Category:
                this.interactionBrowseByCategory(ref, store);
                break;
            case Views.Permissions:
                this.interactionBrowseByPermissions(ref, store);
                break;
            case Discard:
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

        player.getPageManager().openCustomPage(ref, store, new PermissionMenu(playerRef));
        sendUpdate(new UICommandBuilder(), new UIEventBuilder(), false);
    }

    private void buildDynamicPage(UICommandBuilder uiCommandBuilder) {
        uiCommandBuilder.clear(this.SelectorDynamicContent);
        uiCommandBuilder.append(this.SelectorDynamicContent, CurrentView.getDocumentPath());
    }

    private void handleViewChange(Ref<EntityStore> ref, Store<EntityStore> store, Views view)
    {
        Player player = store.getComponent(ref, Player.getComponentType());
        assert player != null;

        var timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        player.sendMessage(Message.raw("[" + timestamp + "] Pressed " + view.name()));

        this.CurrentView = view;
        var uiCommandBuilder = new UICommandBuilder();
        buildDynamicPage(uiCommandBuilder);

        switch (view){
            case Views.Category:
                var dynamicEntryList = "#DynamicEntryList";
                    uiCommandBuilder.clear(dynamicEntryList);
                    for (int i = 0; i < 20; i++) {
                    uiCommandBuilder.append(dynamicEntryList, "UI/manage/dynamic/EntryItem.ui");
                }
                break;
        }

        sendUpdate(uiCommandBuilder, new UIEventBuilder(), false);
    }

    private void interactionBrowseByPermissions(Ref<EntityStore> ref, Store<EntityStore> store) {
        handleViewChange(ref, store, Views.Permissions);
    }

    private void interactionBrowseByCategory(Ref<EntityStore> ref, Store<EntityStore> store) {
        handleViewChange(ref, store, Views.Category);
    }
    //endregion
}