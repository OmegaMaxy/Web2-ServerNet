package domain.model;

import helpers.FullResponseBuilder;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ServerLegacy {

	private ArrayList<ArrayList<String>> objServers;
	private String name;

	public ServerLegacy(HttpServletRequest postData) throws IOException {
		this.store(postData);
	}

	private String index() throws IOException {
		URL url = new URL("api.omegauna.eu/ucll/web2/servers/");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");

		return FullResponseBuilder.getFullResponse(conn);
	}
	private String get(int id) throws IOException {
		URL url = new URL("api.omegauna.eu/ucll/web2/servers/" + id);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");

		return FullResponseBuilder.getFullResponse(conn);
	}
	private void store(HttpServletRequest postData) throws IOException {
		URL url = new URL("api.omegauna.eu/ucll/web2/servers/");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json; utf-8");
		conn.setRequestProperty("Accept", "application/json");

		conn.setDoOutput(true);

		String jsonInputString = "{\"name\": \"" + postData.getParameter("name") + "\", \"numServices\": \"" + postData.getParameter("numServices") + "\"}";
		try(OutputStream os = conn.getOutputStream()){
			byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
			os.write(input, 0, input.length);
		}

		//return FullResponseBuilder.getFullResponse(conn);
	}
	public static boolean save(HttpServletRequest postData, int id) throws IOException {
		URL url = new URL("api.omegauna.eu/ucll/web2/servers/" + id);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("PUT");
		conn.setRequestProperty("Content-Type", "application/json; utf-8");
		conn.setRequestProperty("Accept", "application/json");

		conn.setDoOutput(true);

		String jsonInputString = "{\"name\": \"" + postData.getParameter("name") + "\", \"numServices\": \"" + postData.getParameter("numServices") + "\"}";
		try(OutputStream os = conn.getOutputStream()){
			byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
			os.write(input, 0, input.length);
		}

		return true;
	}
	public static boolean delete(int id) throws IOException {
		URL url = new URL("api.omegauna.eu/ucll/web2/servers/" + id);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("DELETE");
		conn.setRequestProperty("Content-Type", "application/json; utf-8");

		return true;
	}

	@Override
	public boolean equals(Object object) {
		boolean equal = false;
		if (object instanceof ServerLegacy) {
			ServerLegacy other = (ServerLegacy) object;
			equal = this.name.equals(other.name);
		}
		return equal;
	}

	@Override
	public String toString() {
		return "";
	}
}
