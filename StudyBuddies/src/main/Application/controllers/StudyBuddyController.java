package main.Application.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import main.Application.models.KnowledgeGraph;
import main.Application.models.Lesson;
import main.Application.views.StudyBuddyView;
import main.Factories.KnowledgeGraphFactory;
import main.Application.models.KnowledgeGraph.Node;
/**
 * 
 * @author Robbie McArthur - 2098323m
 *
 * This class provides the control mechanisms for the Study Buddy
 * section of the program. 
 * 
 * It will enable users to add their own concept graph, and use it
 * to find relevant study buddies, using numerous distance measuring
 * algorithms such as simple feature extraction and graph clustering.
 *
 */

public class StudyBuddyController {
	private StudyBuddyView v;
	private String input;
	private int choice, userID;
	private boolean found, finished;
	private ArrayList<KnowledgeGraph> studentGraphs;
	private HashMap<String, Integer> userMap;
	private HashMap<Integer, KnowledgeGraph> graphMap;
	private HashMap<Integer, int[][]> vectorMap;
	private KnowledgeGraph graph, userGraph;;
	// use hashmap or something to have user/keys with graph positions in g list

	public StudyBuddyController(KnowledgeGraph graph, ArrayList<KnowledgeGraph> graphs, int u) {
		v = new StudyBuddyView(this);
		this.graph = graph;
		this.studentGraphs = graphs;
		this.userID = u;
		found = false; 
		finished = false;
		input = "";
		choice = 0;
	}


	public void start(ApplicationController c) {
		while(!finished) {
			v.SBMenu();
			choice = Integer.parseInt(v.getUserInput());
			switch(choice) {
			case 1:
				System.out.println("\nEntering add profile function....");	
				addProfile();
				break;
			case 2:
				System.out.println("\nEntering upload graph function....");	
				System.out.println("Which graph are you uploading? ");
				choice = Integer.parseInt(v.getUserInput());
				uploadGraph(choice);
				break;
			case 3:
				System.out.println("\nEntering get profile function....");	
				getProfile();
				break;
			case 4:
				v.similarLearningAbility();
				cluster();
				v.buddyMatch(graph);
				break;
			case 5:
				v.coverKnowledgeGap();
				SFE(graph, studentGraphs);
				v.buddyMatch(graph);
				break;
			case 6:
				System.out.println("\nReturning to main menu....");	
				c.returnToMainMenu();
				break;
			default:
				System.out.println("\nPlease enter a valid choice.");	
			}
		}
	}
	/**
	 * Method enables users to add their profiles, it will prompt for a username
	 * and therefore assign space in userMap for their credentials. 
	 * 
	 * Map will be used to link to their KnowledgeGraph in studentGraph list.
	 * If user already exists, program will prompt user to select alternative username.
	 */
	public void addProfile() {
		v.addUser();
		input = v.getUserInput();
		if(userMap.containsKey(input)) {
			System.out.println("Error. User already exists");
		}
		else {
			for(;;) {
				if(!(userMap.containsValue(userID)))
				{
					userMap.put(input, userID);
					System.out.println("User created: " + input + " ID: " + userMap.get(input));
				}
				else {
					userID++;
				}
			}
		}
	}

	/**
	 * At this stage user chooses from set of graphs within program. 
	 * 
	 * Future development to implement capability to input own graph.
	 * 
	 * @param choice
	 */
	public void uploadGraph(int choice) {
		userGraph = studentGraphs.get(choice);
		System.out.println("User graph successfully uploaded from graph set");
	}
	
	/**
	 * This version will enable users to input their own knowledgegraph, 
	 * although edges will still be randomized.
	 * 
	 * -- actually, allow users to input grades && dictate edges based on input.
	 */
	public void uploadGraph(KnowledgeGraphFactory g) {
		graph = g.getGraph();
		
	}

	public void getProfile() {
		v.getUser();
		input = v.getUserInput();
	}

	/**
	 * Method prints the details of the Knowledge Graph of recommended
	 * study buddy for user. 
	 *  
	 * @param graph - graph of recommended study buddy
	 */
	public void showBuddyDetails(KnowledgeGraph graph) {
		System.out.println("\n\nFound study buddy with the following knowledge graph: ");
		Iterator iter = graph.nodes();
		while(iter.hasNext()) {
			KnowledgeGraph.Node n = (Node) iter.next();
			Lesson les = (Lesson) n.getElement();
			System.out.println("ID: " + les.getID() + " Course: " + les.getCourse() 
			+ " Concept: " + les.getConcept() + " Teacher: " 
			+ les.getTeacher() + " Grade: " + les.getGrade() + " Student: " + les.getStudent());
		}
	}

	/**
	 * Method uses simple feature extraction, computing the distance between
	 * in/out degrees of the nodes in each graph within the graph set.
	 * 
	 * A recommendation of the k - nearest neighbouring graphs is returned.
	 * 
	 * @param graph - KnowledgeGraph used to find buddy
	 * @return graph - n x 2 matrix representation of graph
	 */
	public KnowledgeGraph SFE(KnowledgeGraph graph, ArrayList<KnowledgeGraph> graphs) {
		int[][] query = new int[2][graphs.size()];
		for(KnowledgeGraph g: graphs ) {
			query = generateVector(query, graph);
		}
		return graph;
	}

	public void computeDistance() {

	}
	public int[][] generateVector(int[][] query, KnowledgeGraph graph) {
		Iterator iter = graph.nodes();
		int column = 0; int row = 0;
		// first, get in degree in column 1
		// then out degree in column 2
		while(iter.hasNext()) {
			KnowledgeGraph.Node n = (Node) iter.next();
			System.out.println("Adding to vector " + graph.degree(n));
			query[column][row] = graph.degree(n);
			System.out.println("Adding to vector " + graph.outDegree(n));
			query[column+1][row] = graph.outDegree(n);
			row++;
		}
		return query;
	}

	public KnowledgeGraph cluster() {
		return null;
	}
}
