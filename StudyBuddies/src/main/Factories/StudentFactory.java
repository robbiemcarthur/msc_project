package main.Factories;

import main.Application.models.KnowledgeGraph;
import main.Application.models.Student;

public class StudentFactory {
	
	public StudentFactory() {

	}
	
	public Student getStudent() {
		return new Student();
	}
	
	public Student getStudent(KnowledgeGraph g) {
		return new Student(g);
	}
}
