package fr.thewinuxs.bungeegroups.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import fr.thewinuxs.bungeegroups.Core;
import fr.thewinuxs.bungeegroups.config.Config;

public class MojangAPI {

	public static UUID getUUIDfromString(String player) throws IOException {

		UUID uuid = null;

		try {
			URL url = new URL(
					"https://api.mojang.com/users/profiles/minecraft/" + player);

			HttpURLConnection request = (HttpURLConnection) url
					.openConnection();
			request.connect();

			// Convert to a JSON object to print data
			JsonParser jp = new JsonParser(); // from gson

			// Convert the input stream to a json element
			JsonElement root = jp.parse(new InputStreamReader(
					(InputStream) request.getContent()));

			// May be an array, may be an object.
			JsonObject rootobj = root.getAsJsonObject();

			uuid = UUID.fromString(rootobj.get("id").getAsString());

		} catch (MalformedURLException e) {

			if (Config.debug) {
				e.printStackTrace();
			}
			Core.log.warning("Error with MojangAPI !");
		}
		return uuid;

	}
	
	
	public static String getNamefromUUID(UUID uuid) throws IOException {

		String name = null;

		try {
			URL url = new URL(
					"https://sessionserver.mojang.com/session/minecraft/profile/" + uuid);

			HttpURLConnection request = (HttpURLConnection) url
					.openConnection();
			request.connect();

			// Convert to a JSON object to print data
			JsonParser jp = new JsonParser(); // from gson

			// Convert the input stream to a json element
			JsonElement root = jp.parse(new InputStreamReader(
					(InputStream) request.getContent()));

			// May be an array, may be an object.
			JsonObject rootobj = root.getAsJsonObject();

			name = rootobj.get("name").getAsString();

		} catch (MalformedURLException e) {

			if (Config.debug) {
				e.printStackTrace();
			}
			Core.log.warning("Error with MojangAPI !");
		}
		return name;
	}

}
