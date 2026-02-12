package de.thepawlow.permkit.ui.manage.pages;

import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.ui.builder.UIEventBuilder;
import de.thepawlow.permkit.ui.manage.ManageView;
import de.thepawlow.permkit.ui.manage.ManageViewModel;
import de.thepawlow.permkit.ui.manage.dynamic.CategoryItem;
import de.thepawlow.permkit.ui.manage.dynamic.CategoryItemData;
import dev.jonrapp.hytaleReactiveUi.elements.Element;

public class Category extends Element<ManageView> {
    private final ManageViewModel viewModel;

    public Category(ManageView pageRef, ManageViewModel viewModel) {
        super(pageRef);
        this.viewModel = viewModel;
    }

    @Override
    protected void onCreate(
            String root,
            UICommandBuilder commands,
            UIEventBuilder events
    ) {
        commands.append(root, "UI/manage/pages/Category.ui");

        int index = 0;
        for (CategoryItemData data : viewModel.getCategories()) {
            CategoryItem item = new CategoryItem(pageRef, data);
            item.create("#CategoryList", index++, commands, events);
        }
    }
}