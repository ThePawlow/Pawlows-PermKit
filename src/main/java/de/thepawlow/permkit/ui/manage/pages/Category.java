package de.thepawlow.permkit.ui.manage.pages;

import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.ui.builder.UIEventBuilder;
import de.thepawlow.permkit.ui.manage.ManageView;
import de.thepawlow.permkit.ui.manage.ManageViewModel;
import de.thepawlow.permkit.ui.manage.dynamic.CategoryItem;
import de.thepawlow.permkit.ui.manage.dynamic.CategoryItemData;
import dev.jonrapp.hytaleReactiveUi.elements.Element;
import java.util.ArrayList;
import java.util.List;

public class Category extends Element<ManageView> {

    private final ManageViewModel viewModel;

    public Category(ManageView pageRef, ManageViewModel viewModel) {
        super(pageRef);
        this.viewModel = viewModel;
        // TODO - Boilerplate code for loading it actually
        var categories = new ArrayList<CategoryItemData>();
        categories.add(new CategoryItemData("de.lorem.ipsum"));
        categories.add(new CategoryItemData("de.lorem.suppp", List.of(new String[]{"steal", "steal even more"})));
        categories.add(new CategoryItemData( "de.lorem.supp2p", List.of(new String[]{"steal but fancy"})));
        categories.add(new CategoryItemData( "de.lorem.supp2ap"));

        viewModel.setCategories(categories);
    }

    @Override
    protected void onCreate(
        String root,
        UICommandBuilder commands,
        UIEventBuilder events
    ) {
        commands.append(root, "UI/manage/pages/Category.ui");

        int i = 0;
        for (CategoryItemData category : viewModel.getCategories()) {
            HytaleLogger.getLogger().atInfo().log("Created Category " + category.getTitle());
            CategoryItem item = new CategoryItem(pageRef, category);
            item.create("#CategoryList", i, commands, events);
            i++;
        }
    }
}