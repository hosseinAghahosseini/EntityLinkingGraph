//writing by mohammadHoseinAbedi : 26/5/2018, he will come and save humanity(Ayatollah Khamenei).
package entityLinkingGraph;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.json.JSONException;
import org.json.JSONObject;

public class App {

	public static void main(String[] args) {

		try {
			Integer docId = new Integer(92193);

			// get Map of document-Summary and document-Id
			Map<Integer, String> summaryMap = new HashMap<>();
			/*
			 * = new JsonToMap<Integer, String>(
			 * "data\\new_dataset\\summaryArticleSmall.json").getMap("id", "summary", 0,
			 * "zero");
			 */

			// get Map of document-title and document-Id
			Map<Integer, String> titleMap = new HashMap<>();
			/*
			 * = new JsonToMap<Integer, String>("data\\new_dataset\\titleArticleSmall.json")
			 * .getMap("id", "title", 0, "zero");
			 */

			BufferedReader reader = new ReaderWriter().getReader("data\\new_dataset\\summaryArticleSmall.json");
			String line;
			JSONObject jsonLine;

			try {
				// create Maps
				while ((line = reader.readLine()) != null) {
					jsonLine = new JSONObject(line);
					summaryMap.put(jsonLine.getInt("id"), jsonLine.getString("summary"));
					titleMap.put(jsonLine.getInt("id"), jsonLine.getString("title"));
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}

			reader.close();

			// building graph
			Graph graph = new SingleGraph("0");
			graph.setStrict(false);
			graph.setAutoCreate(true);

			// add Root Node
			graph.addNode(docId.toString());

			// stack Node and Expanded List
			LinkedList<Integer> nodeList = new LinkedList<>();
			LinkedList<Integer> expandedList = new LinkedList<>();
			nodeList.add(docId);
			int layers = 1;

			while (!nodeList.isEmpty() && layers <= 2) {
				System.out.println("Layer number " + layers + " is bieng processed.");
				System.out.println("Layer number "+layers + " Nodes : " + nodeList.toString());
				@SuppressWarnings("unchecked")
				LinkedList<Integer> thisLayerNodes = (LinkedList<Integer>) nodeList.clone();

				for (Integer actualVertex : thisLayerNodes) {
					expandedList.add(actualVertex);

					for (Integer namedEntity : new NamedEntityRecognition(summaryMap.get(actualVertex))
							.getNamedEntityMap().values()) {

						if (!expandedList.contains(namedEntity) && !nodeList.contains(namedEntity))
							nodeList.add(namedEntity);

						graph.addEdge(actualVertex.toString() + namedEntity.toString(), actualVertex.toString(),
								namedEntity.toString());

					}
				}
				nodeList.removeAll(thisLayerNodes);


				layers++;
			}

			for (Node node : graph.getNodeSet()) {
				node.setAttribute("label", titleMap.get(Integer.parseInt(node.getId())));
			}
			graph.display();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
