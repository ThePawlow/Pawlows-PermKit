package de.thepawlow.permkit.ui.main.pages;

import com.hypixel.hytale.protocol.packets.interface_.CustomUIEventBindingType;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.ui.builder.UIEventBuilder;
import de.thepawlow.permkit.Permissions;
import de.thepawlow.permkit.ui.PlayerContext;
import de.thepawlow.permkit.ui.main.MainView;
import de.thepawlow.permkit.ui.manage.ManageView;
import de.thepawlow.permkit.ui.settings.SettingsView;
import dev.jonrapp.hytaleReactiveUi.elements.Element;
import dev.jonrapp.hytaleReactiveUi.events.EventBinding;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Overview extends Element<MainView> {

    public Overview(MainView pageRef) {
        super(pageRef);
    }

    @Override
    protected void onCreate(
        String root,
        UICommandBuilder commands,
        UIEventBuilder events
    ) {
        commands.append(root, "UI/main/pages/Overview.ui");

        bindEvent(
            CustomUIEventBindingType.Activating,
            "#ButtonManage",
            events,
            EventBinding.action("Clicked:ButtonManage").onEvent(context ->
                handleButtonManage(PlayerContext.from(context))
            )
        );

        bindEvent(
            CustomUIEventBindingType.Activating,
            "#ButtonAudit",
            events,
            EventBinding.action("Clicked:ButtonAudit").onEvent(context ->
                handleButtonAudit(PlayerContext.from(context))
            )
        );

        bindEvent(
            CustomUIEventBindingType.Activating,
            "#ButtonSettings",
            events,
            EventBinding.action("Clicked:ButtonSettings").onEvent(context ->
                handleButtonSettings(PlayerContext.from(context))
            )
        );
    }

    private void handleButtonSettings(PlayerContext context) {
        if (!context.doesPlayerHavePermission(Permissions.UI.SETTINGS)) return;

        context
            .player()
            .getPageManager()
            .openCustomPage(
                context.ref(),
                context.store(),
                new SettingsView(context.playerRef())
            );
    }

    private void handleButtonAudit(PlayerContext context) {
        if (!context.doesPlayerHavePermission(Permissions.UI.AUDIT)) return;

        context.player().sendMessage(Message.raw("TODO"));
        sendUpdate(new UICommandBuilder(), new UIEventBuilder(), false);
    }

    private void handleButtonManage(PlayerContext context) {
        if (!context.doesPlayerHavePermission(Permissions.UI.MANAGE)) return;

        context
            .player()
            .getPageManager()
            .openCustomPage(
                context.ref(),
                context.store(),
                new ManageView(context.playerRef())
            );
    }
}
