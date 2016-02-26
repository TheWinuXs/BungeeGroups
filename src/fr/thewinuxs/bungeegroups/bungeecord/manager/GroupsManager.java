package fr.thewinuxs.bungeegroups.bungeecord.manager;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import fr.thewinuxs.bungeegroups.bungeecord.Core;
import fr.thewinuxs.bungeegroups.bungeecord.Group;
import fr.thewinuxs.bungeegroups.bungeecord.data.mysql.MySQL;

public class GroupsManager {

	public boolean exist(Group group) {
		switch (Core.getConfig().getTypeData()) {
		case MYSQL:
			try {

				if (!MySQL.isConnected()) {
					MySQL.connect();
				}

				ResultSet result = MySQL.getResult("SELECT Name FROM Groups WHERE Name = '" + group.getName() + "' ");
				if (result.next()) {
					return true;
				}
				return false;

			} catch (Exception e) {
				e.printStackTrace();
			}

			return false;

		case FILE:

			// TODO Possible error !!!

			Configuration config = null;

			try {
				config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(Core.getDataFile().getFileGroups());
				config.getString("Groups." + group.getName());
				return true;

			} catch (IOException e) {
				// e.printStackTrace();
				return false;
			}

		default:
			return false;
		}
	}

	public void init(Group group) {

		switch (Core.getConfig().getTypeData()) {
		case MYSQL:
			try {

				if (!MySQL.isConnected()) {
					MySQL.connect();
				}

				ResultSet result = MySQL.getResult("SELECT * FROM Groups WHERE Name = '" + group.getName() + "'");

				while (result.next()) {
					group.setPrefix(result.getString("Prefix"));
					group.setSuffix(result.getString("Suffix"));
				}
				
				ResultSet result1 = MySQL.getResult("SELECT * FROM Permissions WHERE Group = '" + group.getName() + "'");
				
				List<String> s = new ArrayList<>();
				
				while (result1.next()) {
					
					s.add(result1.getString("Group"));
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			break;

		case FILE:

			// TODO Possible error !!!

			Configuration config = null;

			try {
				config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(Core.getDataFile().getFileGroups());

			} catch (IOException e) {
				// e.printStackTrace();
			}

			group.setPrefix(config.getString("Groups." + group.getName() + ".prefix"));
			group.setSuffix(config.getString("Groups." + group.getName() + ".suffix"));
			group.setPermissions(config.getStringList("Groups." + group.getName() + ".permissions"));
			group.setInheritances(config.getStringList("Groups." + group.getName() + ".inheritances"));

		}
	}
}
