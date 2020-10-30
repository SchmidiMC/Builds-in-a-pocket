package org.anirudh_noel.schemaman;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.world.World;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SchematicLoader {

   public static void load(File file, World world, BlockVector3 pos) {
      
      file = new File("D:\\Server\\SpigotServer\\plugins\\WorldEdit\\schematics\\test.schem");
      ClipboardFormat format = ClipboardFormats.findByFile(file);
      try (ClipboardReader reader = format.getReader(new FileInputStream(file))) {

         Clipboard clipboard = reader.read();

         try (EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(world, -1)) {

            Operation operation = new ClipboardHolder(clipboard)
                 .createPaste(editSession)
//                 .to(BlockVector3.at(x, y, z))
                 .to(pos)
                 .ignoreAirBlocks(false)
                 .build();
            Operations.complete(operation);
         }

      } catch (IOException | WorldEditException e) {
         e.printStackTrace();
      }

   }



}
