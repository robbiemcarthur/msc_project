package main.Logger;

import java.util.ArrayList;

public interface ILogger {
	/**
	 * Logger object will write contents of each graph to file
	 * enabling us to see if graph generator is working
	 */
	
	public void writeToFile(String contents);
	// write contents to file
	
	public void openFile();
	// open new file
	
	public void saveFile();
	// save file
	
	public void closeFile();
	// close file and tidy up resources

	public void writeToFile(ArrayList<String> contents);
}
