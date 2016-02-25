package fr.thewinuxs.bungeegroups;

import java.io.IOException;
import java.util.logging.Logger;

import fr.thewinuxs.bungeegroups.commands.GroupsCommand;
import fr.thewinuxs.bungeegroups.config.Config;
import fr.thewinuxs.bungeegroups.data.TypeData;
import fr.thewinuxs.bungeegroups.data.mysql.MySQL;
import fr.thewinuxs.bungeegroups.listener.Join;
import fr.thewinuxs.bungeegroups.listener.Left;
import fr.thewinuxs.bungeegroups.listener.Modify;
import fr.thewinuxs.bungeegroups.manager.GroupsManager;
import fr.thewinuxs.bungeegroups.manager.PlayersManager;
import fr.thewinuxs.bungeegroups.Metrics;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

public class Core extends Plugin {

	private static Core instance;
	public static final Logger log = Logger.getLogger("Minecraft");
	
	private static Config config;
	private static GroupsManager gm;
	private static PlayersManager pm;

	@Override
	public void onEnable() {

		instance = this;

		if (!getDataFolder().exists()) {
			getDataFolder().mkdir();
		}

		config = new Config();
		config.load();

		gm = new GroupsManager();
		pm = new PlayersManager();
		
		if (config.getTypeData() == TypeData.MYSQL) {

			MySQL.connect();
			MySQL.createTable();
		}

		registerListeners(this, new Join(), new Left(), new Modify());
		registerCommands(this, new GroupsCommand("bgroup"));

		try {
			Metrics metrics = new Metrics(this);
			metrics.start();
		} catch (IOException e) {
			// Error when submit the stats
			log.warning("Error when submit the stats to Metrics.");
		}
	}

	@Override
	public void onDisable() {

		instance = null;
	}

	public static Core getInstance() {
		return instance;
	}

	private void registerListeners(Plugin plugin, Listener... listeners) {
		for (Listener listener : listeners) {
			ProxyServer.getInstance().getPluginManager()
					.registerListener(plugin, listener);
		}
	}

	private void registerCommands(Plugin plugin, Command... commands) {
		for (Command command : commands) {
			ProxyServer.getInstance().getPluginManager()
					.registerCommand(plugin, command);
		}
	}

	public static Config getConfig() {
		return config;
	}

	public static GroupsManager getGroupsManager() {
		return gm;
	}
	public static PlayersManager getPlayersManager() {
		return pm;
	}

}
