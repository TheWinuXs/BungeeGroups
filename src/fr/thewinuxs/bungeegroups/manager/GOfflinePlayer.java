package fr.thewinuxs.bungeegroups.manager;

import java.util.ArrayList;
import java.util.UUID;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class GOfflinePlayer {

	
	private String name;
	private static ArrayList<GOfflinePlayer> players = new ArrayList<>();
	private UUID uuid;
	private ArrayList<Group> groups = new ArrayList<>();

	public GOfflinePlayer(String name) {
		this.name = name;
		
		ProxiedPlayer p = ProxyServer.getInstance().getPlayer(name);
		if (p != null) {
			this.uuid = p.getUniqueId();
		}
		players.add(this);
		
	}
	
	public String getName() {
		return this.name;
	}
	public UUID getUUID() {
		return this.uuid;
	}
	
	public ArrayList<Group> getGroups() {
		return this.groups ;
	}
	
	public Group getGroup() {
		return (this.groups.get(0) != null) ? this.groups.get(0) : null ;
	}
	
	public void remove() {
		players.remove(this);
	}
	
	public static GOfflinePlayer getGOfflinePlayer(String name) {
		for (GOfflinePlayer gop : players) {
			if (gop.equals(name)) {
				return gop;
			}
		}
		return null;
	}

}
