package org.anirudh_noel.schemaman;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.transform.AffineTransform;
import com.sk89q.worldedit.session.ClipboardHolder;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public enum Schemas {

   WOODEN_MANUAL_CANNON("D:\\Server\\SpigotServer\\plugins\\WorldEdit\\schematics\\test.schem");

   Clipboard clipboard;

   Schemas(String path) {

      File file = new File(path);
      ClipboardFormat format = ClipboardFormats.findByFile(file);
      try (ClipboardReader reader = format.getReader(new FileInputStream(file))) {
         clipboard = reader.read();
      } catch (IOException e) {
         e.printStackTrace();
      }

   }

   public void place(World world, Location position, Vector direction) {

      try (EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(BukkitAdapter.adapt(world), -1)) {

         ClipboardHolder clipboardHolder = new ClipboardHolder(clipboard);

         clipboardHolder.setTransform(new AffineTransform().rotateY(90));

         Operation operation = clipboardHolder
              .createPaste(editSession)
              .to(BukkitAdapter.asBlockVector(position))
              .ignoreAirBlocks(false)
              .build();


         Operations.complete(operation);

      } catch (WorldEditException e) {
         e.printStackTrace();
      }

   }

}
