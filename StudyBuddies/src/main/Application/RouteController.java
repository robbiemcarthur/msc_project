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
		int g = 0;
		lesson = (Lesson) nodes.get(0).getElement();
		lesson.setRandomGrade();
		while(lesson.getGrade()<40) {lesson.resitGrade(lesson.getGrade());}
		graph.addNode(lesson);
		for(int i = 1; i < nodes.size()-3; i++)
		{
			lesson = (Lesson) nodes.get(i).getElement();
			lesson.setRandomGrade();
			g = lesson.getGrade();
			graph.addNode((Lesson) nodes.get(i++).getElement());
			passed = false;
			while (!passed && visits<MAX_VISITS)
			{
				if(visits>MAX_VISITS) 
				{
					break;
				}
				else {
					if(g>40) {
						if(!graph.containsEdge(nodes.get(i), nodes.get(i+2)))
						{
							graph.addEdge(nodes.get(i), nodes.get(i+2), visits);
							passed = true;
						}
					}
					else {
						g = lesson.resitGrade(g);
						if(!graph.containsEdge(nodes.get(i), nodes.get(i++)))
						{
							graph.addEdge(nodes.get(i), nodes.get(i++), visits);
						}
					}
				}
			}
			visits++;
		}
		visits = 0;
		return graph;
	}
}
