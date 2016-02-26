package fr.thewinuxs.bungeegroups.bungeecord;

import java.util.ArrayList;
import java.util.List;

import fr.thewinuxs.bungeegroups.bungeecord.data.TypeData;

public class Group {

	private static ArrayList<Group> groups = new ArrayList<>();

	private String name;
	private String prefix;
	private String suffix;
	private List<String> permissions;
	private List<String> inheritances;

	public Group(String name) {
		this.name = name;
		setPrefix("");
		setSuffix("");
		groups.add(this);
		init();
	}

	public String getName() {
		return this.name;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	/*
	 * Get all the groups loaded
	 */
	public static ArrayList<Group> getAll() {
		return groups;
	}

	/*
	 * Init the group
	 */
	public void init() {
		// TODO Get in MySQL or in File
		boolean exist = Core.getGroupsManager().exist(this);
		if (!exist) {
			create();
		}

		Core.getGroupsManager().init(this);

	}

	/*
	 * Save this group
	 */
	public void save() {
		// Update the group in File or MySQL
	}

	/*
	 * Save all the group
	 */
	public static void saveAll() {
		for (Group g : Group.getAll()) {
			g.save();
		}
	}

	/*
	 * Create the group
	 */
	public void create() {
		// Add a new Group in File or MySQL

	}

	public void delete() {
		if (Core.getConfig().getTypeData() == TypeData.MYSQL) {
			// TODO Delete the group in the MySQL database
		} else {
			// TODO Else delete the group in the file
		}

		remove();
	}

	public static void deleteAll() {
		for (Group g : groups) {
			if (Core.getConfig().getTypeData() == TypeData.MYSQL) {
				// TODO Delete the group in the MySQL database
			} else {
				// TODO Else delete the group in the file
			}

			g.delete();
		}
	}

	public static Group getGroup(String name) {
		for (Group g : groups) {
			if (g.getName().equalsIgnoreCase(name)) {
				return g;
			}
		}
		return null;
	}

	public static boolean exist(String name) {
		return getGroup(name) != null;
	}

	public void remove() {
		groups.remove(this);
	}

	public static void removeAll() {
		groups.clear();
	}

	public void setPermissions(List<String> list) {
		this.permissions = list;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public void setInheritances(List<String> inheritances) {
		this.inheritances = inheritances;

	}

	protected List<String> getInheritances() {
		return inheritances;
	}

	public ArrayList<Group> getGroupsInheritance() {
		if (inheritances == null)
			return null;
		ArrayList<Group> groups = new ArrayList<>();
		for (Object inheritance : inheritances) {
			Group group = Group.getGroup(inheritance + "");
			if (group != null)
				groups.add(group);
		}
		return null;

	}

}
