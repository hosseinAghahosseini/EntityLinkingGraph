package entityLinkingGraph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

public class test {

	public static void main(String[] args) throws IOException, JSONException {

		BufferedReader reader = new ReaderWriter().getReader("data\\new_dataset\\tokenize_summaryArticleSmall.json");
		BufferedWriter writer = new ReaderWriter().getWriter("data\\new_dataset\\noErrortokenize_summaryArticleSmall.json");

		String line;
		//JSONObject jsonLine;

		while ((line = reader.readLine()) != null) {
			//jsonLine = new JSONObject(line);
			
			if(!line.equals("ERROR"))
				writer.append(line).append("\r\n");
			
			
			
//			if(!jsonLine.getString("title").matches("\\d+s|\\d+")) {
//				writer.append("{\"id\":\"" + jsonLine.getInt("id") + "\",\"summary\":\""
//						+ jsonLine.getString("summary").replaceAll("\\s+", "") + "\"}").append("\r\n");
//			}

		}

			// String title = "";
			// String[] array = jsonLine.getString("title").split("\\s+");
			//
			// for (int i = 0; i < array.length; i++) {
			// if (i == array.length - 1)
			// title += array[i];
			// else
			// title += array[i] + "-";
			// }
			//
			// writer.append("{\"id\":\"" + jsonLine.getInt("id") + "\",\"titleNoSpace\":\""
			// + jsonLine.getString("title").replaceAll("\\s+", "") +
			// "\",\"titleDashed\":\"" + title + "\"}");

			// writer.append("{\"id\":\"" + jsonLine.getInt("id") + "\",\"title\":\""
			// + jsonLine.getString("title").replaceAll("\\s+", "") + "\"}").append("\r\n");
			// String title = "";
			// String[] array = jsonLine.getString("title").split("\\s+");
			//
			// for (int i = 0; i < array.length; i++) {
			// if (i == array.length - 1)
			// title += array[i];
			// else
			// title += array[i] + "-";
			// }
			// writer.append("{\"id\":\"" + jsonLine.getInt("id") + "\",\"title\":\"" +
			// title + "\"}").append("\r\n");

		
		writer.flush();
		writer.close();

	}

}
