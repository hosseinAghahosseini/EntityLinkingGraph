package entityLinkingGraph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ReaderWriter {

	public BufferedReader getReader(String openPath) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(openPath), "UTF8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return reader;
	}

	public BufferedWriter getWriter(String savePath) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(savePath, true), "UTF8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return writer;
	}

}
