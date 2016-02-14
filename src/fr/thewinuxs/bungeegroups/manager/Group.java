package fr.thewinuxs.bungeegroups.manager;

import java.util.ArrayList;

public class Group {

	private static ArrayList<Group> groups = new ArrayList<>();

	private String name;
	private String prefix;
	private String suffix;

	public Group(String name) {
		this.name = name;
		groups.add(this);
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

	public static ArrayList<Group> getAll() {
		return groups;
	}

	public static void removeAll() {
		/*
		 *  // Remove All groups in file or MySQL
		 */
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

}
