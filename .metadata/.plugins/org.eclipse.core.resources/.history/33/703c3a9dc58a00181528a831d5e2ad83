package StudentGraph;

import java.util.ArrayList;
import java.util.Random;

import NameGenerator.NameGenerator;

public class KnowledgeGraphFactory implements GraphFactory {
	private KnowledgeGraph g;
	private ArrayList<Lesson> LessonList;
	private KnowledgeGraph.Node n;
	private Random rand;
	private Lesson l;
	private Student s;
	private NameGenerator ng;

	public KnowledgeGraphFactory() {
		g = new KnowledgeGraph();
		s = new Student();
		ng = new NameGenerator();
		ng.loadNames(10000);
		rand = new Random();
	}

	@Override
	public Graph createGraph() {
		g = new KnowledgeGraph();
		return g;
	}

	public KnowledgeGraph createMathGraph() {
		g = new KnowledgeGraph();
		int grade = rand.nextInt(100);
		String teacher = ng.GenerateName();
		String course = "Math";
		ArrayList<String> concepts = new ArrayList();
		concepts.add("Math 1");
		concepts.add("Math 2");
		concepts.add("Revision 1");
		concepts.add("Math 3");
		concepts.add("Revision 2");
		for (String con: concepts) {
			l = new Lesson(teacher, course, con, grade, s);
			n = new KnowledgeGraph.Node();
		}
		return g;
	}

}
