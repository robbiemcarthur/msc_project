package main.Application;

import java.util.Scanner;

public class ApplicationView {
	private ApplicationController c;
	private Scanner scanner;
	private String type;
	private int quantity;
	
	public ApplicationView(ApplicationController c) {
		this.c = c;
		scanner = new Scanner(System.in);
		type = "";
		quantity = 0;
	}
	
	public void startUpMessage() {
		System.out.println("Welcome to Graph Generator.\n\n");
	}
	
	public String getGraphType() {
		type = scanner.nextLine();
		return type;
	}
	
	public int getGraphQuantity() {
		quantity = scanner.nextInt();
		return quantity;
	}
}
