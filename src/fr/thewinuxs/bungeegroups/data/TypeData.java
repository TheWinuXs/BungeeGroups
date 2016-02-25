package fr.thewinuxs.bungeegroups.data;

public enum TypeData {

	FILE, MYSQL;

	private static TypeData td = FILE;

	public void setTypeData(TypeData typedata) {
		td = typedata;
	}

	public TypeData getTypeData() {
		return td;
	}
}
