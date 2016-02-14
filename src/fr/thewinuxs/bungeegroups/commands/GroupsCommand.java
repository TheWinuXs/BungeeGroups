package fr.thewinuxs.bungeegroups.commands;

import fr.thewinuxs.bungeegroups.manager.Group;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class GroupsCommand extends Command {

	public GroupsCommand(String name) {
		super(name);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {

		if (args.length < 1) {

			if (!hasPermission(sender, "bgroups.help")) {
				sender.sendMessage("§cYou don't have permission to execute this command !");
				return;
			}
			sendHelp(sender);
			return;
		}

		else if (args.length > 0) {

			if (args[0].equalsIgnoreCase("help")) {
				if (!hasPermission(sender, "bgroups.help")) {
					sender.sendMessage("§cYou don't have permission to execute this command !");
					return;
				}
				sendHelp(sender);
				return;
			} else if (args[0].equalsIgnoreCase("create")) {
				if (!hasPermission(sender, "bgroups.help")) {
					sender.sendMessage("§cYou don't have permission to execute this command !");
					return;
				}
				if (!(args.length > 1)) {
					sender.sendMessage("§cError: /bgroups create <group-name>");
					return;
				}
				
				if (Group.exist(args[1])) {
					sender.sendMessage("§cThis group already exist !");
					return;
				}
				
				Group g = new Group(args[1]);
				sender.sendMessage("§aYou have successfully created the group: §2" + g.getName());
				return;	
				
			}

		} else {

			
			
			
			return;
		}

		/*
		 * Main Command
		 */

	}

	@SuppressWarnings("deprecation")
	private void sendHelp(CommandSender sender) {

		sender.sendMessage("---------------------------");
		sender.sendMessage(" /bgroups help");
		sender.sendMessage(" /bgroups create <group-name>");
		sender.sendMessage(" /bgroups delete <group-name>");
		sender.sendMessage(" /bgroups set <player> <group-name>");
		sender.sendMessage(" /bgroups save §8- Save data");
		sender.sendMessage("---------------------------");

	}

	private boolean hasPermission(CommandSender sender, String permission) {
		if (sender instanceof ProxiedPlayer
				&& !sender.hasPermission(permission)) {
			return false;
		}
		return true;
	}

}
