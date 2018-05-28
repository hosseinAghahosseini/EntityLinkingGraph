//writing by mohammadHoseinAbedi : 11/5/2018, he will come and save humanity(Ayatollah Khamenei).
package tokenSplitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import entityLinkingGraph.ReaderWriter;

public class DictonaryToHashmap {
	private HashMap<String, Integer> hashMap;

	public DictonaryToHashmap() {
		this.hashMap = new HashMap<String, Integer>();
		System.out.println("english token convert to hashMap...");
		this.toHashMap("data\\new_dataset\\noSpaceTitleArticleNEW.json");
	}

	private void toHashMap(String filePath) {
		String line = "";
		try {

			BufferedReader reader = new ReaderWriter().getReader(filePath);

			JSONObject jsonLine;
			while ((line = reader.readLine()) != null) {
				jsonLine = new JSONObject(line);
				this.hashMap.put(jsonLine.getString("title"), null);
			}
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();

		} catch (JSONException e) {
			System.out.println(line);
			e.printStackTrace();
		}
	}

	public HashMap<String, Integer> getHashMap() {
		return this.hashMap;
	}

}
