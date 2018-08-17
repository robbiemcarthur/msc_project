package main.Application.views;

import java.util.Scanner;

import main.Application.controllers.GraphGeneratorController;

public class GraphGeneratorView {
	private Scanner scanner;
	private String input;
	
	public GraphGeneratorView(GraphGeneratorController c) {
		scanner = new Scanner(System.in);
		input = "";
	}
	
	public void startUpMessage() {
		System.out.println("Welcome to Study Buddies!\n\n");
	}
	
	public void printMenu() {
		System.out.println("/////////// MAIN MENU ///////////\n"
				+ "\nGenerate Projected Graphs (1)"
				+ "\nGenerate Knowledge Graphs (2)"
				+ "\nFind a Study Buddy (3)"
				+ "\nPrint Graphs (4)"
				+ "\nWrite Graph File (5)"
				+ "\nGet Degrees (6)"
				+ "\nQuit (7)");
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
