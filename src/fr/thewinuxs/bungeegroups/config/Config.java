package fr.thewinuxs.bungeegroups.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import fr.thewinuxs.bungeegroups.Core;
import fr.thewinuxs.bungeegroups.data.TypeData;
import fr.thewinuxs.bungeegroups.data.mysql.MySQL;

public class Config {

	static String type = "FILE";

	static File file = new File(Core.getInstance().getDataFolder(),
			"config.yml");
	static Configuration config;

	public static boolean debug = false;

	public static void load() {

		if (!file.exists()) {
			InputStream localInputStream = Core.getInstance()
					.getResourceAsStream("config.yml");
			try {
				Files.copy(localInputStream, file.toPath(), new CopyOption[0]);
			} catch (IOException localIOException) {
				localIOException.printStackTrace();
				return;
			}
		}

		try {
			config = ConfigurationProvider.getProvider(YamlConfiguration.class)
					.load(file);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		type = config.getString("DataStore.Type").toUpperCase();
		
		try {
			
			TypeData.setTypeData(TypeData.valueOf(config.getString("DataStore.Type").toUpperCase()));
		
		} catch (NullPointerException e) {
//			e.printStackTrace();
			System.out.print("The DataStore: " + config.getString("DataStore.Type") + " doesn't exist !");
			return;
		}
		
		if (TypeData.getTypeData() == TypeData.MYSQL) {
			
			MySQL.Host = config.getString("DataStore.MySQL.Host");
			MySQL.User = config.getString("DataStore.MySQL.User");
			MySQL.Password = config.getString("DataStore.MySQL.Password");
			MySQL.Database = config.getString("DataStore.MySQL.Database");
			MySQL.Port = config.getInt("DataStore.MySQL.Port");
		}

	}

}
