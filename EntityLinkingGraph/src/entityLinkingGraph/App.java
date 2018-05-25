package entityLinkingGraph;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class App {

	public static void main(String[] args) {

		Map<Integer, String> map = new HashMap<>();
		BufferedReader reader = new ReaderWriter().getReader("data\\new_dataset\\summaryArticleSmall.json");
		String line;
		JSONObject jsonLine;
		Integer docId = new Integer(0);
		String document = null;
		try {
			int i = 0;
			while ((line = reader.readLine()) != null) {
				jsonLine = new JSONObject(line);
				if (i == 0) {
					document = jsonLine.getString("summary");
					docId = new Integer(jsonLine.getInt("id"));
					i++;
				}

				map.put(jsonLine.getInt("id"), jsonLine.getString("summary"));
			}

			Graph graph = new Graph();
			graph.addVertex(docId);
			//System.out.println(docId);

			NamedEntityRecognition n = new NamedEntityRecognition(document);

			//System.out.println(n.getDocumentVector().values().toString());

			for (Integer nodes : n.getDocumentVector().values()) {
				graph.addVertex(nodes);
				n = new NamedEntityRecognition(map.get(nodes));
				for (Integer nodes1 : n.getDocumentVector().values()) {
					graph.addVertex(nodes1);
					graph.addEdge(nodes, nodes1);
				}
			}
			graph.addEdge(docId, 0);

			//System.out.println(graph.getVertices().toString());
			graph.printVertices();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}
}
