package de.thepawlow.permkit.ui.manage.dynamic;

import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.ui.builder.UIEventBuilder;
import de.thepawlow.permkit.ui.manage.ManageView;
import dev.jonrapp.hytaleReactiveUi.bindings.UIBindable;
import dev.jonrapp.hytaleReactiveUi.bindings.UIBinding;
import dev.jonrapp.hytaleReactiveUi.elements.Element;

public class CategoryItem extends Element<ManageView> {

    private final int index;

//    @UIBinding(selector = "#ItemIndex.TextSpans")
//    private UIBindable<String> itemIndex;

    public CategoryItem(ManageView pageRef, int index) {
        super(pageRef);
        this.index = index;
    }

    @Override
    protected void onCreate(String root, UICommandBuilder commands, UIEventBuilder events) {
        commands.append(root, "UI/manage/dynamic/CategoryItem.ui");

        // Set the index value (batched with creation)
//        itemIndex.set(String.valueOf(index), commands);
    }
}