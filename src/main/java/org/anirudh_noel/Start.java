package org.anirudh_noel;

import static de.schmidimc.mc.common.communication.ChatHelper.say;

import java.io.IOException;

import org.anirudh_noel.services.PocketBlockService;
import org.anirudh_noel.utils.BukkitRecipeService;
import org.anirudh_noel.utils.SchematicLoader;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import de.schmidimc.mc.common.annotation.ApiVersion;
import de.schmidimc.mc.common.annotation.plugin.Plugin;
import de.schmidimc.mc.common.communication.ChatHelper;

@Plugin(name = Start.pluginName, apiVersion = ApiVersion.VERSION_1_16, authors = { "Minecraftian14",
		"Schmidi" }, main = "org.anirudh_noel.Start", version = "1.6.13", description = "A fun plugin")
public class Start extends JavaPlugin {

	private BukkitRecipeService recipeService;
	private PocketBlockService pocketBlockService;
	private SchematicLoader schematicLoader;
	protected static final String pluginName = "BuildsInAPocket";
	
	private final ConsoleCommandSender console = Bukkit.getConsoleSender();
	
	@Override
	public void onEnable() {
		this.setDefaultPrefix();
		say(console, "Starting to load plugin...");
		
		this.schematicLoader = new SchematicLoader(this);

		try {
			say(console, "Starting to setup data folder and schematics.");
			this.schematicLoader.setupDataFolder();
			say(console, "Data setup successful.");
		} catch (IOException e) {
			this.setErrorPrefix();
			say(console, "Data setup failed.");
			e.printStackTrace();
			this.setDefaultPrefix();
		}

		say(console, "Starting to load services.");
		this.pocketBlockService = new PocketBlockService(this);
		this.recipeService = new BukkitRecipeService(this);

		say(console, "Services loaded successfully.");
		
		PluginInitializer.init(this);
		say(console, "Plugin successfully started.");
	}

	private void setDefaultPrefix() {
		ChatHelper.setPrefix(ChatColor.BOLD+""+ChatColor.DARK_GREEN+"["+ChatColor.GREEN+pluginName+ChatColor.DARK_GREEN+"]"+ChatColor.BLUE+" >>> ");
	}
	
	private void setErrorPrefix() {
		ChatHelper.setPrefix(ChatColor.BOLD+""+ChatColor.DARK_GREEN+"["+ChatColor.GREEN+pluginName+ChatColor.DARK_GREEN+"]"+ChatColor.RED+" >>> ");
		
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
