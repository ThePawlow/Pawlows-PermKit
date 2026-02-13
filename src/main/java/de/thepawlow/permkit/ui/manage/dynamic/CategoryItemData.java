package de.thepawlow.permkit.ui.manage.dynamic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CategoryItemData {
    private String title;
    private List<String> permissions = new ArrayList<>();

    public CategoryItemData(String title) {
        this.title = title;
    }

    public CategoryItemData(String title, List<String> permissions) {
        this.title = title;
        this.permissions = permissions;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public List<String> getPermissions() {
        return Collections.unmodifiableList(permissions);
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = new ArrayList<>(permissions);
    }
}