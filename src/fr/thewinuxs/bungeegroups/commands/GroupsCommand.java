package fr.thewinuxs.bungeegroups.commands;

import fr.thewinuxs.bungeegroups.listener.Modify;
import fr.thewinuxs.bungeegroups.manager.GPlayer;
import fr.thewinuxs.bungeegroups.manager.Group;
import fr.thewinuxs.bungeegroups.utils.MojangAPI;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
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
				if (!hasPermission(sender, "bgroups.create")) {
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
				g.create();

				sender.sendMessage("§aYou have successfully created the group: §2"
						+ args[1]);
				return;

			} else if (args[0].equalsIgnoreCase("delete")) {
				if (!hasPermission(sender, "bgroups.delete")) {
					sender.sendMessage("§cYou don't have permission to execute this command !");
					return;
				}
				if (!(args.length > 1)) {
					sender.sendMessage("§cError: /bgroups delete <group-name>");
					return;
				}

				if (!Group.exist(args[1])) {
					sender.sendMessage("§cThis group doesn't exist !");
					return;
				}

				Group g = Group.getGroup(args[1]);
				g.delete();

			} else if (args[0].equalsIgnoreCase("prefix")) {
				if (!hasPermission(sender, "bgroups.prefix")) {
					sender.sendMessage("§cYou don't have permission to execute this command !");
					return;
				}

				if (!(sender instanceof ProxiedPlayer)) {
					sender.sendMessage("§cThis command is only executable by a player !");
					return;
				}

				if (!(args.length > 1)) {
					sender.sendMessage("§cError: /bgroups prefix <group-name>");
					return;
				}

				if (!Group.exist(args[1])) {
					sender.sendMessage("§cThis group doesn't exist !");
					return;
				}

				Group g = Group.getGroup(args[1]);

				sender.sendMessage("§aEnter the prefix for the group §2"
						+ g.getName());
				Modify.prefix.put((ProxiedPlayer) sender, g);

			} else if (args[0].equalsIgnoreCase("suffix")) {
				if (!hasPermission(sender, "bgroups.suffix")) {
					sender.sendMessage("§cYou don't have permission to execute this command !");
					return;
				}

				if (!(sender instanceof ProxiedPlayer)) {
					sender.sendMessage("§cThis command is only executable by a player !");
					return;
				}

				if (!(args.length > 1)) {
					sender.sendMessage("§cError: /bgroups suffix <group-name>");
					return;
				}

				if (!Group.exist(args[1])) {
					sender.sendMessage("§cThis group doesn't exist !");
					return;
				}

				Group g = Group.getGroup(args[1]);

				sender.sendMessage("§aEnter the suffix for the group §2"
						+ g.getName());
				Modify.suffix.put((ProxiedPlayer) sender, g);

			} else if (args[0].equalsIgnoreCase("set")) {
				if (!hasPermission(sender, "bgroups.set")) {
					sender.sendMessage("§cYou don't have permission to execute this command !");
					return;
				}
				if (!(args.length > 2)) {
					sender.sendMessage("§cError: /bgroups set <player> <group-name>");
					return;
				}

				if (!Group.exist(args[2])) {
					sender.sendMessage("§cThis group doesn't exist !");
					return;
				}

				GPlayer gp = GPlayer
						.getGPlayer(MojangAPI.getExactName(args[1]));

				if (gp == null) {
					gp = new GPlayer(MojangAPI.getExactName(args[1]));
				}

				gp.setGroup(Group.getGroup(args[2]));
				sender.sendMessage("§b" + gp.getName()
						+ " §&is now in group §2" + gp.getGroup());

				if (gp.isOnline()) {
					ProxyServer
							.getInstance()
							.getPlayer(gp.getName())
							.sendMessage(
									"§aYou are now in the group §2"
											+ gp.getGroup());
				}

			} else {
				if (!hasPermission(sender, "bgroups.help")) {
					sender.sendMessage("§cYou don't have permission to execute this command !");
					return;
				}
				sendHelp(sender);
			}

		} else {

			return;
		}

	}

	@SuppressWarnings("deprecation")
	private void sendHelp(CommandSender sender) {

		sender.sendMessage("§9---------------------------");
		sender.sendMessage(" §6/bgroups help §8- See all the command available");
		sender.sendMessage(" §6/bgroups create <group-name>");
		sender.sendMessage(" §6/bgroups delete <group-name>");
		sender.sendMessage(" §6/bgroups prefix <group-name> <prefix>");
		sender.sendMessage(" §6/bgroups suffix <group-name> <suffix>");
		sender.sendMessage(" §6/bgroups set <player> <group-name>");
		sender.sendMessage(" §6/bgroups save §8- Save data");
		sender.sendMessage("§9---------------------------");

	}

	private boolean hasPermission(CommandSender sender, String permission) {
		if (sender instanceof ProxiedPlayer
				&& (!sender.hasPermission(permission) || !sender
						.hasPermission("bgroups.*"))) {
			return false;
		}
		return true;
	}

}
