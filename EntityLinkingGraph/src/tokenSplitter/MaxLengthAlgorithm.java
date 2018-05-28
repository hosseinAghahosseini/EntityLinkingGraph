//writing by mohammadHoseinAbedi : 11/5/2018, he will come and save humanity(Ayatollah Khamenei).
package tokenSplitter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Stack;

import org.json.JSONException;
import org.json.JSONObject;

import entityLinkingGraph.ReaderWriter;

public class MaxLengthAlgorithm {
	private Stack<String> tokenStack;
	private HashMap<String, Integer> tokenHashMap;

	public MaxLengthAlgorithm() {
		this.tokenStack = new Stack<>();
		this.tokenHashMap = new DictonaryToHashmap().getHashMap();
		this.algorithm();

	}

	private void algorithm() {

		try {
			BufferedWriter writer = new ReaderWriter()
					.getWriter("data\\new_dataset\\tokenize_summaryArticleSmall.json");

			BufferedReader reader = new ReaderWriter().getReader("data\\new_dataset\\noSpaceSummaryArticleSmall.json");

			String actualToken = "";
			String readerline = "";
			JSONObject jsonLine;

			while ((readerline = reader.readLine()) != null) {
				jsonLine = new JSONObject(readerline);
				String line = jsonLine.getString("summary");

				int lengthOfString = line.length();
				boolean isInfinity = false;
				while (lengthOfString > 0 && !isInfinity) {
					for (int i = 0; i < lengthOfString; i++) {
						actualToken = line.substring(i, lengthOfString);
						isInfinity = true;
						if (this.tokenHashMap.containsKey(actualToken)) {
							this.tokenStack.push(actualToken);
							lengthOfString = i;
							isInfinity = false;
							break;
						}
					}
				}
				if (isInfinity) {
					writer.append("ERROR").append("\r\n");
					while (!this.tokenStack.isEmpty())
						this.tokenStack.pop();
				} else {
					String summary = "";
					while (!this.tokenStack.isEmpty()) {
						summary += this.tokenStack.pop() + " ";
					}
					writer.append("{\"id\":\"" + jsonLine.getInt("id") + "\",\"summary\":\"" + summary + "\"}")
							.append("\r\n");
					System.out.println(jsonLine.getInt("id"));
				}
			}

			writer.flush();
			writer.close();
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
