package fr.thewinuxs.bungeegroups.bungeecord.listener;

import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;
import fr.thewinuxs.bungeegroups.bungeecord.GPlayer;

public class Join implements Listener {

	@EventHandler(priority = EventPriority.HIGH)
	public void onJoin(PostLoginEvent e) {

		if (GPlayer.getGPlayer(e.getPlayer().getName()) == null) {
			new GPlayer(e.getPlayer().getName());
		}

	}
}
