package fr.thewinuxs.bungeegroups.data.file;

import java.io.File;
import java.io.IOException;

import fr.thewinuxs.bungeegroups.Core;

public class DataFile {

	private File file_groups = new File(Core.getInstance().getDataFolder(), "groups.yml");
	private File file_permissions = new File(Core.getInstance().getDataFolder(), "permissions.yml");
	private File file_users = new File(Core.getInstance().getDataFolder(), "users.yml");

	public File getFileGroups() {
		return file_groups;
	}

	public File getFilePermissions() {
		return file_permissions;
	}

	public File getFileUsers() {
		return file_users;
	}

	protected void createFileGroups() {
		try {
			file_groups.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	protected void createFilePermissions() {
		try {
			file_permissions.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	protected void createFileUsers() {
		try {
			file_users.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void initFile() {
		if (!file_groups.exists())
			createFilePermissions();
		if (!file_permissions.exists())
			createFileGroups();
		if (!file_users.exists())
			createFileUsers();
		
	}
}
