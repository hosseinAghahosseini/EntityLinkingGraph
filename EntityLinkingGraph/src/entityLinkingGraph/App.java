package entityLinkingGraph;

import java.io.BufferedReader;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

public class App {

	public static void main(String[] args) {

		BufferedReader reader = new ReaderWriter().getReader("data\\new_dataset\\summaryArticleNEW.json");
		String document = null;
		try {
			document = reader.readLine();
			JSONObject jsonLine = new JSONObject(document);
			document = jsonLine.getString("summary");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		NamedEntityRecognition n = new NamedEntityRecognition(document);
		System.out.println(n.getDocumentVector().toString());
	}
}
