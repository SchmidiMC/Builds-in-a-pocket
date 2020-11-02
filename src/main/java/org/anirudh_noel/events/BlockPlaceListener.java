package org.anirudh_noel.events;

import static org.anirudh_noel.utils.DEFAULT_VALUES.E;
import static org.anirudh_noel.utils.DEFAULT_VALUES.N;
import static org.anirudh_noel.utils.DEFAULT_VALUES.S;
import static org.anirudh_noel.utils.DEFAULT_VALUES.W;

import org.anirudh_noel.Start;
import org.anirudh_noel.model.PocketBlockTypes;
import org.anirudh_noel.services.PocketBlockService;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.util.Vector;

import de.schmidimc.mc.common.annotation.Listener;

@Listener
public class BlockPlaceListener implements org.bukkit.event.Listener {

	private Start plugin;
	private PocketBlockService pocketBlockService;
	
	public BlockPlaceListener(Start plugin) {
		this.plugin = plugin;
		this.plugin.getPocketBlockService();
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		//TODO identify way for placed block
		if(event.getBlock().getBlockData().getMaterial() != PocketBlockTypes.CANON.getBlockType()) return;

	      Location position = event.getBlockPlaced().getLocation();
	      Vector increment = getDirection(event.getPlayer());
	      
	      this.pocketBlockService.getPlacedPocketBlocks().get(0).place(event.getBlock().getWorld(), position, getDirection(event.getPlayer()));
	   }

	   public static Vector getDirection(Player player) {

	      double rotation = (player.getLocation().getYaw() - 90) % 360;
	      if (rotation < 0) rotation += 360.0;

	      if (0 <= rotation && rotation < 45) return N;
	      else if (45 <= rotation && rotation < 135) return E;
	      else if (135 <= rotation && rotation < 225) return S;
	      else if (225 <= rotation && rotation < 315) return W;
	      else return N;
	   }
}
