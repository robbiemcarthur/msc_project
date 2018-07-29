package main.Application;

import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;

import main.Factories.KnowledgeGraphFactory;
import main.Factories.LessonFactory;
import main.NameGenerator.NameGenerator;
import main.StudentGraph.KnowledgeGraph;
import main.StudentGraph.KnowledgeGraph.Node;
import main.StudentGraph.Lesson;
import main.StudentGraph.Student;

public class ApplicationController {
	private ApplicationView appView;
	private RouteController router;
	private KnowledgeGraph graph;
	private KnowledgeGraph.Node curr;
	private KnowledgeGraphFactory graphF;
	private Lesson lesson;
	private LessonFactory lessonF;
	private ArrayList<Lesson> lessons;
	private ArrayList<KnowledgeGraph> graphs;
	private ArrayList<KnowledgeGraph.Node> nodes;
	private ArrayList<String> concepts;
	private String course, teacher;
	private int grade, quantity, lessonID,studentID;
	private boolean finished;
	private Iterator iter;
	private Student s;

	public ApplicationController() {
		appView = new ApplicationView(this);
		router = new RouteController();
		s = new Student();
		graph = new KnowledgeGraph(0, s);
		graphF = new KnowledgeGraphFactory();
		lesson = new Lesson();
		lessonF = new LessonFactory();
		nodes = new ArrayList();
		lessons = new ArrayList();
		graphs = new ArrayList();
		concepts = new ArrayList();
		course = "";
		teacher = "";
		grade = 0;
		quantity = 0;
		lessonID = 0;
		studentID = 0;
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
			String input = appView.getUserInput();
			if(input.equalsIgnoreCase("n")) {
				finished = true;
				break;
			}
			else if(input.equalsIgnoreCase("p"))
			{
				printGraphs();
			}
			else {
				course = input;
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
		graph = router.getRoute(nodes, graph);
		graphs.add(graph);
		}
	}

	public void printGraphs() {

		for(KnowledgeGraph g: graphs) {
			iter = g.nodes();
			Student s = new Student();
			System.out.println("Graph "+ g.id());
			while(iter.hasNext()) {
				s = graphF.getStudent();
				KnowledgeGraph.Node n = (KnowledgeGraph.Node) iter.next();
				Lesson les = (Lesson) n.getElement();
				System.out.println("ID: " + les.getID() + " Course: " + les.getCourse() 
				+ " Concept: " + les.getConcept() + " Teacher: " 
				+ les.getTeacher() + " Grade: " + les.getGrade() + " Student: " + s.getID());
			}
		}
	}
	
	public void initialiseNodes() {
		for(Lesson lesson: lessons) {
			curr = new KnowledgeGraph.Node(lesson);
			nodes.add(curr);
		}
		for (KnowledgeGraph.Node node: nodes)
		{
		Lesson l = (Lesson) (node.getElement());
		}
	}

	public void initialiseLessons() {
		for(String con : concepts) {
			lessonID++;
			grade = 0;
			lesson = lessonF.getLesson(teacher, course, con, grade, lessonID);
			lessons.add(lesson);
			lessonID++;
			lesson = lessonF.getLesson(teacher, course, con, grade, lessonID);
			lessons.add(lesson);
		}
		for(Lesson l: lessons) {
			System.out.println("ID: " + l.getID() + " Course: " + l.getCourse() 
			+ " Concept: " + l.getConcept() + " Teacher: " 
			+ l.getTeacher() + " Grade: " + l.getGrade() + " Student: ");
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
