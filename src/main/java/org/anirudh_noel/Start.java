package org.anirudh_noel;

import org.anirudh_noel.services.PocketBlockService;
import org.anirudh_noel.utils.BukkitRecipeService;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import de.schmidimc.mc.common.annotation.ApiVersion;
import de.schmidimc.mc.common.annotation.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

@Plugin(name = "BuildsInAPocket", apiVersion = ApiVersion.VERSION_1_16, authors = { "Minecraftian14",
		"Schmidi" }, main = "org.anirudh_noel.Start", version = "1.6.13", description = "A fun plugin")
public class Start extends JavaPlugin {

	private BukkitRecipeService recipeService;
	private PocketBlockService pocketBlockService;

	@Override
	public void onEnable() {

		PluginInitializer.init(this);

		this.pocketBlockService = new PocketBlockService(this);
		this.recipeService = new BukkitRecipeService(this);
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Plugin started!");
	}

	@Override
	public void onDisable() {
	}

	public PocketBlockService getPocketBlockService() {
		return pocketBlockService;
	}
	
	public BukkitRecipeService getRecipeService() {
		return recipeService;
	}

}
