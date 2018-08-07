package main.Application;

import java.util.ArrayList;
import java.util.Random;

import main.StudentGraph.KnowledgeGraph;
import main.StudentGraph.Lesson;
import main.StudentGraph.Student;

public class RouteController {
	private boolean passed;
	private int visits;
	private final int MAX_VISITS = 2;
	private Student s;
	private Lesson curr, prev, next;
	private ArrayList<KnowledgeGraph.Node> nodes;
	private ArrayList<Lesson> lessons;
	private KnowledgeGraph graph;
	private Random rand;


	public RouteController() {
		visits = 0;
		s = new Student();
		curr = new Lesson();
		prev = new Lesson();
		next= new Lesson();
		nodes = new ArrayList();
		lessons = new ArrayList();
		graph = new KnowledgeGraph(0);
		passed = false;
		rand = new Random();
	}

	public KnowledgeGraph route(ArrayList<KnowledgeGraph.Node> nodes, KnowledgeGraph graph) {
		this.nodes = nodes;
		this.graph = graph;
		int count = 0;
		boolean finished = false;
		while(!finished)
		{
			for(int i = 0; i < nodes.size(); i++) {
				visits = rand.nextInt(6);
				if(i==8) {
					curr = (Lesson) nodes.get(i).getElement();
					curr.setgrade(rand.nextInt(60)+40);
					graph.addNode(curr);
					finished = true;
					if(visits>4)
					{
						curr = (Lesson) nodes.get(i + 1).getElement();
						curr.setgrade(rand.nextInt(40)-1);
						graph.addNode(curr);
						graph.addEdge(nodes.get(i), nodes.get(i + 1), visits);
					}
				}
				else {
					curr = (Lesson) nodes.get(i).getElement();
					curr.setgrade(rand.nextInt(60)+40);
					next = (Lesson) nodes.get(i + 1).getElement();
					next.setgrade(rand.nextInt(60)+40);
					// might need to reset elements to update i.e. nodes.geti.setelement(curr)
					if(visits==3) {
						visits = 0;
					}
					if(visits > 3) {
						count++;
						if(count>1) {
						finished = true;
						break;
						}
					}
					if(visits==0) {
						//add standard lesson to next standard lesson
						graph.addNode(curr);
						graph.addNode(next);
						graph.addEdge(nodes.get(i), nodes.get(i + 1), visits);
					}
					else {
						// add standard lesson to next standard lesson, and revision lesson
						if(i>7) {
							continue;
						}
						else {
							prev = (Lesson) nodes.get(i + 2).getElement();
							next.setgrade(rand.nextInt(60)+40);
							graph.addNode(curr);
							graph.addNode(next);
							//						graph.addNode(prev);
							graph.addEdge(nodes.get(i), nodes.get(i + 2), visits);
							graph.addEdge(nodes.get(i + 2), nodes.get(i + 1), visits);
							graph.addEdge(nodes.get(i + 1), nodes.get(i), visits);
						}
					}
				}
				i++;
			}
			finished = true;
		}
		return this.graph;
	}
}
