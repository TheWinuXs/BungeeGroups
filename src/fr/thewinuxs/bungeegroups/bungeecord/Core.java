package fr.thewinuxs.bungeegroups.bungeecord;

import java.io.IOException;
import java.util.logging.Logger;

import fr.thewinuxs.bungeegroups.bungeecord.command.GroupsCommand;
import fr.thewinuxs.bungeegroups.bungeecord.config.Config;
import fr.thewinuxs.bungeegroups.bungeecord.data.TypeData;
import fr.thewinuxs.bungeegroups.bungeecord.data.file.DataFile;
import fr.thewinuxs.bungeegroups.bungeecord.data.mysql.MySQL;
import fr.thewinuxs.bungeegroups.bungeecord.listener.Join;
import fr.thewinuxs.bungeegroups.bungeecord.listener.Left;
import fr.thewinuxs.bungeegroups.bungeecord.listener.Modify;
import fr.thewinuxs.bungeegroups.bungeecord.manager.GroupsManager;
import fr.thewinuxs.bungeegroups.bungeecord.manager.PlayersManager;
import fr.thewinuxs.bungeegroups.bungeecord.utils.Metrics;

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
	private static DataFile dataFile;

	@Override
	public void onEnable() {

		instance = this;

		if (!getDataFolder().exists()) {
			getDataFolder().mkdir();
		}

		config = new Config();
		config.load();

		dataFile = new DataFile();
		dataFile.initFile();

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
			ProxyServer.getInstance().getPluginManager().registerListener(plugin, listener);
		}
	}

	private void registerCommands(Plugin plugin, Command... commands) {
		for (Command command : commands) {
			ProxyServer.getInstance().getPluginManager().registerCommand(plugin, command);
		}
	}

	public static Config getConfig() {
		return config;
	}

	public static DataFile getDataFile() {
		return dataFile;
	}

	public static GroupsManager getGroupsManager() {
		return gm;
	}

	public static PlayersManager getPlayersManager() {
		return pm;
	}

}
