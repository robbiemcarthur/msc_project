package main.Factories;

import main.Application.models.KnowledgeGraph;


public class KnowledgeGraphFactory implements GraphFactory {
	private int id;
	
	public KnowledgeGraphFactory() {
		id = 0;
	}

	@Override
	public KnowledgeGraph getGraph() {
		return new KnowledgeGraph(id++);
	}
}
