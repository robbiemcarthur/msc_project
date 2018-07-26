package main.Application;

import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;

import main.Factories.KnowledgeGraphFactory;
import main.Factories.LessonFactory;
import main.NameGenerator.NameGenerator;
import main.StudentGraph.KnowledgeGraph;
import main.StudentGraph.Lesson;

public class ApplicationController {
	private ApplicationView appView;
	private NameGenerator ng;
	private KnowledgeGraph graph;
	private KnowledgeGraph.Node curr, prev, succ;
	private KnowledgeGraph.Edge edge;
	private KnowledgeGraphFactory graphF;
	private Lesson lesson;
	private LessonFactory lessonF;
	private Random rand;
	private ArrayList<Lesson> lessons;
	private ArrayList<KnowledgeGraph> graphs;
	private ArrayList<KnowledgeGraph.Node> nodes;
	private ArrayList<KnowledgeGraph.Edge> edges;
	private ArrayList<String> concepts;
	private String course, teacher, student;
	private int grade, quantity, graphID, lessonID;
	private final int MAX_ATTEMPTS = 10;
	private boolean finished;

	public ApplicationController() {
		appView = new ApplicationView(this);
		ng = new NameGenerator();
		ng.loadNames(10000);
		graph = new KnowledgeGraph();
		graphF = new KnowledgeGraphFactory();
		lesson = new Lesson();
		lessonF = new LessonFactory();
		rand = new Random();
		nodes = new ArrayList();
		edges = new ArrayList();
		lessons = new ArrayList();
		graphs = new ArrayList();
		concepts = new ArrayList();
		course = "";
		teacher = "";
		student = "";
		grade = 0;
		quantity = 0;
		graphID = 0;
		lessonID = 0;
		finished = false;
	}

	public void StartApplication() {
		appView.startUpMessage();
		initialiseConcepts();
		initialiseLessons(10);
		initialiseNodes();
		initialiseEdges();
		runApplication();
	}

	public void runApplication() {
		while(!finished) {
			initialiseConcepts();
			appView.printMenu();
			appView.askGraphType();
			course = appView.getUserInput();
			if(course.equalsIgnoreCase("n")) {
				finished = true;
				break;
			}
			else {
				//				lesson.setCourse(course);
				switch(course.toLowerCase()) {
				case "math":
					teacher = "Mr. X";
				case "computing":
					teacher = "Mr. Y";
				case "history":
					teacher = "Mr. Z";
				default:
					teacher = "Mr. A";
				}
				appView.askGraphQuantity();
				quantity = Integer.parseInt(appView.getUserInput());
				createGraph(quantity);
			}
		}
		if(finished) {
			quitApplication();
		}
	}

	public void createGraph(int quantity) {
		initialiseLessons(quantity);
		for(int i = 0; i <= quantity; i++) {
			student = ng.GenerateName();
			increment("graph");
			graph = graphF.createGraph();
			boolean passed = false;
			while(!passed) {
				int attempts = 0;
			}
		}
	}

	public void initialiseNodes() {
		for(Lesson lesson: lessons) {
			curr = new KnowledgeGraph.Node(lesson);
			nodes.add(curr);
		}
		System.out.println(nodes.toString());
	}

	public void initialiseEdges() {
		for(int i = 0; i < nodes.size(); i+=3) {
			edge = new KnowledgeGraph.Edge(nodes.get(i), nodes.get(i++), 0);
			edges.add(edge);
			edge = new KnowledgeGraph.Edge(nodes.get(i++), nodes.get(i+2), 0);
			edges.add(edge);
			edge = new KnowledgeGraph.Edge(nodes.get(i+2), nodes.get(i), 0);
			edges.add(edge);
		}
	}

	public void initialiseLessons(int quantity) {
		for(String con : concepts) {
			lessonID++;
			grade = rand.nextInt(100);
			lesson = lessonF.getLesson(teacher, course, con, grade, lessonID, student);
			lessons.add(lesson);
		}
	}

	public void initialiseConcepts() {
		concepts.add("A");
		concepts.add("B");
		concepts.add("C");
		concepts.add("D");
		concepts.add("E");
		concepts.add("F");
		concepts.add("G");
		concepts.add("H");
		concepts.add("I");
		concepts.add("J");
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
