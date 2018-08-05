package main.Application;

import java.util.ArrayList;

import main.StudentGraph.KnowledgeGraph;
import main.StudentGraph.Lesson;
import main.StudentGraph.Student;

public class RouteController {
	private boolean passed;
	private int visits;
	private final int MAX_VISITS = 2;
	private Student s;
	private Lesson lesson;
	private ArrayList<KnowledgeGraph.Node> nodes;
	private KnowledgeGraph graph;


	public RouteController() {
		visits = 0;
		s = new Student();
		lesson = new Lesson();
		nodes = new ArrayList();
		graph = new KnowledgeGraph(0, s);
		passed = false;
	}

	public KnowledgeGraph getRoute(ArrayList<KnowledgeGraph.Node> nodes, KnowledgeGraph graph) {
		this.nodes = nodes;
		this.graph = graph;
		for(int i = 0; i < nodes.size(); i++) {
			passed = false;
			visits = 1;
			while(!passed && visits<MAX_VISITS)
			{
				if(i % 2 == 0) {
					lesson(i);
					if(passed==false && !(i==0))
					{
						i--;
					}
				}
				else {
					revisionLesson(i);
					if(passed==false && !(i==0)) {
						i--;
					}
				}
			}
		}
		return graph;
	}

	public void lesson(int i) {
		lesson = (Lesson) nodes.get(i).getElement();
		graph.addNode(lesson);
		if(lesson.getGrade()==0)
		{
			lesson.setRandomGrade();
		}
		else {
			lesson.setgrade(lesson.resitGrade(lesson.getGrade()));
		}
		if(lesson.getGrade()>40) {
			if(!(i==8))
			{
				graph.addEdge(nodes.get(i), nodes.get(i + 2), visits);
			}
			i += 1;
			passed = true;
		}
		else {
			visits++;
		}
	}

	public void revisionLesson(int i) {
		lesson = (Lesson) nodes.get(i-1).getElement();
		if(lesson.getGrade()>40)
		{
			passed = true;
		}
		else {
			if(!(i==9))
			{
				lesson = (Lesson) nodes.get(i).getElement();
				graph.addNode(lesson);
				lesson.setgrade(40);
				KnowledgeGraph.Edge edge = new KnowledgeGraph.Edge(nodes.get(i), nodes.get(i + 1), visits);
				if(!graph.containsEdge(nodes.get(i), nodes.get(i + 1)))
				{
					graph.addEdge(nodes.get(i), nodes.get(i + 1), visits);
					passed = true;
				}
				else 
				{
					if(visits<MAX_VISITS)
					{
						visits++;
						graph.removeEdge(edge);
						graph.addEdge(nodes.get(i), nodes.get(i + 1), visits);
					}
					else {
						passed = true;
					}
				}
			}
		}
//		else {
//			lesson = (Lesson) nodes.get(i-1).getElement();
//			if(lesson.getGrade()>40)
//			{
//				passed = true;
//			}
//			else
//			{
//				lesson = (Lesson) nodes.get(i).getElement();
//				graph.addNode(lesson);
//				lesson.setgrade(40);
//				passed = true;
//			}
//		}
	}
}
