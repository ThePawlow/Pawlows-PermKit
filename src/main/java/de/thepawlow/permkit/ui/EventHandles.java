package de.thepawlow.permkit.ui;

import de.thepawlow.permkit.ui.main.MainView;

public class EventHandles {
    public static void DiscardToMainView(PlayerContext context) {
        context
            .player()
            .getPageManager()
            .openCustomPage(
                context.ref(),
                context.store(),
                new MainView(context.playerRef())
            );
    }
}
