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
		finished = false;
	}
	
	public void StartApplication() {
		appView.startUpMessage();
		runApplication();
	}
	
	public void runApplication() {
		while(!finished) {
			
		}
		if(finished) {
			quitApplication();
		}
	}
	
	public void quitApplication() {
		System.out.println("\n\nFinished using Graph Generator. Exiting program. ");
		System.exit(0);
	}
}
