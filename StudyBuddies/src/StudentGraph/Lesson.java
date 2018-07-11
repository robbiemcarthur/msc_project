package StudentGraph;

import java.util.Random;

public class Lesson {
	private String teacher, course, concept;
	private int grade;
	private Random rand;
	private Student student;
	
    //////////// Constructors ////////////
	
	public Lesson(String t, String c, String con, int grade, Student s) {
		this.teacher = t;
		this.course = c;
		this.concept = con;
		this.grade = grade;
		rand = new Random();
		this.student = s;
	}
	
	public Lesson() {
		teacher = "";
		course  = "";
		concept = "";
		grade = 0;
		rand = new Random();
		student = new Student();
	}
	
    //////////// Accessors ////////////

    public String getTeacher () {
        return this.teacher;
    }
    
    public String getCourse () {
        return this.course;
    }
    
    public String getConcept () {
        return this.concept;
    }
    
    public int getGrade() {
    	return this.grade;
    }
    
    public Student getStudent () {
        return this.student;
    }

    //////////// Transformers ////////////

    public void setTeacher (String t) {
        this.teacher = t;
    }
    
    public void setCourse (String c) {
        this.course = c;
    }
    
    public void setConcept (String con) {
        this.concept = con;
    }
    
    public void setgrade(int grade) {
    	this.grade = grade;
    }
    
    public void setStudent (Student s) {
        this.student = s;
    }
    
    public void setRandomGrade() {
    	this.grade = rand.nextInt(10);
    }
}
