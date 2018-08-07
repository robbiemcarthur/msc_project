package main.Application.views;

import java.util.Scanner;

import main.Application.controllers.StudyBuddyController;
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
	
	public void startUpMessage() {
		System.out.println("Welcome to Study Buddies!\n\n");
	}
	
	public void printMenu() {
		System.out.println("/////////// MENU ///////////"
				+ "\nPlease select from the following options...."
				+ "\nUpload your Knowledge Graph (1)"
				+ "\nFind buddy to cover a Knowledge Gap (2)"
				+ "\nFind buddy with similar learning ability (3)"
				+ "\nExit (3)"
				);
	}
	
	
	public void askGraphType() {
		System.out.println("\n\nEnter a graph type (math, programming, history, random): ");
	}
	
	public void askGraphQuantity() {
		System.out.println("\n\nEnter graph quantity: ");
	}
	
	public String getUserInput() {
		input = scanner.next();
		scanner.nextLine();
		return input;
	}
}
