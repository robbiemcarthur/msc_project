package main.Application.views;

import java.util.Iterator;
import java.util.Scanner;

import main.Application.controllers.StudyBuddyController;
import main.Application.models.KnowledgeGraph;
import main.Application.models.Lesson;
import main.Application.models.KnowledgeGraph.Node;
/**
 * 
 * @author Robbie McArthur - 2098323m
 *
 * View class to work with StudyBuddy controller. Class 
 * outputs information to console depending on study
 * buddy activities carried out by the user.
 *
 */

public class StudyBuddyView {
	private StudyBuddyController c;
	private Scanner scanner;
	private String input, userID;
	
	public StudyBuddyView(StudyBuddyController c) {
		this.c = c;
		scanner = new Scanner(System.in);
		input = "";
	}
	
	public void SBMenu() {
		System.out.println("/////////// Study Buddy Mode ///////////"
				+ "\nPlease select from the following options...."
				+ "\nUpload your Knowledge Graph (1)"
				+ "\nRetrieve your existing Knowledge Graph (2)"
				+ "\nFind buddy to cover a Knowledge Gap (3)"
				+ "\nFind buddy with similar learning ability (4)"
				+ "\nExit (5)"
				);
	}
	
	
	public void getUser() {
		System.out.println("\n\nWhat is your user id? ");
	}
	
	public void addUser() {
		System.out.println("\n\nPlease input a user ID to create an account ");
	}
	
	public void similarLearningAbility() {
		System.out.println("\n\nFinding you a user with similar learning ability... ");
	}
	
	public void coverKnowledgeGap() {
		System.out.println("\n\nFinding you a user to cover knowledge gap... ");
	}
	
	public void buddyMatch(KnowledgeGraph graph) {
		System.out.println("\n\nFound study buddy with the following knowledge graph: ");
		c.showBuddyDetails(graph);
	}
	
	public String getUserInput() {
		input = scanner.next();
		scanner.nextLine();
		return input;
	}
}
