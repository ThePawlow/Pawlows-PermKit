package de.thepawlow.permkit.ui.manage.pages;

import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.ui.builder.UIEventBuilder;
import de.thepawlow.permkit.ui.manage.ManageView;
import dev.jonrapp.hytaleReactiveUi.elements.Element;

public class Permission extends Element<ManageView> {

    public Permission(ManageView pageRef) {
        super(pageRef);
    }

    @Override
    protected void onCreate(
        String root,
        UICommandBuilder commands,
        UIEventBuilder events
    ) {
        commands.append(root, "UI/manage/pages/Permission.ui");
    }
}
