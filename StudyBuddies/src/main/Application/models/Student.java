package main.Application.models;
import java.util.ArrayList;
import main.NameGenerator.NameGenerator;

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
	public int id; 


	public Student(int i) {
		this.ng = new NameGenerator();
		ng.loadNames(1000);
		name = ng.GenerateName();
		this.id = i;
	}

	public Student() {
		ng = new NameGenerator();
		ng.loadNames(1000);
		name = ng.GenerateName();
		id = 0;
	}

	// SETTERS
	public void setName(String n) {
		this.name = n;
	}

	public void setID(int i) {
		this.id = i;
	}


	// GETTERS
	public String getName() {
		return this.name;
	}
	public int getID() {
		return this.id;
	}
}