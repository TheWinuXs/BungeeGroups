package fr.thewinuxs.bungeegroups.bungeecord.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import fr.thewinuxs.bungeegroups.bungeecord.Core;
import fr.thewinuxs.bungeegroups.bungeecord.data.TypeData;
import fr.thewinuxs.bungeegroups.bungeecord.data.mysql.MySQL;

public class Config {

	private TypeData typedata;

	private File file = new File(Core.getInstance().getDataFolder(), "config.yml");
	private Configuration config;

	private boolean debug = false;

	public void load() {

		// typedata.setTypeData(TypeData.FILE);

		if (!file.exists()) {
			InputStream localInputStream = Core.getInstance().getResourceAsStream("config.yml");
			try {
				Files.copy(localInputStream, file.toPath(), new CopyOption[0]);
			} catch (IOException localIOException) {
				localIOException.printStackTrace();
				return;
			}
		}

		try {
			config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		
		debug = config.getBoolean("Debug");
		

		String type = config.getString("DataStore.Type").toUpperCase();
		
		try {

			typedata = TypeData.valueOf(type);

		} catch (NullPointerException e) {
			// e.printStackTrace();
			System.out.print("The DataStore: " + config.getString("DataStore.Type") + " doesn't exist !");
			return;
		}

		if (typedata == TypeData.MYSQL) {

			MySQL.Host = config.getString("DataStore.MySQL.Host");
			MySQL.User = config.getString("DataStore.MySQL.User");
			MySQL.Password = config.getString("DataStore.MySQL.Password");
			MySQL.Database = config.getString("DataStore.MySQL.Database");
			MySQL.Port = config.getInt("DataStore.MySQL.Port");
			MySQL.TimeOut = config.getInt("DataStore.MySQL.TimeOut");
		}

	}

	public boolean getDebugMode() {
		return debug;
	}

	public TypeData getTypeData() {
		return typedata;
	}

}
