package de.thepawlow.permkit.commands;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import de.thepawlow.permkit.Permissions;
import de.thepawlow.permkit.ui.main.MainView;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

public class MenuShowCommand extends AbstractPlayerCommand {

    public MenuShowCommand() {
        super("show", "commands.permissionmenu.list.desc");
    }

    @Override
    protected void execute(
        @NonNullDecl CommandContext commandContext,
        @NonNullDecl Store<EntityStore> store,
        @NonNullDecl Ref<EntityStore> ref,
        @NonNullDecl PlayerRef playerRef,
        @NonNullDecl World world
    ) {
        Player player = store.getComponent(ref, Player.getComponentType());
        assert player != null;

        if (!player.hasPermission(Permissions.UI.BASE)) {
            return;
        }

        MainView page = new MainView(playerRef);
        player.getPageManager().openCustomPage(ref, store, page);
    }
}
