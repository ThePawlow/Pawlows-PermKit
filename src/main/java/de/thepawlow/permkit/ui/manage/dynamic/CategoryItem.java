package de.thepawlow.permkit.ui.manage.dynamic;

import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.ui.builder.UIEventBuilder;
import de.thepawlow.permkit.ui.manage.ManageView;
import dev.jonrapp.hytaleReactiveUi.bindings.UIBindable;
import dev.jonrapp.hytaleReactiveUi.bindings.UIBinding;
import dev.jonrapp.hytaleReactiveUi.elements.Element;

public class CategoryItem extends Element<ManageView> {
    @UIBinding(selector = "#CategoryTitle.TextSpans")
    private UIBindable<String> title;

    private final CategoryItemData data;

    public CategoryItem(ManageView pageRef, CategoryItemData data) {
        super(pageRef);
        this.data = data;
    }

    @Override
    protected void onCreate(String root, UICommandBuilder commands, UIEventBuilder events) {
        commands.append(root, "UI/manage/dynamic/CategoryItem.ui");
        title.set(data.getTitle() + " [" + data.getPermissions().size() + "]", commands);

        if(data.getPermissions().isEmpty())
        {
            HytaleLogger.getLogger().atInfo().log("Created Object has empty permissions");
            // TODO - Iz. empty
            return;
        }

        int i = 0;
        for (String permission : data.getPermissions()) {
            HytaleLogger.getLogger().atInfo().log("Creating PermissionEntry" + permission);
            var item = new PermissionEntry(pageRef, permission);
            item.create("#PermissionEntries", i, commands, events);
            i++;
        }
    }
}