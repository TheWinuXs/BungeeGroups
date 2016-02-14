package fr.thewinuxs.bungeegroups;

import net.md_5.bungee.api.plugin.Plugin;

public class Core extends Plugin {

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
