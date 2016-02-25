package fr.thewinuxs.bungeegroups.manager;

import java.sql.ResultSet;

import fr.thewinuxs.bungeegroups.Group;
import fr.thewinuxs.bungeegroups.data.mysql.MySQL;

public class GroupsManager {

	public boolean exist(Group group) {

		try {

			if (!MySQL.isConnected()) {
				MySQL.connect();
			}

			ResultSet result = MySQL
					.getResult("SELECT Name FROM Groups WHERE Name = '"
							+ group.getName() + "' ");
			if (result.next()) {
				return true;
			}
			return false;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}

	public void init(Group group) {

		try {

			if (!MySQL.isConnected()) {
				MySQL.connect();
			}

			ResultSet result = MySQL
					.getResult("SELECT * FROM Groups WHERE Name = '"
							+ group.getName() + "'");

			if (result.next()) {
				group.setPrefix(result.getString("Prefix"));
				group.setSuffix(result.getString("Suffix"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
