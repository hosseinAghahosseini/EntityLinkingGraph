package entityLinkingGraph;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonToMap<K, V> {
	private String path;
	private Map<K, V> map;

	public JsonToMap(String path) {

		this.path = path;
		this.map = new HashMap<>();

	}

	public Map<K, V> getMap(String jsonKeyToMapKey, String jsonKeyToMapValue, K exampleKey, V exampleValue) {
		BufferedReader reader = new ReaderWriter().getReader(this.path);
		String line;
		JSONObject jsonLine;

		try {
			// get class of Key and Value for CastingTo
			this.map.put(exampleKey, exampleValue);
			Class<?> Kclass = this.map.keySet().toArray()[0].getClass();
			Class<?> Vclass = this.map.values().toArray()[0].getClass();
			this.map.remove(exampleKey);

			// create Map
			while ((line = reader.readLine()) != null) {
				jsonLine = new JSONObject(line);
				this.map.put(
						new CastingTo<K>(jsonLine.get(jsonKeyToMapKey), Kclass).getCastedObject(),
						new CastingTo<V>(jsonLine.get(jsonKeyToMapValue), Vclass).getCastedObject()
						);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return this.map;
	}

}
