package main.Application.models;
import java.util.ArrayList;
public class Cluster {
	private int head;
	public double WCSS;
	private ArrayList<Double> distances;
	private ArrayList<KnowledgeGraph> graphs;
	
	public Cluster(int head, ArrayList<Double> distances, ArrayList<KnowledgeGraph> graphs) {
		this.head = head;
		WCSS = 0.0;
		this.distances = distances;
		this.graphs = graphs;
	}
	
	public Cluster() {
		head = 0;
		WCSS = 0.0;
		distances = new ArrayList<Double>();
		graphs = new ArrayList<KnowledgeGraph>();
	}
	
	/**
	 * Calculate this cluster's sum of squares
	 */
	public void sumOfSquares() {
		double temp = 0;
		for(Double distance: distances) {
			temp += distance * distance;
		}
		WCSS = temp;
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
	
	public double getWCSS() {
		return this.WCSS;
	}
	
	// SETTERS
	public void setHead(int h) {
		this.head = h;
	}
	
	public void setWCSS(Double w) {
		this.WCSS = w;
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
