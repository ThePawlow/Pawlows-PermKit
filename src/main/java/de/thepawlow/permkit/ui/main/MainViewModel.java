package de.thepawlow.permkit.ui.main;

import de.thepawlow.permkit.ui.BaseViewModel;

public class MainViewModel extends BaseViewModel<MainViewModel.Sections> {
    public enum Sections {
        OVERVIEW
    }

    public MainViewModel() {
        super(Sections.OVERVIEW);
    }

    // Add your MainViewModel-specific methods here
}