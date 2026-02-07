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
        Overview,
        Category,
        Permissions
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
        uiCommandBuilder.append("UI/PermissionManagePage.ui");

        System.out.println("CurrentView.name()");
        System.out.println(CurrentView.name());

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

        buildDynamicPage(uiCommandBuilder);

//        var dynamicEntryList = "#DynamicEntryList";
//        uiCommandBuilder.clear(dynamicEntryList);
//        for (int i = 0; i < 20; i++) {
//            uiCommandBuilder.append(dynamicEntryList, "UI/dynamic/EntryItem.ui");
//        }
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
            default:
                player.sendMessage(Message.raw("Pressed invalid Action"));
                break;
        }
    }

    private void buildDynamicPage(UICommandBuilder uiCommandBuilder) {
        uiCommandBuilder.clear(this.SelectorDynamicContent);

        var documentPath = "UI/pages/PermissionManagePage";
        switch (CurrentView) {
            case Category:
                documentPath += "Category.ui";
                break;
            case Overview:
                documentPath += "Overview.ui";
                break;
            case Permissions:
                documentPath += "Permissions.ui";
                break;
            default:
                break;
        }

        uiCommandBuilder.append(this.SelectorDynamicContent, documentPath);
    }

    private void interactionBrowseByPermissions(Ref<EntityStore> ref, Store<EntityStore> store) {
        Player player = store.getComponent(ref, Player.getComponentType());
        assert player != null;

        var timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        player.sendMessage(Message.raw("[" + timestamp + "] " + "Pressing BrowseByPermissions"));

        this.CurrentView = Views.Permissions;
        var uiCommandBuilder = new UICommandBuilder();
        buildDynamicPage(uiCommandBuilder);

        sendUpdate(uiCommandBuilder, new UIEventBuilder(), false);
    }

    private void interactionBrowseByCategory(Ref<EntityStore> ref, Store<EntityStore> store) {
        Player player = store.getComponent(ref, Player.getComponentType());
        assert player != null;

        var timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        player.sendMessage(Message.raw("[" + timestamp + "] " + "Pressing BrowseByCategory"));

        this.CurrentView = Views.Category;
        var uiCommandBuilder = new UICommandBuilder();
        buildDynamicPage(uiCommandBuilder);

        sendUpdate(uiCommandBuilder, new UIEventBuilder(), false);
    }
    //endregion
}