package de.thepawlow.permkit.ui;

public abstract class BaseViewModel<TSection extends Enum<TSection>> {
    private TSection currentSection;
    private ViewUpdateCallback updateCallback;

    protected BaseViewModel(TSection initialSection) {
        this.currentSection = initialSection;
    }

    public void setUpdateCallback(ViewUpdateCallback callback) {
        this.updateCallback = callback;
    }

    public TSection getCurrentSection() {
        return currentSection;
    }

    protected void setCurrentSection(TSection section) {
        this.currentSection = section;
        notifyViewUpdate();
    }

    protected void notifyViewUpdate() {
        if (updateCallback != null) {
            updateCallback.onViewNeedsUpdate();
        }
    }

    @FunctionalInterface
    public interface ViewUpdateCallback {
        void onViewNeedsUpdate();
    }
}