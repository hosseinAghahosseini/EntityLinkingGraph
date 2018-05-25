package entityLinkingGraph;

import java.util.HashMap;
import java.util.Map;

public class NamedEntityRecognition {

	private String titleArticle = "data\\new_dataset\\titleArticleSmall.json";
	private Map<String, Integer> titleArticleMap;
	private Map<String, Integer> neMap;
	private String document;

	public NamedEntityRecognition(String document) {
		this.document = document;
		this.titleArticleToMap();
		this.namedEntityRecognizer();

	}

	private void titleArticleToMap() {
		JsonToMap<String, Integer> map = new JsonToMap<String, Integer>(this.titleArticle);
		this.titleArticleMap = map.getMap("title", "id", "zero", 0);
	}

	private void namedEntityRecognizer() {
		this.neMap = new HashMap<>();

		for (String token : this.document.split("\\s+")) {
			if (this.titleArticleMap.containsKey(token.trim())) {
				this.neMap.put(token.trim(), this.titleArticleMap.get(token.trim()).intValue());
			}
		}
	}

	public Map<String, Integer> getNamedEntityMap() {
		return this.neMap;
	}
}
