package fr.thewinuxs.bungeegroups;

import fr.thewinuxs.bungeegroups.commands.GroupsCommand;
import fr.thewinuxs.bungeegroups.listener.Join;
import fr.thewinuxs.bungeegroups.listener.Left;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

public class Core extends Plugin {

	private static Core instance;

	@Override
	public void onEnable() {

		instance = this;

		registerListeners(this, new Join(), new Left());
		registerCommands(this, new GroupsCommand("bgroup"));
		

	}

	@Override
	public void onDisable() {

		instance = null;
	}

	public static Core getInstance() {
		return instance;
	}

	private static void registerListeners(Plugin plugin, Listener... listeners) {
		for (Listener listener : listeners) {
			ProxyServer.getInstance().getPluginManager()
					.registerListener(plugin, listener);
		}
	}
	
	private static void registerCommands(Plugin plugin, Command... commands) {
		for (Command command : commands) {
			ProxyServer.getInstance().getPluginManager().registerCommand(plugin, command);
		}
	}

}
