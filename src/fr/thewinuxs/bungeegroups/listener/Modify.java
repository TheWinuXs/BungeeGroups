package fr.thewinuxs.bungeegroups.listener;

import java.util.HashMap;

import fr.thewinuxs.bungeegroups.manager.Group;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class Modify implements Listener {
	
	public static HashMap<ProxiedPlayer, Group> prefix = new HashMap<>();
	public static HashMap<ProxiedPlayer, Group> suffix = new HashMap<>();

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onWrite(ChatEvent e) {

		if (e.getSender() instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) e.getSender();
			if (prefix.get(p) != null) {
				prefix.get(p).setPrefix(e.getMessage());
				p.sendMessage("§aThe prefix for the group §2"+ prefix.get(p).getName() +" has been successfully changed !");
				prefix.remove(p);
				return;
			} else if (suffix.get(p) != null) {
				suffix.get(p).setSuffix(e.getMessage());
				p.sendMessage("§aThe suffix for the group §2"+ suffix.get(p).getName() +" has been successfully changed !");
				suffix.remove(p);
				return;
			}
		} 
	}
	
}
