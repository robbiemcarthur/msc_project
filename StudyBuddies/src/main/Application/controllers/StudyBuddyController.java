package main.Application.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.math.*;

import main.Application.models.KnowledgeGraph;
import main.Application.models.Lesson;
import main.Application.models.Student;
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
	private Student user;
	private int choice, userID;
	private boolean found, finished;
	private ArrayList<KnowledgeGraph> studentGraphs;
	private ArrayList<KnowledgeGraph> recommendedGraphs;
	private ArrayList<Student> students;
	private ArrayList<int[][]> vectors;
	private int[][] query;
	private HashMap<String, Integer> userMap;
	private HashMap<Integer, KnowledgeGraph> graphMap;
	private HashMap<Integer, int[][]> vectorMap;
	private KnowledgeGraph graph, userGraph;
	private Random rand;

	private final int MAX_ITERATIONS = 20;

	public StudyBuddyController(KnowledgeGraph graph, ArrayList<KnowledgeGraph> graphs) {
		v = new StudyBuddyView(this);
		this.graph = graph;
		this.studentGraphs = graphs;
		user = new Student(graph);
		students = new ArrayList<Student>();
		recommendedGraphs = new ArrayList<KnowledgeGraph>();
		vectors = new ArrayList<int[][]>();
		found = false; 
		finished = false;
		input = "";
		choice = 0;
		rand = new Random();
	}


	public void start(GraphGeneratorController c) {
		while(!finished) {
			v.SBMenu();
			try {
				choice = Integer.parseInt(v.getUserInput());
				for(;;) {
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
						System.out.println("\nHow many neighbours do you wish to return?");
						choice = Integer.parseInt(v.getUserInput());
						cluster(choice,studentGraphs);
						break;
					case 5:
						v.coverKnowledgeGap();
						SFE(graph, studentGraphs);
						v.buddyMatch(graph);
						break;
					case 6:
						System.out.println("\nComparing graphs to check buddy compatibility....");	
						System.out.println("\nPlease enter your graph ID: ");
						choice = Integer.parseInt(v.getUserInput());
						KnowledgeGraph q = studentGraphs.get(choice);
						System.out.println("\nPlease enter your buddies graph ID:  ");
						choice = Integer.parseInt(v.getUserInput());
						KnowledgeGraph t = studentGraphs.get(choice);
						computeGraphDistance(q,t);
						break;
					case 7:
						System.out.println("\nReturning to main menu....");	
						c.returnToMainMenu();
					default:
						System.out.println("\nPlease enter a valid choice.");	
					}
				}
			} catch (NumberFormatException e) {
				System.out.println("\nInvalid input");
			}catch (IndexOutOfBoundsException e) {
				System.out.println("\nNo such graph exists. Please try again.");
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
		try {
			input = v.getUserInput();
			if(userMap.containsKey(input)) {
				System.out.println("\nError. User already exists");
			}
			else {
				for(;;) {
					if(!(userMap.containsValue(userID)))
					{
						userMap.put(input, userID);
						System.out.println("\nUser created: " + input + " ID: " + userMap.get(input));
					}
					else {
						userID++;
					}
				}
			} 
		}catch (NumberFormatException e) {
			System.out.println("\nSomething went wrong. Please try again.");
		}catch (NullPointerException e) {
			System.out.println("\nSomething went wrong. Please try again.");
		} finally {
			System.out.println("\nUnknown error.");
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
		try {
			userGraph = studentGraphs.get(choice);
			System.out.println("\nUser graph successfully uploaded from graph set");
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("\nInvalid input");
		}
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
		try {
			v.getUser();
			input = v.getUserInput();
		}catch (IndexOutOfBoundsException e) {
			System.out.println("\nIndex out of bounds. Please try again.");
		}catch (NullPointerException e) {
			System.out.println("\nIndex out of bounds. Please try again.");
		}
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

	public void showBuddyDetails(Student s) {
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
		for(;;)
		{
			try {
				for(KnowledgeGraph g: graphs ) {
					int[][] query = new int[2][graph.size()];
					query = generateVector(query, g);
					vectors.add(query);
				}
				//		vectorTest();
				computeDistance();
				System.out.println("\nHow many neighbours do you wish to return?");
				choice = Integer.parseInt(v.getUserInput());
				returnKNN(choice);
				break;
			} 
			catch(NumberFormatException e) {
				System.out.println("\nSomething went wrong. Please try again.");
			} 
			catch (IndexOutOfBoundsException e) {
				System.out.println("\nIndex out of bounds. Try again.");
				break;
			}
		}
		return graph;
	}

	public void computeDistance() {
		try {
			System.out.println("\nWhich graph number is yours?");
			choice = Integer.parseInt(v.getUserInput());
			query = vectors.get(choice);
			vectors.remove(query);
			double distance = 0.0;
			for(int[][] arr: vectors) {
				double overall = 0.0;
				for(int j = 0; j < graph.size(); j++) {
					distance = 0.0;
					for(int i = 0; i < 2; i ++) {
						distance += Math.pow(query[i][j]-arr[i][j], 2.0);
						//					System.out.println(String.format("Distance between query and matrix is: %.2f", distance));
					}
					distance = Math.sqrt(distance);
					overall += distance;
				}
				System.out.println("\nOverall distance is: " + overall);
				Student s = new Student(graph);
				s.setDistance(overall);
				students.add(s);
			}
		}catch (IndexOutOfBoundsException e) {
			System.out.println("\nIndex out of bounds. Please try again.");
		}
		catch (NumberFormatException e) {
			System.out.println("\nPlease enter a valid number.");
		}
	}

	/**
	 * Function takes two KnowledgeGraph objects and computes Euclidian distance
	 * between them. 
	 * 
	 * @param query - query graph
	 * @param target - target graph
	 * @return distance - double, measure of distance between query and target
	 */
	public double computeGraphDistance(KnowledgeGraph query, KnowledgeGraph target) {
		double distance = 0.0;
		double overall = 0.0;
		try {
			int[][] q = new int[2][query.size()];
			int[][] t = new int[2][target.size()];
			q = generateVector(q, query);
			t = generateVector(t, target);
			for(int j = 0; j < graph.size(); j++) {
				distance = 0.0;
				for(int i = 0; i < 2; i ++) {
					distance += Math.pow(q[i][j]-t[i][j], 2.0);
					//				System.out.println(String.format("Distance between query and matrix is: %.2f", distance));
				}
				distance = Math.sqrt(distance);
				overall += distance;
			}
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("\nIndex out of bounds. Please try again.");
		}
		catch (NumberFormatException e) {
			System.out.println("\nPlease enter a valid number.");
		}
		//		System.out.println("Distance between graph 1 and graph 2 is: " + overall);
		return overall;
	}


	public int median(ArrayList<KnowledgeGraph> list) {
		Double minDistance = Double.MAX_VALUE;
		int minIndex = 0;
		try {
			for(int i = 0; i<list.size(); i++) {
				double d = 0.0;
				for(int j = 0; j<list.size(); j++) {
					d += computeGraphDistance(list.get(i), list.get(j));
				}
				if(d<minDistance) {
					minDistance = d;
					minIndex = i;
				}
			}
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("\nIndex out of bounds. Please try again.");
		}
		catch (NumberFormatException e) {
			System.out.println("\nPlease enter a valid number.");
		}
		return minIndex;
	}

	public void returnKNN(int k) {
		try {
		Collections.sort(students);
		//		System.out.println("Student sort test: ");
		int num = 1;
		//		for(Student s: students) {
		//			System.out.println("Student " + num + " distance: " + s.getDistance());
		//			num++;
		//		}
		System.out.println("\nRecommended students are: ");
		for(int i = 0; i < k; i++) {
			System.out.println("\nStudent: " + students.get(i).getName() + " ID: " + students.get(i).getID() + " distance: " + students.get(i).getDistance());
		}
		showBuddyDetails(students.get(0));
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("\nIndex out of bounds. Please try again.");
		}
		catch (NumberFormatException e) {
			System.out.println("\nPlease enter a valid number.");
		}
	}

	public void vectorTest() {
		try {
			for(int[][] arr: vectors) {
				int nodeNum = 1;
				for(int i = 0; i < 2; i ++) {
					for(int j = 0; j < graph.size()-1; j++) {
						System.out.println("node " + nodeNum++);
						System.out.println("in degree " + arr[i][j]);
						System.out.println("out degree " + arr[i][j+1]);
					}
				}
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("\nIndex out of bounds. Please try again.");
		}
	}

	public int[][] generateVector(int[][] query, KnowledgeGraph graph) {
		Iterator iter = graph.nodes();
		int column = 0; int row = 0;
		// first, get in degree in column 1
		// then out degree in column 2
		while(iter.hasNext()) {
			KnowledgeGraph.Node n = (Node) iter.next();
			query[column][row] = graph.degree(n);
			query[column+1][row] = graph.outDegree(n);
			row++;
		}
		return query;
	}

	public void cluster(int k, ArrayList<KnowledgeGraph> list) {
		try {
			int counter = 0;
			boolean done = false;
			while(counter<MAX_ITERATIONS || !done) {
				ArrayList<KnowledgeGraph> pickedCH = new ArrayList<KnowledgeGraph>();
				int[] clusterheads = new int[k];
				for(int i = 0; i < k; i++) {
					int check = 0;
					int y = rand.nextInt(list.size());
					for(int x: clusterheads) {
						if(x==y) {
							check++;
							if(check>1) {
								i--;
								break;
							}
						}
						else {
							clusterheads[i] = y;
						}
					}
				}
				System.out.println("Starting clusterheads.....");
				int c = 1;
				for(int x: clusterheads) {
					System.out.println("Clusterhead " + c++ + " = " + x);
				}
				for(int x: clusterheads) {
					Double min = Double.MAX_VALUE;
					double distance = 0;
					int minIndex = 0;
					for(int i = 0; i < list.size(); i++) {
						distance = computeGraphDistance(list.get(x), list.get(i));
						if(distance<min) {
							min = distance;
							minIndex = i;
						}
					}
					pickedCH.add(list.get(minIndex));
				}
				for(int i = 0; i < k; i++) {
					if(clusterheads[i] == median(pickedCH))
					{
						done = true;
					}
					clusterheads[i] = median(pickedCH);
				}
				int count = 1;
				System.out.println("Changed clusterheads.....");
				for(int x: clusterheads) {
					System.out.println("Clusterhead " + count++ + " = " + x);
				}
				counter++;
			}
		}catch (IndexOutOfBoundsException e) {
			System.out.println("\nIndex out of bounds. Please try again.");
		}
	}
}
