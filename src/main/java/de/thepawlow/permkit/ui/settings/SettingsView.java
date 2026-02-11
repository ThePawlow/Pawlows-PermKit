package de.thepawlow.permkit.ui.settings;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.protocol.packets.interface_.CustomUIEventBindingType;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.ui.builder.UIEventBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import javax.annotation.Nonnull;
import de.thepawlow.permkit.ui.EventHandles;
import de.thepawlow.permkit.ui.PlayerContext;
import dev.jonrapp.hytaleReactiveUi.events.EventBinding;
import dev.jonrapp.hytaleReactiveUi.pages.ReactiveUiPage;

public class SettingsView extends ReactiveUiPage {

    public SettingsView(@Nonnull PlayerRef playerRef) {
        // Why CantClose? Changes of Settings are made - We want to be 100% it's intentionally
        super(playerRef, CustomPageLifetime.CantClose);
    }

    @Override
    public void build(
        @Nonnull Ref<EntityStore> ref,
        @Nonnull UICommandBuilder commandBuilder,
        @Nonnull UIEventBuilder events,
        @Nonnull Store<EntityStore> store
    ) {
        commandBuilder.append("UI/settings/View.ui");

        bindEvent(
            CustomUIEventBindingType.Activating,
            "#ButtonSave",
            events,
            EventBinding.action("Clicked:#ButtonSave").onEvent(context ->
                interactionSave(PlayerContext.from(context))
            )
        );

        bindEvent(
            CustomUIEventBindingType.Activating,
            "#ButtonDiscard",
            events,
            EventBinding.action("Clicked:#ButtonDiscard").onEvent(context ->
                EventHandles.DiscardToMainView(PlayerContext.from(context))
            )
        );
    }

    private void interactionSave(PlayerContext context) {
        context.player().sendMessage(
            Message.raw("Pressing Save")
        );

        sendUpdate(new UICommandBuilder(), new UIEventBuilder(), false);
    }

    @Override
    public String getRootContentSelector() {
        return "#Content";
    }
}
