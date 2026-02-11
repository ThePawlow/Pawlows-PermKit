package de.thepawlow.permkit.ui.main;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.ui.builder.UIEventBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import de.thepawlow.permkit.PermKitPlugin;
import de.thepawlow.permkit.ui.main.pages.Overview;
import dev.jonrapp.hytaleReactiveUi.bindings.UIBindable;
import dev.jonrapp.hytaleReactiveUi.bindings.UIBinding;
import dev.jonrapp.hytaleReactiveUi.pages.ReactiveUiPage;
import javax.annotation.Nonnull;

public class MainView extends ReactiveUiPage {

    public MainView(@Nonnull PlayerRef playerRef) {
        super(playerRef, CustomPageLifetime.CanDismiss);
    }

    @Override
    public void build(
        @Nonnull Ref<EntityStore> ref,
        @Nonnull UICommandBuilder commandBuilder,
        @Nonnull UIEventBuilder events,
        @Nonnull Store<EntityStore> store
    ) {
        commandBuilder.append("UI/main/View.ui");
        commandBuilder.set("#Subtitle.Text", "V " + PermKitPlugin.get().getManifest().getVersion().toString());

        showPrimaryElement(new Overview(this));
    }

    @Override
    public String getRootContentSelector() {
        return "#Content";
    }
}
