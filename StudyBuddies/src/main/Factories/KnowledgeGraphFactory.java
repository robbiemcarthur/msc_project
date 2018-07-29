package main.Factories;

import main.StudentGraph.KnowledgeGraph;
import main.StudentGraph.Student;


public class KnowledgeGraphFactory implements GraphFactory {
	private int id;
	private StudentFactory sf;
	private Student s;
	
	public KnowledgeGraphFactory() {
		id = 0;
		sf = new StudentFactory();
	}

	@Override
	public KnowledgeGraph getGraph() {
		id++;
		s = sf.getStudent();
		return new KnowledgeGraph(id, s);
	}
	
	public Student getStudent() {
		return this.s;
	}
}
