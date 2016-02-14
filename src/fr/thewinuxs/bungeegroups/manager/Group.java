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

}
