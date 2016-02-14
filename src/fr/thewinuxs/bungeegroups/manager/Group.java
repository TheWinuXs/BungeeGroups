package fr.thewinuxs.bungeegroups.manager;

import java.util.ArrayList;

public class Group {

	private static ArrayList<Group> groups = new ArrayList<>();

	String name;
	String prefix;
	String suffix;

	public Group(String name) {
		this.name = name;
		setPrefix("");
		setSuffix("");
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
	
	public void init() {
		// Init the group
	}
	
	public void remove() {
		// Remove group from data (Not File or MySQL)
	}
	
	public void update() {
		// Update the group in File or MySQL	
	}
	
	public void create() {
		// Add a new Group from File or MySQL
		
		init();
	}
	
	public void delete() {
		// Delete the group from File or MySQL
		
		remove();
	}

	public static void deleteAll() {
		for (Group g : groups) {
			g.delete();
		}
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
