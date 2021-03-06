package fr.thewinuxs.bungeegroups.bungeecord.manager;

import java.sql.ResultSet;
import java.util.UUID;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import fr.thewinuxs.bungeegroups.bungeecord.Core;
import fr.thewinuxs.bungeegroups.bungeecord.data.mysql.MySQL;

public class PlayersManager {

	public static boolean isInDatabase(String player) {
		try {
			if (!MySQL.isConnected()) {
				MySQL.connect();
			}

			// Get the UUID with String
			UUID uuid = UUID.randomUUID();

			ResultSet rs = MySQL.getResult("SELECT * FROM Players WHERE PlayerUUID = '" + uuid + "'");
			if (rs.next()) {
				return true;
			}

			return false;
		} catch (Exception e) {
			if (Core.getConfig().getDebugMode())
				e.getStackTrace();
		}

		return false;
	}

	public static void addPlayerToDatabase(ProxiedPlayer player) {
		try {
			if (!MySQL.isConnected()) {
				MySQL.connect();
			}
			MySQL.update("INSERT INTO Players (PlayerName, PlayerUUID) VALUES ('" + player.getName() + "', '"
					+ player.getUniqueId().toString() + "')");
		} catch (Exception e) {
			if (Core.getConfig().getDebugMode())
				e.getStackTrace();
		}
	}

}
