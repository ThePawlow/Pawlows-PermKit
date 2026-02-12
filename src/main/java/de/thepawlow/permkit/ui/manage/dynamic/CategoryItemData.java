package de.thepawlow.permkit.ui.manage.dynamic;

public class CategoryItemData {
    private final int id;
    private String name;
    private String description;
    private boolean isEnabled;
    private int itemCount;

    public CategoryItemData(int id, String name) {
        this.id = id;
        this.name = name;
        this.description = "";
        this.isEnabled = true;
        this.itemCount = 0;
    }

    // Full constructor if you need more control
    public CategoryItemData(int id, String name, String description, boolean isEnabled, int itemCount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isEnabled = isEnabled;
        this.itemCount = itemCount;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public int getItemCount() {
        return itemCount;
    }

    // Setters (if you need mutable data)
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
}