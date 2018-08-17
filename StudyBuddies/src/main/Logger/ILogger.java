package main.Logger;

import java.util.ArrayList;

/*
 * Logger object will write contents of each graph to file
 * enabling us to see if graph generator is working
 */

public interface ILogger {
	
	public void initialise(String filename);
	// initialise filewriter
	
	public void appendHeader();
	// append a header to the file
	
	public void writeLine(String line);
	// write line to file
	
	public void closeFile();
	// close file and tidy up resources

	public void writeToFile(ArrayList<String> contents);
	// write contents to file
}
