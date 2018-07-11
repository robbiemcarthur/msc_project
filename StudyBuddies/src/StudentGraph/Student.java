package StudentGraph;
import java.util.ArrayList;
import NameGenerator.NameGenerator;

/**
 * @author Robbie McArthur - 2098323m
 *
 * Base class for 'Students' to be recommended as study buddies
 * Will act as template for students of all disciplines
 * 
 */

public class Student {
	public NameGenerator ng;
	public String name;
	public String course;
	public String [] modules;
	public ArrayList<String> concepts = new ArrayList();
	public int id; 
	public ArrayList<Integer> conceptScore = new ArrayList();

	public Student(String n, String c, String [] m, ArrayList<String> con, int i, ArrayList<Integer> cs) {
		this.ng = new NameGenerator();
		this.course = c;
		this.modules = m;
		this.concepts = con;
		this.id = i;
		this.conceptScore = cs;
	}

	public Student() {
		ng = new NameGenerator();
		name = ng.GenerateName();
		course = "";
		modules = new String[9];
		id = 0;
	}

	// SETTERS
	public void setName(String n) {
		this.name = n;
	}

	public void setCourse(String c) {
		this.course = c;
	}
	public void setModule(String [] m) {
		this.modules = m;
	}
	public void setConcept(ArrayList<String> con) {
		this.concepts = con;
	}
	public void setID(int i) {
		this.id = i;
	}
	public void setConceptScore(ArrayList<Integer> cs) {
		this.conceptScore = cs;
	}

	// SETTERS
	public String getName() {
		return this.name;
	}
	
	public String getCourse() {
		return this.course;
	}
	
	public String [] getModules() {
		return this.modules;
	}
	
	public void modulesToString() {
		for (String i: modules) {
			System.out.print(i);
		}
	}
	
	public ArrayList<String> getConcept() {
		return this.concepts;
	}
	
	public int getID() {
		return this.id;
	}
	
	// will probably be better to make double in future so 
	// as to increase accuracy and effectiveness of recommendations 
	public ArrayList<Integer> getConceptScore() {
		return this.conceptScore;
	}
}
