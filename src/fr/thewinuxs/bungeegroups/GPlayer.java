package fr.thewinuxs.bungeegroups;

import java.util.ArrayList;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class GPlayer {

	private static ArrayList<GPlayer> players = new ArrayList<>();

	private ProxiedPlayer player;
	private String name;
	private ArrayList<Group> groups = new ArrayList<>();
	private boolean online = false;

	public GPlayer(String name) {
		this.name = name;

		ProxiedPlayer player = ProxyServer.getInstance().getPlayer(name);
		if (player != null) {
			this.player = player;
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

		// On r�cup�re dans la bdd

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
