package fr.thewinuxs.bungeegroups.api;

import java.util.ArrayList;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import fr.thewinuxs.bungeegroups.manager.GOfflinePlayer;
import fr.thewinuxs.bungeegroups.manager.GPlayer;
import fr.thewinuxs.bungeegroups.manager.Group;

public class BungeeGroupsAPI {

	public static Group getGroup(ProxiedPlayer player) {
		return (GPlayer.getGPlayer(player) != null) ? GPlayer
				.getGPlayer(player).getGroup() : null;
	}

	public static Group getGroup(String player) {
		return (GOfflinePlayer.getGOfflinePlayer(player) != null) ? GOfflinePlayer
				.getGOfflinePlayer(player).getGroup() : null;
	}

	/*
	 * public static void setGroup(ProxiedPlayer player, String name) { if
	 * (GPlayer.getGPlayer(player) != null)
	 * GPlayer.getGPlayer(player).setGroup(); else return; }
	 * 
	 * public static void setGroup(String player, String name) { if
	 * (GOfflinePlayer.getGOfflinePlayer(player) != null)
	 * GOfflinePlayer.getGOfflinePlayer(player).setGroup(); else return; }
	 */

	public static ArrayList<Group> getGroups(ProxiedPlayer player) {
		return (GPlayer.getGPlayer(player) != null) ? GPlayer
				.getGPlayer(player).getGroups() : null;
	}

	public static ArrayList<Group> getGroups(String player) {
		return (GOfflinePlayer.getGOfflinePlayer(player) != null) ? GOfflinePlayer
				.getGOfflinePlayer(player).getGroups() : null;
	}

	public static ArrayList<Group> getAllGroups() {
		return Group.getAll();
	}

	public static void deleteAllGroups() {
		Group.deleteAll();
	}

}
