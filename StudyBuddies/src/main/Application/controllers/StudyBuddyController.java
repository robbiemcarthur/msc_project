package main.Application.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import main.Application.models.KnowledgeGraph;
import main.Application.models.Lesson;
import main.Application.views.StudyBuddyView;
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
	private boolean found;
	private ArrayList<KnowledgeGraph> studentGraphs;
	private HashMap<String, Integer> userMap;
	// use hashmap or something to have user/keys with graph positions in g list

	public StudyBuddyController() {
		v = new StudyBuddyView(this);
		found = false; 
	}

	/**
	 * Method enables users to add their profiles, it will prompt for a username
	 * and therefore assign space in userMap for their credentials. 
	 * 
	 * Map will be used to link to their KnowledgeGraph in studentGraph list.
	 * If user already exists, program will prompt user to select alternative username.
	 */
	public void addProfile() {
		
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
	 * Method uses simple feature extraction, accumulating in/out degrees
	 * of input graph to compare with graph set and returning 
	 * suitable KnowledgeGraph.
	 * 
	 * @param graph - KnowledgeGraph used to find buddy
	 * @return graph - n x 2 matrix representation of graph
	 */
	public KnowledgeGraph SFE(KnowledgeGraph graph) {

		return null;
	}

	public KnowledgeGraph cluster() {
		return null;
	}
}
