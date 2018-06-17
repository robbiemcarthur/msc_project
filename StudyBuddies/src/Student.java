

/**
 * @author Robbie McArthur - 2098323m
 *
 * Base class for 'Students' to be recommended as study buddies
 * Will act as template for Law, Software, Politics students etc
 * 
 */

public abstract class Student {
	protected String name;
	protected String course;
	protected String [] modules;
	protected String concept;
	protected int id; 
	protected int [] conceptScore;

	public Student(String n, String c, String [] m, String con, int i, int [] cs) {
		this.course = c;
		this.modules = m;
		this.concept = con;
		this.id = i;
		this.conceptScore = cs;
	}

	public Student() {
		name = "";
		course = "";
		modules = new String[9];
		concept = "";
		id = 0;
		conceptScore = new int [9];
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
	public void setConcept(String con) {
		this.concept = con;
	}
	public void setID(int i) {
		this.id = i;
	}
	public void setConceptScore(int [] cs) {
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
	
	public String getConcept() {
		return this.concept;
	}
	
	public int getID() {
		return this.id;
	}
	
	// will probably be better to make double in future so 
	// as to increase accuracy and effectiveness of recommendations 
	public int [] getConceptScore() {
		return this.conceptScore;
	}
}
