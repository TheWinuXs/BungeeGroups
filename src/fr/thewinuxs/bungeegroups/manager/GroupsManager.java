package fr.thewinuxs.bungeegroups.manager;

import java.sql.ResultSet;

import fr.thewinuxs.bungeegroups.Core;
import fr.thewinuxs.bungeegroups.Group;
import fr.thewinuxs.bungeegroups.data.mysql.MySQL;

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

			// TODO File

		}
		return false;
	}

	public void init(Group group) {

		switch (Core.getConfig().getTypeData()) {
		case MYSQL:
			try {

				if (!MySQL.isConnected()) {
					MySQL.connect();
				}

				ResultSet result = MySQL.getResult("SELECT * FROM Groups WHERE Name = '" + group.getName() + "'");

				if (result.next()) {
					group.setPrefix(result.getString("Prefix"));
					group.setSuffix(result.getString("Suffix"));
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case FILE:

			// TODO File

		}
	}
}
