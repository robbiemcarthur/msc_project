package main.Application.models;
import main.NameGenerator.NameGenerator;

/**
 * @author Robbie McArthur - 2098323m
 *
 * Base class for 'Students' to be recommended as study buddies
 * Will act as template for students of all disciplines
 * 
 */

public class Student implements Comparable<Student> {
	private NameGenerator ng;
	private String name;
	private int id;
	private double distance; 
	private KnowledgeGraph graph;

	// CONSTRUCTORS
	public Student(int i, KnowledgeGraph graph) {
		this.ng = new NameGenerator();
		ng.loadNames(1000);
		name = ng.GenerateName();
		this.id = i;
		this.graph = graph;
		distance = 0.0;
	}

	public Student(KnowledgeGraph graph) {
		ng = new NameGenerator();
		ng.loadNames(1000);
		name = ng.GenerateName();
		id = 0;
		distance = 0.0;
		this.graph = graph;
	}
	
	public Student() {
		ng = new NameGenerator();
		ng.loadNames(1000);
		name = ng.GenerateName();
		id = 0;
		distance = 0.0;
		this.graph = new KnowledgeGraph();
	}

	// SETTERS
	public void setName(String n) {
		this.name = n;
	}

	public void setID(int i) {
		this.id = i;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	public void setGraph(KnowledgeGraph graph) {
		this.graph = graph;
	}

	// GETTERS
	public String getName() {
		return this.name;
	}
	public int getID() {
		return this.id;
	}

	public double getDistance() {
		return distance;
	}
	
	public KnowledgeGraph getGraph() {
		return graph;
	}

	@Override
	public int compareTo(Student o) {
		if(this.getDistance()<o.getDistance()) {
			return -1;
		}
		else if(this.getDistance()>o.getDistance()) {
			return 0;
		}
		else {
			return 1;
		}
	}
}
