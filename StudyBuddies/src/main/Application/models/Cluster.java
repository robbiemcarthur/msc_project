package main.Application.models;
import java.util.ArrayList;
public class Cluster {
	private int head;
	private ArrayList<Double> distances;
	private ArrayList<KnowledgeGraph> graphs;
	
	public Cluster(int head, ArrayList<Double> distances, ArrayList<KnowledgeGraph> graphs) {
		this.head = head;
		this.distances = distances;
		this.graphs = graphs;
	}
	
	public Cluster() {
		head = 0;
		distances = new ArrayList<Double>();
		graphs = new ArrayList<KnowledgeGraph>();
	}
	
	//GETTERS
	public int getHead() {
		return this.head;
	}
	
	public ArrayList<Double> getDistances() {
		return this.distances;
	}
	
	public ArrayList<KnowledgeGraph> getGraphs() {
		return this.graphs;
	}
	
	// SETTERS
	public void setHead(int h) {
		this.head = h;
	}
	
	public void setDistances(ArrayList<Double> d) {
		this.distances = d;
	}
	
	public void addDistance(Double d) {
		distances.add(d);
	}
	
	public void setGraphs(ArrayList<KnowledgeGraph> g) {
		this.graphs = g;
	}
	
	public void addGraph(KnowledgeGraph g) {
		graphs.add(g);
	}
	
	// CLEAN UP
	public void clearDistances() {
		distances.clear();
	}
	
	public void clearGraphs() {
		graphs.clear();
	}
}
