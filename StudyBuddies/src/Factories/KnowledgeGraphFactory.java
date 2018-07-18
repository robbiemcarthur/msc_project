package Factories;

import java.util.ArrayList;
import java.util.Random;

import NameGenerator.NameGenerator;
import StudentGraph.Graph;
import StudentGraph.KnowledgeGraph;
import StudentGraph.Lesson;
import StudentGraph.KnowledgeGraph.Node;

public class KnowledgeGraphFactory implements GraphFactory {
	private KnowledgeGraph g;
	private ArrayList<Lesson> LessonList;
	private KnowledgeGraph.Node n;
	private Random rand;
	private NameGenerator ng;

	public KnowledgeGraphFactory() {
		g = new KnowledgeGraph();
		ng = new NameGenerator();
		ng.loadNames(10000);
		rand = new Random();
	}

	@Override
	public Graph createGraph() {
		return new KnowledgeGraph();
	}

	public KnowledgeGraph createMathGraph() {
		g = new KnowledgeGraph();
		String teacher = ng.GenerateName();
		String course = "Math";
		ArrayList<String> concepts = new ArrayList();
		concepts.add("Math 1");
		concepts.add("Math 2");
		concepts.add("Revision 1");
		concepts.add("Math 3");
		concepts.add("Revision 2");
		for (String con: concepts) {
			Lesson l = new Lesson();
			Object lesson = l;
			n = new KnowledgeGraph.Node(lesson);
		}
		return g;
	}

}
