package main.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Logger implements ILogger {

	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";

	private static final String FILE_HEADER = "CourseID,CourseName,Concept,In Degree,Out Degree,Teacher,Grade,Student";

	@SuppressWarnings("unused")
	private String filename;
	private FileWriter writer;

	public Logger() {
		filename = "";
	}

	public Logger(String filename) {
		this.filename = filename;
	}

	@Override
	public void initialise(String filename) {
		this.filename = filename;
		try {
			writer = new FileWriter(filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void writeToFile(ArrayList<String> contents) {
		try {
			for(String c: contents) {
				writer.append(c);
				writer.append(COMMA_DELIMITER);
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void appendHeader() {
		try {
			writer.append(COMMA_DELIMITER);
			writer.append(FILE_HEADER);
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void writeLine(String line) {
		try {
			writer.append(NEW_LINE_SEPARATOR);
			writer.append(line);
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void closeFile() {
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
