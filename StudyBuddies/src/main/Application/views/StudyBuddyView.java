package main.Application.views;

import java.util.Scanner;

import main.Application.controllers.StudyBuddyController;
import main.Application.models.KnowledgeGraph;
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
	private String input;
	
	public StudyBuddyView(StudyBuddyController c) {
		this.c = c;
		scanner = new Scanner(System.in);
		input = "";
	}
	
	public void SBMenu() {
		System.out.println("/////////// Study Buddy Mode ///////////\n"
				+ "\nPlease select from the following options....\n"
				+ "\nShow my Knowledge Graph (1)"
				+ "\nShow my buddies (2)"
				+ "\nFind buddies using Graph Clustering (3)"
				+ "\nFind buddies using Simple Feature Extraction (4)"
				+ "\nCheck buddy compatibility (5)"
				+ "\nReturn to Main Menu (6)"
				);
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
