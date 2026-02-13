package de.thepawlow.permkit.ui.manage.dynamic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CategoryItemData {
    private final int id;
    private String title;
    private List<String> permissions = new ArrayList<>();

    public CategoryItemData(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public CategoryItemData(int id, String title, List<String> permissions) {
        this.id = id;
        this.title = title;
        this.permissions = permissions;
    }

    public int getId() {
        return id;
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