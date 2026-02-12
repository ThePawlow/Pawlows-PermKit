package de.thepawlow.permkit.ui.manage;

import de.thepawlow.permkit.ui.EventHandles;
import de.thepawlow.permkit.ui.PlayerContext;
import de.thepawlow.permkit.ui.manage.dynamic.CategoryItemData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManageViewModel {
    private ManageSection currentSection = ManageSection.OVERVIEW;
    private final List<CategoryItemData> categories = new ArrayList<>();
    private ViewUpdateCallback updateCallback;

    public ManageViewModel() {
        loadCategories();
    }

    public void setUpdateCallback(ViewUpdateCallback callback) {
        this.updateCallback = callback;
    }

    // Commands
    public void navigateToPermissions(PlayerContext context) {
        currentSection = ManageSection.PERMISSIONS;
        notifyViewUpdate();
    }

    public void navigateToCategory(PlayerContext context) {
        currentSection = ManageSection.CATEGORY;
        notifyViewUpdate();
    }

    public void discardChanges(PlayerContext context) {
        EventHandles.DiscardToMainView(context);
    }

    // State getters
    public ManageSection getCurrentSection() {
        return currentSection;
    }

    public List<CategoryItemData> getCategories() {
        return Collections.unmodifiableList(categories);
    }

    private void loadCategories() {
        for (int i = 0; i < 20; i++) {
            categories.add(new CategoryItemData(i, "Category " + i));
        }
    }

    private void notifyViewUpdate() {
        if (updateCallback != null) {
            updateCallback.onViewNeedsUpdate();
        }
    }

    @FunctionalInterface
    public interface ViewUpdateCallback {
        void onViewNeedsUpdate();
    }
}