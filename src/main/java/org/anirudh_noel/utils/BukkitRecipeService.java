package org.anirudh_noel.utils;

import org.anirudh_noel.Start;
import org.anirudh_noel.model.PocketBlock;
import org.anirudh_noel.model.PocketBlockTypes;
import org.anirudh_noel.services.PocketBlockService;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class BukkitRecipeService {

	private Start plugin;
	private PocketBlockService pocketBlockService;

	public BukkitRecipeService(Start plugin) {
		this.plugin = plugin;
		this.pocketBlockService = plugin.getPocketBlockService();
		this.addPockBlockItems();
	}

	private void addPockBlockItems() {

		for (PocketBlockTypes type : PocketBlockTypes.values()) {

			PocketBlock pocketBlock = this.pocketBlockService.createPocketBlock(PocketBlockTypes.CANON);
			ItemStack item  = pocketBlock.getItemStack();
			NamespacedKey key = new NamespacedKey(plugin, type.getNamedKey());
			ShapedRecipe recipe = new ShapedRecipe(key, item);
			recipe.shape("BBB", "BCB", "BBB");
			recipe.setIngredient('B', Material.STONE);
			recipe.setIngredient('C', Material.COAL);
			Bukkit.addRecipe(recipe);

		}

	}
}
