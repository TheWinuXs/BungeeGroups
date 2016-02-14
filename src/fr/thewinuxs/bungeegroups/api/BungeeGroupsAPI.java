package fr.thewinuxs.bungeegroups.api;

import java.util.ArrayList;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import fr.thewinuxs.bungeegroups.GPlayer;
import fr.thewinuxs.bungeegroups.Group;

public class BungeeGroupsAPI {

	public static Group getGroup(ProxiedPlayer player) {
		return (GPlayer.getGPlayer(player.getName()) != null) ? GPlayer
				.getGPlayer(player.getName()).getGroup() : null;
	}

	public static Group getGroup(String player) {
		return (GPlayer.getGPlayer(player) != null) ? GPlayer
				.getGPlayer(player).getGroup() : null;
	}

	public static void setGroup(ProxiedPlayer player, String name) {
		if (GPlayer.getGPlayer(player.getName()) != null)
			if (Group.getGroup(name) != null) {
				GPlayer.getGPlayer(player.getName()).setGroup(
						Group.getGroup(name));
			}
	}

	public static void setGroup(String player, String name) {
		if (GPlayer.getGPlayer(player) != null)
			if (Group.getGroup(name) != null) {
				GPlayer.getGPlayer(player).setGroup(Group.getGroup(name));
			}
	}

	public static ArrayList<Group> getGroups(ProxiedPlayer player) {
		return (GPlayer.getGPlayer(player.getName()) != null) ? GPlayer
				.getGPlayer(player.getName()).getGroups() : null;
	}

	public static ArrayList<Group> getGroups(String player) {
		return (GPlayer.getGPlayer(player) != null) ? GPlayer
				.getGPlayer(player).getGroups() : null;
	}

	public static ArrayList<Group> getAllGroups() {
		return Group.getAll();
	}

	public static void deleteAllGroups() {
		Group.deleteAll();
	}

}
