package de.thepawlow.permkit.ui.manage.pages;

import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.ui.builder.UIEventBuilder;
import de.thepawlow.permkit.ui.manage.ManageView;
import de.thepawlow.permkit.ui.manage.ManageViewModel;
import dev.jonrapp.hytaleReactiveUi.elements.Element;

public class Overview extends Element<ManageView> {

    public Overview(ManageView pageRef, ManageViewModel viewModel) {
        super(pageRef);
    }

    @Override
    protected void onCreate(
        String root,
        UICommandBuilder commands,
        UIEventBuilder events
    ) {
        commands.append(root, "UI/manage/pages/Overview.ui");
    }
}
