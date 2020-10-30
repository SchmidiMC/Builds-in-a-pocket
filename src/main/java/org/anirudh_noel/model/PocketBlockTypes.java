package org.anirudh_noel.model;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public enum PocketBlockTypes {
	
	CANON("x.schmematic", Material.STONE, ChatColor.BOLD+""+ChatColor.DARK_PURPLE+"Canon", "place_canon_block");

	
	private String schematicPath;
	private Material blockType;
	private String displayName;
	private String namedKey;
	
	private PocketBlockTypes(String schematicPath, Material blockType, String displayName, String namedKey) {
		this.schematicPath = schematicPath;
		this.blockType = blockType;
		this.displayName = displayName;
		this.namedKey = namedKey;
	}
	
	public String getSchematicPath() {
		return schematicPath;
	}
	
	public Material getBlockType() {
		return blockType;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
	public String getNamedKey() {
		return namedKey;
	}
}
