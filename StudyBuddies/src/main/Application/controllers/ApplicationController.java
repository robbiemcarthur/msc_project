package main.Application.controllers;

import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;

import main.Application.models.KnowledgeGraph;
import main.Application.models.Lesson;
import main.Application.models.Student;
import main.Application.models.KnowledgeGraph.Node;
import main.Application.views.ApplicationView;
import main.Factories.KnowledgeGraphFactory;
import main.Factories.LessonFactory;
import main.Logger.Logger;
import main.NameGenerator.NameGenerator;

public class ApplicationController {
	private ApplicationView appView;
	private GraphController gController;
	private KnowledgeGraph graph;
	private KnowledgeGraph.Node curr;
	private KnowledgeGraphFactory graphF;
	private Lesson lesson;
	private LessonFactory lessonF;
	private ArrayList<Lesson> lessons;
	private ArrayList<KnowledgeGraph> graphs;
	private ArrayList<KnowledgeGraph.Node> nodes;
	private ArrayList<String> concepts;
	private String course, teacher, filename;
	private int grade, quantity, lessonID,studentID;
	private boolean finished;
	private Iterator<KnowledgeGraph.Node> iter;
	private Logger logger;

	public ApplicationController() {
		appView = new ApplicationView(this);
		gController = new GraphController();
		graph = new KnowledgeGraph(0);
		graphF = new KnowledgeGraphFactory();
		lesson = new Lesson();
		lessonF = new LessonFactory();
		nodes = new ArrayList<KnowledgeGraph.Node>();
		lessons = new ArrayList<Lesson>();
		graphs = new ArrayList<KnowledgeGraph>();
		concepts = new ArrayList<String>();
		course = "";
		teacher = "";
		grade = 0;
		quantity = 0;
		lessonID = 0;
		studentID = 1;
		finished = false;
		filename = "graphs.csv";
		logger = new Logger();
	}

	public void StartApplication() {
		appView.startUpMessage();
		logger.initialise(filename);
		runApplication();
	}

	public void runApplication() {
		while(!finished) {
			appView.printMenu();
			String input = appView.getUserInput();
			if(input.equalsIgnoreCase("n")) {
				finished = true;
				break;
			}
			else if(input.equalsIgnoreCase("p"))
			{
				printGraphs();
			}
			else if(input.equalsIgnoreCase("w"))
			{
				writeToFile();
			}
			else if(input.equalsIgnoreCase("d")) {
				getGraphDegrees();
			}
			else if(input.equalsIgnoreCase("y")) {
				appView.askGraphType();
				course = appView.getUserInput();
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
				initialiseConcepts();
				initialiseLessons();
				initialiseNodes();
				createGraphs(quantity);
			}
		}
		if(finished) {
			quitApplication();
		}
	}

	public void createGraphs(int quantity) {
		for(int i = 0; i < quantity; i++)
		{
			graph = graphF.getGraph();
			graph = gController.route(nodes, graph);
			graphs.add(graph);
			reInitialise();
		}
	}
	
	public void getGraphDegrees() {
		System.out.println("\nWhich graph would you like to get degrees for?" );
		int input = Integer.parseInt(appView.getUserInput());
		System.out.println(gController.getDegrees(graphs.get(input)));
	}


	//////////FILE METHODS ///////////
	
	public void writeToFile() {
		logger.appendHeader();
		for(KnowledgeGraph g: graphs) {
			logger.writeLine("Graph " + g.id());
			iter = g.nodes();
			Student s = new Student();
			ArrayList<String> contents = new ArrayList<String>();
			while(iter.hasNext()) {
				KnowledgeGraph.Node n = iter.next();
				Lesson les = (Lesson) n.getElement();
				les.setStudent(studentID);
				contents.add("\n");
				contents.add(Integer.toString(les.getID()));
				contents.add(les.getCourse());
				contents.add(les.getConcept());
				contents.add(les.getTeacher());
				contents.add(Integer.toString(les.getGrade()));
				contents.add(Integer.toString(les.getID()));
				contents.add(Integer.toString(les.getStudent()));
				logger.writeToFile(contents);
				contents.clear();
			}
			studentID++;
		}
	}

	public void printGraphs() {
		for(KnowledgeGraph g: graphs) {
			iter = g.nodes();
			System.out.println("Graph "+ g.id());
			while(iter.hasNext()) {
				KnowledgeGraph.Node n = iter.next();
				Lesson les = (Lesson) n.getElement();
				les.setStudent(studentID);
				System.out.println("ID: " + les.getID() + " Course: " + les.getCourse() 
				+ " Concept: " + les.getConcept() + " Teacher: " 
				+ les.getTeacher() + " Grade: " + les.getGrade() + " Student: " + les.getStudent());
			}
			studentID++;
		}
	}

	
	////////// INITIALISATION METHODS ///////////
	
	public void reInitialise() {
		nodes.clear();
		lessons.clear();
		initialiseLessons();
		initialiseNodes();
	}
	
	public void initialiseNodes() {
		for(Lesson lesson: lessons) {
			curr = new KnowledgeGraph.Node(lesson);
			nodes.add(curr);
		}
	}
	
	public void initialiseLessons() {
		lessonID = 0;
		for(String con : concepts) {
			lessonID++;
			grade = 0;
			lesson = lessonF.getLesson(teacher, course, con, grade, lessonID, 0);
			lessons.add(lesson);
			lessonID++;
			lesson = lessonF.getLesson(teacher, course, con, grade, lessonID, 0);
			lessons.add(lesson);
		}
	}

	public void initialiseConcepts() {
		concepts.add("A");
		concepts.add("B");
		concepts.add("C");
		concepts.add("D");
		concepts.add("E");
	}

	public void quitApplication() {
		System.out.println("\n\nFinished using Graph Generator. Exiting program. ");
		System.exit(0);
	}
}