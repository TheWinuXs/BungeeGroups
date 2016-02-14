package fr.thewinuxs.bungeegroups;

import java.util.ArrayList;
import java.util.UUID;

import fr.thewinuxs.bungeegroups.utils.MojangAPI;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class GOfflinePlayer {

	/*
	 * Class unused
	 */
	
	
	
	private String name;
	private static ArrayList<GOfflinePlayer> players = new ArrayList<>();
	private UUID uuid;
	private ArrayList<Group> groups = new ArrayList<>();

	public GOfflinePlayer(String name) {
		name = MojangAPI.getExactName(name);
		
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
	
	public void update() {
		
		
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
