package main.Factories;

import main.StudentGraph.KnowledgeGraph;
import main.StudentGraph.Student;


public class KnowledgeGraphFactory implements GraphFactory {
	private int id;
	
	public KnowledgeGraphFactory() {
		id = 0;
	}

	@Override
	public KnowledgeGraph getGraph() {
		id++;
		return new KnowledgeGraph(id);
	}
}
