package de.thepawlow.permkit;

public final class Permissions {

    private Permissions() {}

    public static final class UI {

        public static final String BASE = "de.thepawlow.permkit.ui"; // Allows - Opening the UI
        public static final String ANY = UI.BASE + ".*"; // Allows - Everything
        public static final String SETTINGS = UI.BASE + ".settings"; // Allows - Opening the Settings Page
        public static final String AUDIT = UI.BASE + ".audit"; // Allows - Opening the Audit Page
        public static final String MANAGE = UI.BASE + ".manage"; // Allows - Opening the Manage Page
    }

    //    public static final class ADMIN {
    //        public static final String RELOAD = "de.thepawlow.permkit.admin.reload";
    //        public static final String GRANT = "de.thepawlow.permkit.admin.grant";
    //    }
}
