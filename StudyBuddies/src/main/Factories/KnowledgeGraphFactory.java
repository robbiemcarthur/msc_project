package main.Factories;

import java.util.ArrayList;

import main.StudentGraph.Graph;
import main.StudentGraph.KnowledgeGraph;
import main.StudentGraph.Lesson;
import main.StudentGraph.KnowledgeGraph.Node;

public class KnowledgeGraphFactory implements GraphFactory {
	private KnowledgeGraph g;
	private Lesson lesson;
	private ArrayList<Lesson> LessonList;
	private KnowledgeGraph.Node n;

	public KnowledgeGraphFactory() {
		g = new KnowledgeGraph();
		lesson = new Lesson();
	}

	@Override
	public KnowledgeGraph createGraph() {
		return new KnowledgeGraph();
	}
	
//	public KnowledgeGraph requestGraph(String type) {
//		switch (type.toLowerCase()){
//		case "math":
//			g = createMathGraph();
//		case "programming":
//			g = createProgrammingGraph();
//		case "history":
//			g = createHistoryGraph();
//			default:
//				g = createGraph();
//		}
//		return g;
//	}
//	
//	public KnowledgeGraph createMathGraph() {
//		KnowledgeGraph graph = new KnowledgeGraph();
//		lesson.
//		return graph;
//	}
//	
//	public KnowledgeGraph createProgrammingGraph() {
//		return new KnowledgeGraph();
//	}
//	
//	public KnowledgeGraph createHistoryGraph() {
//		return new KnowledgeGraph();
//	}
}
