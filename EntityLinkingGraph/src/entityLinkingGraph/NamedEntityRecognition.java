package entityLinkingGraph;

import java.util.HashMap;
import java.util.Map;

public class NamedEntityRecognition {

	private String titleArticle = "data\\new_dataset\\titleArticleSmall.json";
	private Map<String, Integer> titleArticleMap;
	private Map<String, Integer> documentVector;
	private String document;

	public NamedEntityRecognition(String document) {
		this.document = document;
		this.titleArticleToMap();
		this.namedEntityRecognizer();

	}

	private void titleArticleToMap() {
		JsonToMap map = new JsonToMap(this.titleArticle);
		this.titleArticleMap = map.getMap("title", "id");
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
