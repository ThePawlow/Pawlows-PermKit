package de.thepawlow.permkit.ui.manage.dynamic;

import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.ui.builder.UIEventBuilder;
import de.thepawlow.permkit.ui.manage.ManageView;
import dev.jonrapp.hytaleReactiveUi.bindings.UIBindable;
import dev.jonrapp.hytaleReactiveUi.bindings.UIBinding;
import dev.jonrapp.hytaleReactiveUi.elements.Element;

public class PermissionEntry extends Element<ManageView> {
    @UIBinding(selector = "#PermissionEntry.TextSpans")
    private UIBindable<String> permissionLabel;
    private final String permission;

    public PermissionEntry(ManageView pageRef, String permission) {
        super(pageRef);
        this.permission = permission;
    }

    @Override
    protected void onCreate(String root, UICommandBuilder commands, UIEventBuilder events) {
        commands.append(root, "UI/manage/dynamic/CategoryEntryItem.ui");
        permissionLabel.set(permission, commands);
    }
}
