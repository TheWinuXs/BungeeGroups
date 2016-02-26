package fr.thewinuxs.bungeegroups.bungeecord.data.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.thewinuxs.bungeegroups.bungeecord.Core;

public class MySQL {

	private static int time = 0;
	public static int TimeOut = 3;

	public static String Host;
	public static String User;
	public static String Password;
	public static String Database;
	public static int Port;

	public static Connection con;

	public static void connect() {
		if (!isConnected()) {

			if (time <= TimeOut) {

				System.out.println("MySQL Connection ...");

				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}

				try {

					con = DriverManager.getConnection("jdbc:mysql://" + Host + ":" + Port + "/" + Database, User, Password);

					System.out.println("MySQL successfully connected !");

					time = 0;

				} catch (SQLException e) {

					time = +1;

					if (time < TimeOut) {
						System.out.println("*********** Error ***********");
						System.out.println("* MySQL ERROR Connection !! *");
						System.out.println("* Reconnecting ...          *");
						System.out.println("*****************************");

						connect();
					} else if (time == TimeOut) {
						System.out.println("************* Error *************");
						System.out.println("*   MySQL ERROR Connection !!   *");
						System.out.println("* Plugin BungeeGroups Disabling.*");
						System.out.println("*********************************");

						if (Core.getConfig().getDebugMode()) {
							e.getStackTrace();
						}

						time = TimeOut + 1;
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
				if (Core.getConfig().getDebugMode()) {
					e.getStackTrace();
				}
				Core.log.warning("Error when try to close the Database connection !");
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
								"CREATE TABLE IF NOT EXISTS Players (Name TEXT, UUID TEXT, `Group` TEXT, id int(11) NOT NULL auto_increment,primary KEY (id));");
				con.createStatement()
						.executeUpdate(
								"CREATE TABLE IF NOT EXISTS Groups (Name TEXT, Prefix TEXT, Suffix TEXT, id int(11) NOT NULL auto_increment,primary KEY (id));");
				con.createStatement()
						.executeUpdate(
								"CREATE TABLE IF NOT EXISTS Permissions (`Group` TEXT, Permission TEXT, id int(11) NOT NULL auto_increment,primary KEY (id));");
				System.out.println("Table are successfully created !");

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

	public static int getTimeOut() {
		return TimeOut;
	}

}
