package org.anirudh_noel.model;

import static org.anirudh_noel.utils.DEFAULT_VALUES.E;
import static org.anirudh_noel.utils.DEFAULT_VALUES.N;
import static org.anirudh_noel.utils.DEFAULT_VALUES.S;
import static org.anirudh_noel.utils.DEFAULT_VALUES.W;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import org.anirudh_noel.Start;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

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

		final String folderLocation = plugin.getDataFolder() + "/schematics";
		File schematicFile = new File(folderLocation + "/" + this.pocketBlockType.getSchematicPath());
		if (schematicFile.exists()) {
			ClipboardFormat format = ClipboardFormats.findByFile(schematicFile);
			try (ClipboardReader reader = format.getReader(new FileInputStream(schematicFile))) {
				clipboard = reader.read();
				return true;
			} catch (IOException e) {
				e.printStackTrace();

				return false;
			}
		} else {
			return false;
		}

	}

	public void place(World world, Location position, Vector direction) {

		try (EditSession editSession = WorldEdit.getInstance().getEditSessionFactory()
				.getEditSession(BukkitAdapter.adapt(world), -1)) {

			ClipboardHolder clipboardHolder = new ClipboardHolder(clipboard);
			int w = Math.floorDiv(clipboard.getDimensions().getX(), 2);

			AffineTransform transform = new AffineTransform();

			if (N.equals(direction)) {
				transform = transform.translate(3, 0, w);
				transform = transform.rotateY(90);

			} else if (E.equals(direction)) {
				transform = transform.translate(-w, 0, 3);
				// transform = transform.rotateY(0);

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
