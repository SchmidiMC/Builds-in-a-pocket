package org.anirudh_noel.model;

import org.anirudh_noel.utils.DEFAULT_VALUES;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PocketBlock {
	
	private Location loc;
	private PocketBlockTypes pocketBlockType;
	private ItemStack itemStack;
	
	
	public PocketBlock(PocketBlockTypes pocketBlockType) {
		this(pocketBlockType, null);
	}
	
	
	public PocketBlock(PocketBlockTypes pocketBlockType, Location loc) {
		this.pocketBlockType = pocketBlockType;
		this.loc = loc;
		this.itemStack = new ItemStack(this.pocketBlockType.getBlockType());
		ItemMeta meta = this.itemStack.getItemMeta();
		meta.setDisplayName(this.pocketBlockType.getDisplayName());
		meta.setLore(DEFAULT_VALUES.DEFAULT_LORE);
		this.itemStack.setItemMeta(meta);
	}
	
	public Location getLoc() {
		return loc;
	}
	
	public PocketBlockTypes getPocketBlockType() {
		return pocketBlockType;
	}
	
	public ItemStack getItemStack() {
		return itemStack;
	}
	

}
