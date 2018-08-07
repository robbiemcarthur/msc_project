package main.Factories;

import main.Application.models.KnowledgeGraph;
import main.Application.models.Student;


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
