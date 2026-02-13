package de.thepawlow.permkit.ui.manage;

import de.thepawlow.permkit.ui.BaseViewModel;
import de.thepawlow.permkit.ui.EventHandles;
import de.thepawlow.permkit.ui.PlayerContext;
import de.thepawlow.permkit.ui.manage.dynamic.CategoryItemData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManageViewModel extends BaseViewModel<ManageViewModel.Sections> {
    public enum Sections {
        OVERVIEW,
        PERMISSIONS,
        CATEGORY
    }

    private List<CategoryItemData> categories = new ArrayList<>();

    public ManageViewModel() {
        super(Sections.OVERVIEW);
    }

    // Commands
    public void navigateToPermissions(PlayerContext context) {
        setCurrentSection(Sections.PERMISSIONS);
    }

    public void navigateToCategory(PlayerContext context) {
        setCurrentSection(Sections.CATEGORY);
    }

    public void discardChanges(PlayerContext context) {
        EventHandles.DiscardToMainView(context);
    }

    // State getters
    public List<CategoryItemData> getCategories() {
        return Collections.unmodifiableList(categories);
    }

    public void setCategories(List<CategoryItemData> categories) {
        this.categories = categories;
    }
}