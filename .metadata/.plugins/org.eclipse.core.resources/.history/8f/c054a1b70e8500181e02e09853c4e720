package StudentGraph;

public class KnowledgeGraphFactory implements GraphFactory {
	KnowledgeGraph g;
	Graph.Node a,b,c,d,e,f;
	Student s;

	@Override
	public Graph createGraph() {
		g = new KnowledgeGraph();
		return g;
	}

	public KnowledgeGraph createStudentGraph() {
		g = new KnowledgeGraph();
		a = g.addNode("A");
		b = g.addNode("B");
		c = g.addNode("Revision A");
		d = g.addNode("C");
		e = g.addNode("Revision B");
		f = g.addNode("D");
		g.addEdge(a, b);
		g.addEdge(b, c);
		g.addEdge(c, a);
		g.addEdge(b, d);
		g.addEdge(d, f);
		g.addEdge(f, e);
		return g;
	}

}
