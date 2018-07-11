package StudentGraph;

import java.util.ArrayList;

public class KnowledgeGraphFactory implements GraphFactory {
	private KnowledgeGraph g;
	private ArrayList<Lesson> LessonList;
	private Graph.Node n;
	private Lesson l;
	private Student s;
	
	public KnowledgeGraphFactory() {
		createGraph();
		LessonList = new ArrayList();
		n = new Graph.Node() {
			
			@Override
			public void setElement(Object elem) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public Object getElement() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}

	@Override
	public Graph createGraph() {
		g = new KnowledgeGraph();
		return g;
	}

	public KnowledgeGraph createStudentGraph() {
		g = new KnowledgeGraph();
		return g;
	}

}
