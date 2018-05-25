package entityLinkingGraph;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class NamedEntityRecognition {

	private String titleArticle = "data\\new_dataset\\titleArticleNEW.json";
	private Map<String, Integer> titleArticleMap;
	private Map<String, Integer> documentVector;
	private String document;

	public NamedEntityRecognition(String document) {
		this.document = document;
		this.titleArticleToMap();
		this.namedEntityRecognizer();

	}

	private void titleArticleToMap() {

		BufferedReader reader = new ReaderWriter().getReader(this.titleArticle);
		String line;
		JSONObject jsonLine;
		this.titleArticleMap = new HashMap<>();

		try {
			while ((line = reader.readLine()) != null) {
				jsonLine = new JSONObject(line);
				this.titleArticleMap.put(jsonLine.getString("title"), jsonLine.getInt("id"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	private void namedEntityRecognizer() {
		this.documentVector = new HashMap<>();

		for (String token : this.document.split("\\s+")) {
			if (this.titleArticleMap.containsKey(token.trim())) {
				this.documentVector.put(token.trim(), this.titleArticleMap.get(token.trim()).intValue());
			}
		}
	}

	public Map<String, Integer> getDocumentVector() {
		return this.documentVector;
	}
}
