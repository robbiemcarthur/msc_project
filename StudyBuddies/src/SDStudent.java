import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class SDStudent extends Student {
	protected final String[] modules = new String [] {"Internet Technology", "HCI", "Programming", 
			"Advanced Programming", "Algorithms and Data Structures", "Software Project Management",
			"Software Engineering", "Systems and Networks", "Enterprise Cyber Security", 
			"Database Theory and Application"};
	protected HashMap<String, Integer> conceptKnowledge = new HashMap();
	protected ArrayList<String> programmingConcepts = new ArrayList();
	protected int score;
	protected Random rand;
	
	public SDStudent() {
		super.course = "Software Development";
		super.modules = this.modules;
		score = 0;
		rand = new Random();
	}
	
	public void loadStudentScores() {
		loadConcepts();
		for(String i: programmingConcepts) {
			score = rand.nextInt(22);
			conceptKnowledge.put(i, score);
		}
	}
	
	public void loadConcepts() {
		programmingConcepts.add("Variables and Data Types");
		programmingConcepts.add("Operations and Branching");
		programmingConcepts.add("Control Structures");
		programmingConcepts.add("Methods");
		programmingConcepts.add("Classes and Objects");
		programmingConcepts.add("Error Handling");
		programmingConcepts.add("Event Listeners");
		programmingConcepts.add("Recursion");
		programmingConcepts.add("Arrays");
		programmingConcepts.add("Interfaces");
		programmingConcepts.add("Inheritance");
	}
	
	public HashMap<String, Integer> getScores(){
		return conceptKnowledge;
	}
}
