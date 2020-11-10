package org.anirudh_noel.events;

import de.schmidimc.mc.common.annotation.Listener;
import org.anirudh_noel.Start;
import org.anirudh_noel.model.PocketBlock;
import org.anirudh_noel.services.PocketBlockService;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import static org.anirudh_noel.utils.DEFAULT_VALUES.*;

@Listener
public class BlockPlaceListener implements org.bukkit.event.Listener {

    private Start plugin;
    private PocketBlockService pocketBlockService;

    public BlockPlaceListener(Start plugin) {
        this.plugin = plugin;
        this.pocketBlockService = this.plugin.getPocketBlockService();
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        // TODO identify way for placed block
//        if (event.getBlock().getBlockData().getMaterial() != PocketBlockTypes.CANON.getBlockType())
//            return;

//        if (!pocketBlockService.contains(event.getItemInHand())) return;

        PocketBlock block = pocketBlockService.getPocketBlock(event.getItemInHand());
        if (block == null) return;

        block
                .place(
                        event.getBlock().getWorld(),
                        event.getBlockPlaced().getLocation(),
                        getDirection(event.getPlayer())
                );
    }

    @EventHandler
    public void onBlockInteract(PlayerInteractEvent event) {

//        event.getClickedBlock()

    }

    public static Vector getDirection(Player player) {

        double rotation = (player.getLocation().getYaw() - 90) % 360;
        if (rotation < 0)
            rotation += 360.0;

        if (0 <= rotation && rotation < 45)
            return N;
        else if (45 <= rotation && rotation < 135)
            return E;
        else if (135 <= rotation && rotation < 225)
            return S;
        else if (225 <= rotation && rotation < 315)
            return W;
        else
            return N;
    }
}
