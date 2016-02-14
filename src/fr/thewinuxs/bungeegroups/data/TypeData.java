package fr.thewinuxs.bungeegroups.data;

public enum TypeData {
	FILE, MYSQL;

	private static TypeData td = FILE;

	public static void setTypeData(TypeData typedata) {
		td = typedata;
	}

	public static TypeData getTypeData() {
		return td;
	}
}
