package main.StudentGraph;

import java.util.Random;

public class Lesson {
	private String teacher, course, concept, student;
	private int id = 0, grade;
	private Random rand;
	private boolean passed;
	
    //////////// Constructors ////////////
	
	public Lesson(String t, String c, String con, int grade, int id, String s) {
		this.teacher = t;
		this.course = c;
		this.concept = con;
		this.grade = grade;
		this.id = id;
		rand = new Random();
		this.student = s;
		passed = false;
	}
	
	public Lesson() {
		teacher = "";
		course  = "";
		concept = "";
		grade = 0;
		rand = new Random();
		id++;
		student = "";
		passed = false;
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
    
    public String getStudent () {
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
    
    public void setStudent (String s) {
        this.student = s;
    }
    
    public void setRandomGrade() {
    	this.grade = rand.nextInt(100);
    }
    
    public boolean checkPass() {
		if (grade>=60) {
			passed = true;
		}
		else {
			passed = false;
		}
		return passed;
	}
    
	public int resitGrade() {
		if(grade<=40) {
			grade += rand.nextInt(60);
		}
		else {
			grade = rand.nextInt(100);
		}
		return grade;
	}
}