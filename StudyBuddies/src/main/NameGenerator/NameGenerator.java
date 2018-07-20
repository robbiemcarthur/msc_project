package main.NameGenerator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/**
 * 
 * @author Robbie McArthur - 2098323m
 * 
 * Class to build a name generator object which will provide a random name in String type
 *
 */
public class NameGenerator {
	private String name, first, last;
	private int index;
	private final String femaleFirst = "C:/prog/StudyBuddies/src/dist.female.first.txt", maleFirst = "C:/prog/StudyBuddies/src/dist.male.first.txt", all = "C:/prog/StudyBuddies/src/dist.all.last.txt";
	private final File _maleFirst, _femaleFirst, _all;
	private ArrayList<String> firstNames = new ArrayList();
	private ArrayList<String> lastNames = new ArrayList(); 
	private Random rand;
	
	public NameGenerator() {
		name = "";
		first = "";
		last = "";
		_maleFirst = new File(maleFirst);
		_femaleFirst = new File(femaleFirst);
		_all = new File(all);
		index = 0;
		rand = new Random();
	}
	
	public String GenerateName() {
		loadNames(1000);
		readFirst();
		readLast();
		name = first + " " + last;
		return name;
	}
	
	public void readFirst() {
		first = firstNames.get(index++);
	}
	
	public void readLast() {
		last = lastNames.get(index++);
	}
	
	public void loadNames(int num) {
		loadMale(num/2);
		loadFemale(num/2);
		shuffleNames();
		loadLast(num);
	}
	
	public void loadMale(int num) {
		String name = "";
		int i = 0;
		try {
			Scanner s = new Scanner(_maleFirst);
			while(s.hasNext() && i<num) {
				name = s.next();
				s.nextLine();
				firstNames.add(name);
				i++;
			}
			s.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't find male file");
			e.printStackTrace();
		}
	}
	
	public void loadFemale(int num) {
		int i = 0;
		String name = "";
		try {
			Scanner s = new Scanner(_femaleFirst);
			while(s.hasNext() && i<num) {
				name = s.next();
				s.nextLine();
				firstNames.add(name);
				i++;
			}
			s.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't find female file");
			e.printStackTrace();
		}
	}
	
	public void shuffleNames() {
		Collections.shuffle(firstNames);
	}
	
	public void loadLast(int num) {
		int i = 0;
		String name = "";
		try {
			Scanner s = new Scanner(_all);
			while(s.hasNext() && i<num) {
				name = s.next();
				s.nextLine();
				lastNames.add(name);
				i++;
			}
			s.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't find last name file");
			e.printStackTrace();
		}
	}
}

