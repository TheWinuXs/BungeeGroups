package fr.thewinuxs.bungeegroups.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;

import fr.thewinuxs.bungeegroups.Core;
import fr.thewinuxs.bungeegroups.data.TypeData;

public class Config {

	public static TypeData type = TypeData.FILE;

	public static void load() {
		File localFile = new File(Core.getInstance().getDataFolder(),
				"config.yml");
		if (!localFile.exists()) {
			InputStream localInputStream = Core.getInstance()
					.getResourceAsStream("config.yml");
			try {
				Files.copy(localInputStream, localFile.toPath(),
						new CopyOption[0]);
			} catch (IOException localIOException) {
				localIOException.printStackTrace();
				return;
			}
		}
		
		
		
		
		
	}

}
