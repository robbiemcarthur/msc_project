package main.Application.models;

import java.util.Random;

public class Lesson {
	private String teacher, course, concept;
	private int id, grade, student;
	private Random rand;
	private boolean passed;
	
    //////////// Constructors ////////////
	
	public Lesson(String t, String c, String con, int grade, int id, int s) {
		this.teacher = t;
		this.course = c;
		this.concept = con;
		this.grade = grade;
		this.id = id;
		this.student = s;
		rand = new Random();
		passed = false;
	}
	
	public Lesson() {
		teacher = "";
		course  = "";
		concept = "";
		grade = 0;
		rand = new Random();
		id = 0;
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
    
    public int getID() {
    	return this.id;
    }
    
    public int getStudent() {
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
    
    
    public void setID (int id) {
    	this.id = id;
    }
    
    public void setStudent (int s) {
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
    
	public int resitGrade(int g) {
		grade += rand.nextInt(100-g);
		return grade;
	}
}
