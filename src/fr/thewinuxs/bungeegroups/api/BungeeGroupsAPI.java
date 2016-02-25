package fr.thewinuxs.bungeegroups.api;

import java.util.ArrayList;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import fr.thewinuxs.bungeegroups.GPlayer;
import fr.thewinuxs.bungeegroups.Group;
import fr.thewinuxs.bungeegroups.listener.event.PlayerChangeGroupEvent;

public class BungeeGroupsAPI {

	public static ArrayList<Group> getGroups(ProxiedPlayer player) {
		if (player == null)
			throw new NullPointerException("Player cannot be null !");
		GPlayer gp = GPlayer.getGPlayer(player.getName());
		if (gp == null)
			gp = new GPlayer(player.getName());
		return gp.getGroups();
	}

	public static ArrayList<Group> getGroups(String player) {
		if (player == null || player.isEmpty())
			throw new NullPointerException("Player cannot be null or empty !");
		GPlayer gp = GPlayer.getGPlayer(player);
		if (gp == null)
			gp = new GPlayer(player);
		return gp.getGroups();
	}

	public static Group getGroup(ProxiedPlayer player) {
		if (player == null)
			throw new NullPointerException("Player cannot be null !");
		GPlayer gp = GPlayer.getGPlayer(player.getName());
		if (gp == null)
			gp = new GPlayer(player.getName());
		return gp.getGroup();
	}

	@SuppressWarnings("unused")
	public static Group getGroup(String player) {
		if (player == null || player.isEmpty())
			throw new NullPointerException("Player cannot be null or empty !");
		GPlayer gp = GPlayer.getGPlayer(player);
		if (gp == null)
			gp = new GPlayer(player);
		if (gp == null)
			throw new NullPointerException("Player doesn't exist");
		return gp.getGroup();
	}

	@SuppressWarnings("unused")
	public static void setGroup(ProxiedPlayer player, String name) {
		GPlayer gp = GPlayer.getGPlayer(player.getName());
		if (gp == null)
			gp = new GPlayer(player.getName());
		if (gp == null)
			throw new NullPointerException("Player doesn't exist !");
		Group group = Group.getGroup(name);
		if (group == null)
			group = new Group(name);
		if (group == null)
			throw new NullPointerException("Group doesn't exist !");
		Group oldgroup = gp.getGroup();
		gp.setGroup(group);
		PlayerChangeGroupEvent event = new PlayerChangeGroupEvent(gp, oldgroup,
				group);
		ProxyServer.getInstance().getPluginManager().callEvent(event);
	}

	@SuppressWarnings("unused")
	public static void setGroup(String player, String name) {
		GPlayer gp = GPlayer.getGPlayer(player);
		if (gp == null)
			gp = new GPlayer(player);
		if (gp == null)
			throw new NullPointerException("Player doesn't exist !");
		Group group = Group.getGroup(name);
		if (group == null)
			group = new Group(name);
		if (group == null)
			throw new NullPointerException("Group doesn't exist !");
		Group oldgroup = gp.getGroup();
		gp.setGroup(group);
		PlayerChangeGroupEvent event = new PlayerChangeGroupEvent(gp, oldgroup,
				group);
		ProxyServer.getInstance().getPluginManager().callEvent(event);
	}

	public static ArrayList<Group> getAllGroups() {
		return Group.getAll();
	}

	public static void deleteAllGroups() {
		Group.deleteAll();
	}

}
