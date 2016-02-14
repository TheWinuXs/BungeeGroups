package fr.thewinuxs.bungeegroups.listener;

import fr.thewinuxs.bungeegroups.manager.GPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class Left implements Listener {

	@EventHandler
	public void onLeft(PlayerDisconnectEvent e) {
		if (GPlayer.getGPlayer(e.getPlayer()) != null) {
			GPlayer.getGPlayer(e.getPlayer()).remove();
		}
	}
	
	
}
