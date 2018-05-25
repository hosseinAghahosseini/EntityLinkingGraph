//writing by mohammadHoseinAbedi : 26/5/2018, he will come and save humanity(Ayatollah Khamenei).
package entityLinkingGraph;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class App {

	public static void main(String[] args) {

		try {
			BufferedReader reader = new ReaderWriter().getReader("data\\new_dataset\\summaryArticleSmall.json");
			String document = new JSONObject(reader.readLine()).getString("summary");
			Integer docId = new JSONObject(reader.readLine()).getInt("id");

			// get Map of document-Summary and document-Id
			Map<Integer, String> summaryMap = new JsonToMap<Integer, String>(
					"data\\new_dataset\\summaryArticleSmall.json").getMap("id", "summary", 0, "zero");

			Graph graph = new Graph();
			graph.addVertex(docId);

			// graph creator
			for (Integer namedEntity : new NamedEntityRecognition(document).getNamedEntityMap().values()) {
				graph.addVertex(namedEntity);
				graph.addEdge(docId, namedEntity);
				for (Integer nodes1 : new NamedEntityRecognition(summaryMap.get(namedEntity)).getNamedEntityMap()
						.values()) {
					graph.addVertex(nodes1);
					graph.addEdge(namedEntity, nodes1);
				}
			}
			graph.printVertices();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}
}
