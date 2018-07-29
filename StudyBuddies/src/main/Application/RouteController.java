package main.Application;

import java.util.ArrayList;

import main.StudentGraph.KnowledgeGraph;
import main.StudentGraph.Lesson;

public class RouteController {
	private boolean passed;
	private int visits;
	private final int MAX_VISITS = 3;


	public RouteController() {
		visits = 0;
		passed = false;
	}

	public KnowledgeGraph getRoute(ArrayList<KnowledgeGraph.Node> nodes, KnowledgeGraph graph) {
		Lesson lesson = new Lesson();
		for(int i = 0; i <= nodes.size()-1; i = i+2)
		{
			lesson = (Lesson) nodes.get(i).getElement();
			lesson.setRandomGrade();
			graph.addNode(lesson);
			if(lesson.getGrade()<40)
			{
				graph.addNode((Lesson) nodes.get(i++).getElement());
			}
			while (!passed && visits<MAX_VISITS)
			{
				if(lesson.getGrade()>40) {
					if(!graph.containsEdge(nodes.get(i), nodes.get(i+2)))
					{
						graph.addEdge(nodes.get(i), nodes.get(i+2), visits);
						passed = true;
					}
				}
				else {
					lesson.resitGrade(lesson.getGrade());
					if(!graph.containsEdge(nodes.get(i), nodes.get(i++)))
					{
						graph.addEdge(nodes.get(i), nodes.get(i++), visits);
					}
				}
				visits++;
			}
			visits = 0;
		}
		return graph;
	}
}