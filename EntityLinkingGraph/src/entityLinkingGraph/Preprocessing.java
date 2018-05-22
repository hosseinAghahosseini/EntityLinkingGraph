package entityLinkingGraph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Preprocessing {
	private String originalDatasetPath;
	private String titleArticleDataset;
	private String summaryArticleDataset;

	public Preprocessing() {
		this.originalDatasetPath = "data\\original_dataset\\originalDataset.txt";
		this.titleArticleDataset = "data\\new_dataset\\titleArticle.json";
		this.summaryArticleDataset = "data\\new_dataset\\summaryArticle.json";
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
				jsonLine = "{\"id\":\"" + articleID + "\",\"title\":\"" + lineArray[0] + "\",\"summary\":\""
						+ isIndexExist(lineArray, 1) + "\"}";
				summaryWriter.append(jsonLine).append("\r\n");

				// Title JSON Construct
				jsonLine = "{\"id\":\"" + articleID + "\",\"title\":\"" + lineArray[0] + "\"}";
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

}
