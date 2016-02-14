package fr.thewinuxs.bungeegroups.manager;

import java.util.ArrayList;
import java.util.UUID;

import net.md_5.bungee.api.connection.ProxiedPlayer;

public class GPlayer {

	private static ArrayList<GPlayer> players = new ArrayList<>();

	UUID uuid;
	ProxiedPlayer player;
	ArrayList<Group> groups = new ArrayList<>();
	String prefix;
	String suffix;

	public GPlayer(ProxiedPlayer player) {
		this.player = player;
		this.uuid = player.getUniqueId();

		players.add(this);
	}

	public ProxiedPlayer getPlayer() {
		return this.player;
	}
	
	public String getName() {
		return this.player.getName();
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
	
	public static GPlayer getGPlayer(ProxiedPlayer player) {
		for (GPlayer gp : players) {
			if (gp.getPlayer() == player) {
				return gp;
			}
		}
		return null;
	}

}
