package entityLinkingGraph;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonToMap {
	private String path;
	private Map<String, Integer> map;

	public JsonToMap(String path) {
		this.map = new HashMap<>();
		this.path = path;
	}

	public Map<String, Integer> getMap(String key, String value) {
		BufferedReader reader = new ReaderWriter().getReader(this.path);
		String line;
		JSONObject jsonLine;
		try {
			while ((line = reader.readLine()) != null) {
				jsonLine = new JSONObject(line);
				this.map.put(jsonLine.getString(key), jsonLine.getInt(value));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return this.map;
	}

}
