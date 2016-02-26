package fr.thewinuxs.bungeegroups.spigot;

import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {

	private static Core instance;

	@Override
	public void onEnable() {

		instance = this;

	}

	@Override
	public void onDisable() {
		instance = null;

	}

	public static Core getInstance() {
		return instance;
	}

}
