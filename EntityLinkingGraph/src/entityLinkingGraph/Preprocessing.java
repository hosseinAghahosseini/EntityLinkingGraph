package entityLinkingGraph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

public class Preprocessing {
	private String originalDatasetPath;
	private String titleArticleDataset;
	private String summaryArticleDataset;
	private String newDatasetPath;

	public Preprocessing() {
		this.originalDatasetPath = "data\\original_dataset\\originalDataset.txt";
		this.titleArticleDataset = "data\\new_dataset\\titleArticle.json";
		this.summaryArticleDataset = "data\\new_dataset\\summaryArticle.json";
		this.newDatasetPath = "data\\new_dataset\\";
	}

	public void newDatasetsCreator() {
		BufferedReader reader = new ReaderWriter().getReader(this.originalDatasetPath);

		BufferedWriter titleWriter = new ReaderWriter().getWriter(this.titleArticleDataset);
		BufferedWriter summaryWriter = new ReaderWriter().getWriter(this.summaryArticleDataset);

		try {
			String line, jsonLine;
			long articleID = 0;
			String[] lineArray;

			while ((line = reader.readLine()) != null) {

				// line splitter
				lineArray = line.split(" \\|\\|\\| ");

				// Summary JSON Construct
				jsonLine = "{\"id\":\"" + articleID + "\",\"title\":\""
						+ lineArray[0].replaceAll("\\r|\\n", "").replaceAll("[^a-zA-Z\\d+\\s+]", "")
						+ "\",\"summary\":\""
						+ isIndexExist(lineArray, 1).replaceAll("\\r|\\n", "").replaceAll("[^a-zA-Z\\d+\\s+]", "")
						+ "\"}";
				summaryWriter.append(jsonLine).append("\r\n");

				// Title JSON Construct
				jsonLine = "{\"id\":\"" + articleID + "\",\"title\":\""
						+ lineArray[0].replaceAll("\\r|\\n", "").replaceAll("[^a-zA-Z\\d+\\s+]", "") + "\"}";
				titleWriter.append(jsonLine).append("\r\n");
				articleID++;
			}

			reader.close();
			titleWriter.flush();
			titleWriter.close();
			summaryWriter.flush();
			summaryWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String isIndexExist(String[] array, int index) {
		try {
			return array[index];
		} catch (ArrayIndexOutOfBoundsException e) {
			return "";
		}

	}

	public void newDatasetsCleaning() {

		String summaryLine = null;
		String titleLine;
		JSONObject jsonSummaryLine;
		JSONObject jsonTitleLine;

		try {
			BufferedReader summaryReader = new ReaderWriter().getReader(this.summaryArticleDataset);
			BufferedWriter summaryWriter = new ReaderWriter().getWriter(this.newDatasetPath + "summaryArticleNEW.json");

			BufferedReader titleReader = new ReaderWriter().getReader(this.titleArticleDataset);
			BufferedWriter titleWriter = new ReaderWriter().getWriter(this.newDatasetPath + "titleArticleNEW.json");

			while ((summaryLine = summaryReader.readLine()) != null && (titleLine = titleReader.readLine()) != null) {

				jsonSummaryLine = new JSONObject(summaryLine);
				jsonTitleLine = new JSONObject(titleLine);

				if (!jsonSummaryLine.getString("title").isEmpty()) // TODO : reduce noise
					summaryWriter.append(summaryLine).append("\r\n");

				if (!jsonTitleLine.getString("title").isEmpty()) // TODO : reduce noise
					titleWriter.append(titleLine).append("\r\n");
			}

			summaryReader.close();
			summaryWriter.flush();
			summaryWriter.close();

			titleReader.close();
			titleWriter.flush();
			titleWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			System.out.println(summaryLine);
			e.printStackTrace();
		}

	}

}
