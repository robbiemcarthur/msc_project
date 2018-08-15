package main.Application.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import main.Application.models.KnowledgeGraph;
import main.Application.models.Lesson;
import main.Application.models.Student;
import main.Application.models.KnowledgeGraph.Node;

public class GraphController {
	private int visits;
	private boolean finished;
	private Lesson curr, prev, next;
	private ArrayList<KnowledgeGraph.Node> nodes;
	private ArrayList<KnowledgeGraph> graphs;
	private ArrayList<Lesson> lessons;
	private KnowledgeGraph graph;
	private Random rand;


	public GraphController() {
		visits = 0;
		finished = false;
		curr = new Lesson();
		prev = new Lesson();
		next= new Lesson();
		nodes = new ArrayList<KnowledgeGraph.Node>();
		lessons = new ArrayList<Lesson>();
		graphs = new ArrayList<KnowledgeGraph>();
		graph = new KnowledgeGraph(0);
		rand = new Random();
	}

	public int [][] computeDistance() {
		return null;
	}
	
	public String getDegrees(KnowledgeGraph graph) {
		this.graph = graph;
		int in = 0;
		int out = 0;
		String degrees = "";
		Iterator<KnowledgeGraph.Node> iter = graph.nodes();
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
				in += this.graph.degree(iter.next());
				out += this.graph.outDegree(iter.next());
				System.out.println(in);
				System.out.println(out);
			}
			System.out.println("Graph: \nIn degree: " + in + "\nOut degree: " + out);
		}
	}

	public KnowledgeGraph getKnowledgeGraph(ArrayList<Lesson> lessons, KnowledgeGraph g, int num) {
		this.lessons = lessons;
		this.graph = g;
		int count = 0;
		for(Lesson l: lessons) {
			while(count<num) {
				l.setgrade(rand.nextInt(60)+40);
				KnowledgeGraph.Node n = (Node) graph.addNode(l);
				count++;
			}
			count = 0;
		}
		randomizeEdges();
		return graph;
	}
	
	public void randomizeEdges() {
		int count = 0;
		int e = rand.nextInt(4);
		Lesson l = new Lesson();
		Iterator<KnowledgeGraph.Node> iter = graph.nodes();
		int randomizer = 0;
		KnowledgeGraph.Node pred = new KnowledgeGraph.Node(l);
		KnowledgeGraph.Node revision = new KnowledgeGraph.Node(l);
		finished = false;
		while(iter.hasNext()) {
			count++;
			randomizer = rand.nextInt(6);
			if(count<e) {
				randomizer = 7;
			}
			visits = rand.nextInt(2);
			KnowledgeGraph.Node succ = iter.next();
			if(iter.hasNext()) {
			revision = iter.next();
			}
			if(iter.hasNext()) {
			pred = iter.next();
			}
			switch(randomizer){
			case 0:
				graph.addEdge(pred, succ, visits);
				break;
			case 1:
				graph.addEdge(succ, pred, visits);
				graph.addEdge(pred, succ, visits);
				graph.addEdge(revision, pred, visits);
				graph.addEdge(pred, revision, visits);
				break;
			case 2:
				graph.addEdge(succ, revision,visits);
				graph.addEdge(revision,pred,visits);
				graph.addEdge(pred,succ,visits);
				break;
			case 3:
				graph.addEdge(succ, pred, visits);
				graph.addEdge(pred, succ, visits);
				break;
			case 4:
				graph.addEdge(pred, revision, visits);
				graph.addEdge(revision, succ, visits);
				break;
			case 5:
				graph.addEdge(pred, revision, visits);
				graph.addEdge(revision, revision, visits);
				graph.addEdge(revision, succ, visits);
				break;
			case 6:
				graph.addEdge(pred, revision, visits);
				graph.addEdge(revision, succ, visits);
				graph.addEdge(succ, revision, visits);
			case 7:
				l = (Lesson) pred.getElement();
				l.setgrade(rand.nextInt(40));
				l = (Lesson) succ.getElement();
				l.setgrade(rand.nextInt(40));
				l = (Lesson) revision.getElement();
				l.setgrade(rand.nextInt(40));
				continue;
			}
		}
	}

	public KnowledgeGraph projectRoute(ArrayList<KnowledgeGraph.Node> nodes, KnowledgeGraph g) {
		this.nodes = nodes;
		this.graph = g;
		int count = 0;
		boolean finished = false;
		while(!finished)
		{
			for(int i = 0; i < nodes.size(); i++) {
				visits = rand.nextInt(6);
				if(i==nodes.size()-1) {
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
						if(i>nodes.size()-2) {
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
		//		degreeTest();
		return this.graph;
	}
	
	public void reverseIterator() {
		
	}

	public void reset() {
		graph.clear();
	}
}
