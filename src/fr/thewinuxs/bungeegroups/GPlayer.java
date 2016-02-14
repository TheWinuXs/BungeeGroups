package fr.thewinuxs.bungeegroups;

import java.util.ArrayList;
import java.util.UUID;


import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class GPlayer {

	private static ArrayList<GPlayer> players = new ArrayList<>();

	ProxiedPlayer player;
	String name;
	UUID uuid;
	ArrayList<Group> groups = new ArrayList<>();
	String prefix;
	String suffix;
	boolean online = false;

	public GPlayer(String pname) {
		this.name = pname;
		this.uuid = player.getUniqueId();

		ProxiedPlayer p = ProxyServer.getInstance().getPlayer(name);
		if (p != null) {
			this.player = p;
			this.online = true;
		}

		players.add(this);
	}

	public ProxiedPlayer getPlayer() {
		return this.player;
	}

	public String getName() {
		return this.name;
	}

	public ArrayList<Group> getGroups() {
		return this.groups;
	}

	public Group getGroup() {
		return (this.groups.get(0) != null) ? this.groups.get(0) : null;
	}

	public void remove() {
		players.remove(this);
	}

	public void init() {

		// On récupère dans la bdd

	}

	public void save() {
		// On save dans la bdd

	}

	public static void saveAll() {
		for (GPlayer gp : GPlayer.getAll()) {
			gp.save();
		}
	}

	/*
	 * public static GPlayer getGPlayer(ProxiedPlayer player) { for (GPlayer gp
	 * : players) { if (gp.getPlayer() != null && gp.getPlayer() == player) {
	 * return gp; } } return null; }
	 */

	public static GPlayer getGPlayer(String pname) {
		for (GPlayer gp : players) {
			if (gp.getName().equals(pname)) {
				return gp;
			}
		}
		return null;
	}

	public static boolean exist(String pname) {
		return getGPlayer(pname) != null;
	}

	public static ArrayList<GPlayer> getAll() {
		return players;
	}

	public void setGroup(Group group) {
		this.groups.set(0, group);

	}

	public boolean isOnline() {
		return this.online;
	}

	public static void removeAll() {
		players.clear();
	}

}
