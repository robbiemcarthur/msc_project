package main.Application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import main.StudentGraph.KnowledgeGraph;
import main.StudentGraph.KnowledgeGraph.Node;
import main.StudentGraph.Lesson;
import main.StudentGraph.Student;

public class GraphController {
	private boolean passed;
	private int visits;
	private final int MAX_VISITS = 2;
	private Student s;
	private Lesson curr, prev, next;
	private KnowledgeGraph.Node _curr, succ, pred;
	private ArrayList<KnowledgeGraph.Node> nodes;
	private ArrayList<KnowledgeGraph> graphs;
	private ArrayList<Lesson> lessons;
	private KnowledgeGraph graph;
	private Random rand;


	public GraphController() {
		visits = 0;
		s = new Student();
		curr = new Lesson();
		prev = new Lesson();
		next= new Lesson();
		nodes = new ArrayList();
		lessons = new ArrayList();
		graphs = new ArrayList<KnowledgeGraph>();
		graph = new KnowledgeGraph(0);
		passed = false;
		rand = new Random();
	}

	public String getDegrees(KnowledgeGraph graph) {
		this.graph = graph;
		int in = 0;
		int out = 0;
		String degrees = "";
		Iterator<KnowledgeGraph.Node> iter = this.graph.nodes();
		while(iter.hasNext()) {
			KnowledgeGraph.Node n = iter.next();
			in += this.graph.degree(n);
			out += this.graph.outDegree(n);
		}
		degrees = "Graph: \nIn degree: " + in + "\nOut degree: " + out;
		return degrees;
	}
	
	public void degreeTest() {
		int in = 0;
		int out = 0;
		for(KnowledgeGraph g: graphs) {
			Iterator<KnowledgeGraph.Node> iter = g.nodes();
			while(iter.hasNext()) {
				KnowledgeGraph.Node n = iter.next();
				in += this.graph.degree(n);
				out += this.graph.outDegree(n);
				System.out.println(in);
				System.out.println(out);
			}
			System.out.println("Graph: \nIn degree: " + in + "\nOut degree: " + out);
		}
	}
	
	public KnowledgeGraph route(ArrayList<KnowledgeGraph.Node> nodes, KnowledgeGraph g) {
		this.nodes = nodes;
		this.graph = g;
		int count = 0;
		boolean finished = false;
		while(!finished)
		{
			for(int i = 0; i < nodes.size(); i++) {
				visits = rand.nextInt(6);
				if(i==8) {
					curr = (Lesson) nodes.get(i).getElement();
					curr.setgrade(rand.nextInt(60)+40);
					KnowledgeGraph.Node _curr = (Node) graph.addNode(curr);
					finished = true;
					// if high amount of visits, add revision lesson
					if(visits>4)
					{
						curr = (Lesson) nodes.get(i + 1).getElement();
						curr.setgrade(rand.nextInt(40)-1);
						KnowledgeGraph.Node succ = (Node) graph.addNode(curr);
						graph.addEdge(_curr, succ, visits);
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
						KnowledgeGraph.Node _curr = (Node) graph.addNode(curr);
						KnowledgeGraph.Node succ = (Node) graph.addNode(next);
						graph.addEdge(_curr, succ, visits);
					}
					else {
						// add standard lesson to next standard lesson, and revision lesson
						if(i>7) {
							continue;
						}
						else {
							prev = (Lesson) nodes.get(i + 2).getElement();
							next.setgrade(rand.nextInt(60)+40);
							KnowledgeGraph.Node _curr = (Node) graph.addNode(curr);
							KnowledgeGraph.Node succ = (Node) graph.addNode(next);
							KnowledgeGraph.Node pred = (Node) graph.addNode(prev);
							graph.addEdge(_curr, pred, visits);
							graph.addEdge(pred, succ, visits);
							graph.addEdge(succ, _curr, visits);
						}
					}
				}
				i++;
			}
			finished = true;
		}
		graphs.add(graph);
		degreeTest();
		return this.graph;
	}
}
