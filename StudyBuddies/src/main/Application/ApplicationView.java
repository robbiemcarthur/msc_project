package main.Application;

import java.util.Scanner;

public class ApplicationView {
	private ApplicationController c;
	private Scanner scanner;
	private String input;
	
	public ApplicationView(ApplicationController c) {
		this.c = c;
		scanner = new Scanner(System.in);
		input = "";
	}
	
	public void startUpMessage() {
		System.out.println("Welcome to Graph Generator.\n\n");
	}
	
	public void printMenu() {
		System.out.println("\t\t\tMENU\t\t\t"
				+ "\nGenerate Graphs (Y)"
				+ "\nQuit (N)");
	}
	
	public void askGraphType() {
		System.out.println("\n\nEnter a graph type (math, programming, history, random: ");
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
