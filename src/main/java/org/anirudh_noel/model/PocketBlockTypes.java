package org.anirudh_noel.model;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public enum PocketBlockTypes {

    CANON("test_canon.schem", Material.STONE, ChatColor.BOLD + "" + ChatColor.DARK_PURPLE + "Canon", "place_canon_block", Arrays.asList(ChatColor.LIGHT_PURPLE + "Place this block", "This is another lore")),
    WALL("test_wall.schem", Material.STONE, ChatColor.BOLD + "" + ChatColor.DARK_PURPLE + "Wall", "place_wall_block", Arrays.asList(ChatColor.LIGHT_PURPLE + "Place this block", "What is a lore "));


    private String schematicPath;
    private Material blockType;
    private String displayName;
    private String namedKey;
    private List<String> lore;

    private PocketBlockTypes(String schematicPath, Material blockType, String displayName, String namedKey, List<String> lore) {
        this.schematicPath = schematicPath;
        this.blockType = blockType;
        this.displayName = displayName;
        this.namedKey = namedKey;
        this.lore = lore;
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

    public List<String> getLore() {
        return lore;
    }

}
