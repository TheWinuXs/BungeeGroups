package fr.thewinuxs.bungeegroups.listener.event;

import net.md_5.bungee.api.plugin.Cancellable;
import net.md_5.bungee.api.plugin.Event;
import fr.thewinuxs.bungeegroups.GPlayer;
import fr.thewinuxs.bungeegroups.Group;

public class PlayerChangeGroupEvent extends Event implements Cancellable {

	private GPlayer gp;
	private Group oldgroup;
	private Group newgroup;
	private boolean cancel;

	public PlayerChangeGroupEvent(GPlayer gp, Group oldgroup, Group newgroup) {
		this.gp = gp;
		this.setOldGroup(oldgroup);
		this.setNewGroup(newgroup);
	}
	
	public GPlayer getGPlayer() {
		return gp;
	}

	public Group getOldGroup() {
		return oldgroup;
	}

	public void setOldGroup(Group oldgroup) {
		this.oldgroup = oldgroup;
	}

	public Group getNewGroup() {
		return newgroup;
	}

	public void setNewGroup(Group newgroup) {
		this.newgroup = newgroup;
	}

	@Override
	public void setCancelled(boolean cancel) {
		this.cancel = cancel;

	}

	@Override
	public boolean isCancelled() {
		return cancel;
	}

}
