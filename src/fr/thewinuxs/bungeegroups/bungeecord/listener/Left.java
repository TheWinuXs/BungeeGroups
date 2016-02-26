package fr.thewinuxs.bungeegroups.bungeecord.listener;

import fr.thewinuxs.bungeegroups.bungeecord.GPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

public class Left implements Listener {

	@EventHandler(priority = EventPriority.NORMAL)
	public void onLeft(PlayerDisconnectEvent e) {
		if (GPlayer.getGPlayer(e.getPlayer().getName()) != null) {
			GPlayer.getGPlayer(e.getPlayer().getName()).remove();
		}
	}

}
