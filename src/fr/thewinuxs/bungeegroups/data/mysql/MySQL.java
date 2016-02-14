package fr.thewinuxs.bungeegroups.data.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import fr.thewinuxs.bungeegroups.config.Config;

import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MySQL {

	private static int time = 0;
	private static int timeout = 2;

	public static String Host;
	public static String User;
	public static String Password;
	public static String Database;
	public static int Port;

	public static Connection con;

	public static void connect() {
		if (!isConnected()) {

			if (time <= timeout) {

				System.out.println("MySQL Connection ...");

				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}

				try {

					con = DriverManager.getConnection("jdbc:mysql://" + Host
							+ ":" + Port + "/" + Database, User, Password);

					System.out.println("MySQL successfully connected !");

					time = 0;

				} catch (SQLException e) {

					time = +1;

					if (time < timeout) {
						System.out.println("*********** Error ***********");
						System.out.println("* MySQL ERROR Connection !! *");
						System.out.println("* Reconnecting ...          *");
						System.out.println("*****************************");

						connect();
					} else if (time == timeout) {
						System.out.println("************* Error *************");
						System.out.println("*   MySQL ERROR Connection !!   *");
						System.out.println("* Plugin BungeeGroups Disabling.*");
						System.out.println("*********************************");

						if (Config.debug) {
							e.getStackTrace();
						}

						time = timeout + 1;
					}

				}

			}
		}
	}

	public static void close() {
		if (isConnected()) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean isConnected() {
		return con != null;
	}

	public static void createTable() {
		if (isConnected()) {
			try {
				con.createStatement()
						.executeUpdate(
								"CREATE TABLE IF NOT EXISTS Friends (PlayerName TEXT,PlayerUUID TEXT,FriendName TEXT,FriendUUID TEXT,id int(11) NOT NULL auto_increment,primary KEY (id));");
				con.createStatement()
						.executeUpdate(
								"CREATE TABLE IF NOT EXISTS Invite (PlayerUUID TEXT,FriendUUID TEXT,id int(11) NOT NULL auto_increment,primary KEY (id));");
				con.createStatement()
						.executeUpdate(
								"CREATE TABLE IF NOT EXISTS Players (PlayerName TEXT,PlayerUUID TEXT,id int(11) NOT NULL auto_increment,primary KEY (id));");
				con.createStatement()
						.executeUpdate(
								"CREATE TABLE IF NOT EXISTS Settings (PlayerName TEXT,PlayerUUID TEXT,getMsg int(12),getJoinMsg int(12),getInvites int(12),id int(11) NOT NULL auto_increment,primary KEY (id));");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void update(String qry) {
		if (!isConnected()) {
			connect();
		}
		try {
			con.createStatement().executeUpdate(qry);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static ResultSet getResult(String qry) {
		if (!isConnected()) {
			connect();
		}
		try {
			return con.createStatement().executeQuery(qry);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean isInDatabase(String player) {
		try {
			if (!isConnected()) {
				connect();
			}

			// Get the UUID with String
			UUID uuid = UUID.randomUUID();

			ResultSet rs = getResult("SELECT * FROM Players WHERE PlayerUUID = '"
					+ uuid + "'");
			if (rs.next()) {
				return true;
			}

			return false;
		} catch (Exception e) {
			if (Config.debug)
				e.getStackTrace();
		}

		return false;
	}

	public static void addPlayerToDatabase(ProxiedPlayer player) {
		try {
			if (!isConnected()) {
				connect();
			}
			update("INSERT INTO Players (PlayerName, PlayerUUID) VALUES ('"
					+ player.getName() + "', '"
					+ player.getUniqueId().toString() + "')");
		} catch (Exception e) {
			if (Config.debug)
				e.getStackTrace();
		}
	}

}
