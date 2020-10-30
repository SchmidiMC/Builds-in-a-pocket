package org.anirudh_noel.utils;

import org.anirudh_noel.Start;
import org.anirudh_noel.model.PocketBlock;
import org.anirudh_noel.model.PocketBlockTypes;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class BukkitRecipeService {

	private Start plugin;

	public BukkitRecipeService(Start plugin) {
		this.plugin = plugin;
		this.addPockBlockItems();
	}

	private void addPockBlockItems() {

		for (PocketBlockTypes type : PocketBlockTypes.values()) {

			ItemStack item = new PocketBlock(type).getItemStack();
			NamespacedKey key = new NamespacedKey(plugin, type.getNamedKey());
			ShapedRecipe recipe = new ShapedRecipe(key, item);
			recipe.shape("BBB", "BCB", "BBB");
			recipe.setIngredient('B', Material.STONE);
			recipe.setIngredient('C', Material.COAL);
			Bukkit.addRecipe(recipe);

		}

	}
}
