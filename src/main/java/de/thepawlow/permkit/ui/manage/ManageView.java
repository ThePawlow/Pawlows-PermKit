package de.thepawlow.permkit.ui.manage;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.protocol.packets.interface_.CustomUIEventBindingType;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.ui.builder.UIEventBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import de.thepawlow.permkit.ui.EventHandles;
import de.thepawlow.permkit.ui.PlayerContext;
import de.thepawlow.permkit.ui.manage.pages.Category;
import de.thepawlow.permkit.ui.manage.pages.Overview;
import de.thepawlow.permkit.ui.manage.pages.Permission;
import dev.jonrapp.hytaleReactiveUi.events.EventBinding;
import dev.jonrapp.hytaleReactiveUi.pages.ReactiveUiPage;
import javax.annotation.Nonnull;

public class ManageView extends ReactiveUiPage {

    public ManageView(@Nonnull PlayerRef playerRef) {
        super(playerRef, CustomPageLifetime.CanDismiss);
    }

    @Override
    public void build(
        @Nonnull Ref<EntityStore> ref,
        @Nonnull UICommandBuilder commandBuilder,
        @Nonnull UIEventBuilder events,
        @Nonnull Store<EntityStore> store
    ) {
        commandBuilder.append("UI/manage/View.ui");

        bindEvent(
            CustomUIEventBindingType.Activating,
            "#ButtonDiscard",
            events,
            EventBinding.action("Clicked:#ButtonDiscard").onEvent(context ->
                EventHandles.DiscardToMainView(PlayerContext.from(context))
            )
        );

        bindEvent(
            CustomUIEventBindingType.Activating,
            "#ButtonCategory",
            events,
            EventBinding.action("Clicked:#ButtonCategory").onEvent(context ->
                interactionCategory(PlayerContext.from(context))
            )
        );

        bindEvent(
            CustomUIEventBindingType.Activating,
            "#ButtonPermissions",
            events,
            EventBinding.action("Clicked:#ButtonPermissions").onEvent(context ->
                interactionPermission(PlayerContext.from(context))
            )
        );

        showPrimaryElement(new Overview(this));
    }

    private void interactionPermission(PlayerContext context) {
        showPrimaryElement(new Permission(this));
        sendUpdate(new UICommandBuilder(), new UIEventBuilder(), false);
    }

    private void interactionCategory(PlayerContext context) {
        showPrimaryElement(new Category(this));
        sendUpdate(new UICommandBuilder(), new UIEventBuilder(), false);
    }

    @Override
    public String getRootContentSelector() {
        return "#Content";
    }
}
