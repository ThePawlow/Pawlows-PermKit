package de.thepawlow.permkit.ui.manage.pages;

import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.ui.builder.UIEventBuilder;
import de.thepawlow.permkit.ui.manage.ManageView;
import de.thepawlow.permkit.ui.manage.dynamic.CategoryItem;
import dev.jonrapp.hytaleReactiveUi.elements.Element;

public class Category extends Element<ManageView> {

    public Category(ManageView pageRef) {
        super(pageRef);
    }

    @Override
    protected void onCreate(
        String root,
        UICommandBuilder commands,
        UIEventBuilder events
    ) {
        commands.append(root, "UI/manage/pages/Category.ui");

        var DEBUGMAXENTRIES = 20;
        for (int i = 0; i < DEBUGMAXENTRIES; i++) {
            CategoryItem item = new CategoryItem(pageRef, i);
            item.create("#CategoryList", i, commands, events);
        }
    }
}
