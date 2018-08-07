package main.Factories;

import main.Application.models.Student;

public class StudentFactory {
	private Student s;
	private int id;
	
	public StudentFactory() {
		id = 0;
	}
	
	public Student getStudent() {
		return new Student(id++);
	}
}
