package main.Application;

import java.util.ArrayList;

import main.Factories.KnowledgeGraphFactory;
import main.Factories.LessonFactory;
import main.NameGenerator.NameGenerator;
import main.StudentGraph.KnowledgeGraph;
import main.StudentGraph.Lesson;

public class ApplicationController {
	private ApplicationView appView;
	private NameGenerator ng;
	private KnowledgeGraph graph;
	private KnowledgeGraphFactory graphF;
	private Lesson lesson;
	private LessonFactory lessonF;
	private ArrayList<Lesson> lessons;
	private ArrayList<KnowledgeGraph> graphs;
	private ArrayList<KnowledgeGraph.Node> nodes;
	private ArrayList<KnowledgeGraph.Edge> edges;
	private String type;
	private int quantity, graphID, lessonID;
	private boolean finished;
	
	public ApplicationController() {
		appView = new ApplicationView(this);
		ng = new NameGenerator();
		ng.loadNames(10000);
		graph = new KnowledgeGraph();
		graphF = new KnowledgeGraphFactory();
		lesson = new Lesson();
		lessons = new ArrayList();
		graphs = new ArrayList();
		nodes = new ArrayList();
		edges = new ArrayList();
		type = "";
		quantity = 0;
		graphID = 0;
		lessonID = 0;
		finished = false;
	}
	
	public void StartApplication() {
		appView.startUpMessage();
		runApplication();
	}
	
	public void runApplication() {
		while(!finished) {
			appView.printMenu();
			appView.askGraphType();
			type = appView.getUserInput();
			lesson.setCourse(type);
			quantity = Integer.parseInt(appView.getUserInput());
			createGraphs(quantity);
		}
		if(finished) {
			quitApplication();
		}
	}
	
	public void createGraphs(int quantity) {
		for(int i = 0; i <= quantity; i++) {
			
		}
	}
	
	public void increment(String type) {
		switch (type.toLowerCase()) {
		case "graph":
			graphID++;
		case "lesson":
			lessonID++;
			default:
				break;
		}
	}
	
	public void resetIDs() {
		graphID = 0;
		lessonID = 0;
	}
	
	public void quitApplication() {
		System.out.println("\n\nFinished using Graph Generator. Exiting program. ");
		System.exit(0);
	}
}
