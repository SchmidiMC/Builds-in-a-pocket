package org.anirudh_noel.events;

import org.anirudh_noel.Start;
import org.anirudh_noel.schemaman.Schemas;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.util.Vector;

import static org.anirudh_noel.utils.DEFAULT_VALUES.*;

@de.schmidimc.mc.common.annotation.Listener
public class TestEvent implements Listener {

   Start plugin;

   public TestEvent(Start plugin) {
      this.plugin = plugin;
   }

   @EventHandler
   public void onPlayerJoin(BlockPlaceEvent event) {

      System.out.println("Block placed... " + event.getBlockPlaced().getState().toString());

      if (!event.getBlockPlaced().getType().equals(Material.DIAMOND_BLOCK)) return;

      Location position = event.getBlockPlaced().getLocation();
      Vector increment = getDirection(event.getPlayer());

//      for (int i = 0; i < 10; i++) {
//         event.getPlayer().getWorld().getBlockAt(position).setType(Material.DIAMOND_BLOCK);
//         position.add(increment);
//      }

//      SchematicLoader.load(null, BukkitAdapter.adapt(event.getPlayer().getWorld()), BukkitAdapter.asBlockVector(position));
      Schemas.WOODEN_MANUAL_CANNON.place(event.getPlayer().getWorld(),position, getDirection(event.getPlayer()));

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
