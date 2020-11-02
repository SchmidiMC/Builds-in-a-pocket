package org.anirudh_noel.model;

import static org.anirudh_noel.utils.DEFAULT_VALUES.E;
import static org.anirudh_noel.utils.DEFAULT_VALUES.N;
import static org.anirudh_noel.utils.DEFAULT_VALUES.S;
import static org.anirudh_noel.utils.DEFAULT_VALUES.W;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.anirudh_noel.Start;
import org.anirudh_noel.utils.DEFAULT_VALUES;
import org.bukkit.Chunk;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;

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

public class PocketBlock {

	private PocketBlockTypes pocketBlockType;
	private ItemStack itemStack;
	private Clipboard clipboard;

	public PocketBlock(PocketBlockTypes pocketBlockType) {
		this.pocketBlockType = pocketBlockType;
		this.itemStack = new ItemStack(this.pocketBlockType.getBlockType());
		System.out.println("this itemstack : " + itemStack);
		ItemMeta meta = this.itemStack.getItemMeta();
		meta.setDisplayName(this.pocketBlockType.getDisplayName());
		meta.setLore(pocketBlockType.getLore());
		this.itemStack.setItemMeta(meta);
	}

	/**
	 * 
	 * @param plugin
	 * @return true if the registration was successful
	 */
	public boolean registerClipboard(Start plugin) {
		System.out.println("in register");
		final String folderLocation = plugin.getDataFolder() + "/schematics";
		File file = new File(folderLocation);
		if (file.exists()) {

			file = new File(folderLocation + "/" + this.pocketBlockType.getSchematicPath());
			if (file.exists()) {

				ClipboardFormat format = ClipboardFormats.findByFile(file);
				try (ClipboardReader reader = format.getReader(new FileInputStream(file))) {
					clipboard = reader.read();
					return true;
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("schematic not there");
			}

		} else {
			System.out.println("file no there");
			
			System.out.println(file.mkdirs());
		}
		return false;

	}

	public void place(World world, Location position, Vector direction) {

		try (EditSession editSession = WorldEdit.getInstance().getEditSessionFactory()
				.getEditSession(BukkitAdapter.adapt(world), -1)) {

			ClipboardHolder clipboardHolder = new ClipboardHolder(clipboard);
			int w = Math.floorDiv(clipboard.getDimensions().getX(), 2);

			AffineTransform transform = new AffineTransform();

			/*  */
			if (N.equals(direction)) {
				transform = transform.translate(3, 0, w);
				transform = transform.rotateY(90);

			} else if (E.equals(direction)) {
				transform = transform.translate(-w, 0, 3);
//	            transform = transform.rotateY(0);

			} else if (S.equals(direction)) {
				transform = transform.translate(-3, 0, -w);
				transform = transform.rotateY(-90);

			} else if (W.equals(direction)) {
				transform = transform.translate(w, 0, -3);
				transform = transform.rotateY(180);

			}

			clipboardHolder.setTransform(transform);

			Operation operation = clipboardHolder.createPaste(editSession).to(BukkitAdapter.asBlockVector(position))
					.ignoreAirBlocks(false).build();

			Operations.complete(operation);

		} catch (WorldEditException e) {
			e.printStackTrace();
		}

	}

	public PocketBlockTypes getPocketBlockType() {
		return pocketBlockType;
	}

	public ItemStack getItemStack() {
		return this.itemStack;
	}

}
