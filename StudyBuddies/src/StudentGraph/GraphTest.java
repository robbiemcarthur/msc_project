package StudentGraph;

import java.util.Iterator;

public class GraphTest {
	public static void main(String[] args) {
		Digraph graph = new KnowledgeGraph();
		Lesson math = new Lesson();

		Graph.Node nodeA = graph.addNode(math);
		Graph.Node nodeB = graph.addNode("Revision 1");
		Graph.Node nodeC = graph.addNode("Math 2");
		Graph.Node nodeD = graph.addNode("Revision 2");
		Graph.Node nodeE = graph.addNode("Math 3");
		Graph.Node nodeF = graph.addNode("Revision 3");
		Graph.Node nodeG = graph.addNode("G");
		Graph.Node nodeH = graph.addNode("H");
		Graph.Node nodeI = graph.addNode("I");

		graph.addEdge(nodeA, nodeB);
		graph.addEdge(nodeA, nodeE);
		graph.addEdge(nodeB, nodeC);
		graph.addEdge(nodeB, nodeD);
		graph.addEdge(nodeC, nodeG);
		graph.addEdge(nodeC, nodeH);
		graph.addEdge(nodeD, nodeE);
		graph.addEdge(nodeD, nodeF);
		graph.addEdge(nodeE, nodeB);
		graph.addEdge(nodeE, nodeF);
		graph.addEdge(nodeF, nodeH);
		graph.addEdge(nodeG, nodeI);
		graph.addEdge(nodeH, nodeI);

		Iterator iter;

		iter = graph.nodes();
		System.out.println("Visiting all nodes in graph: ");
		while (iter.hasNext()) {
			Graph.Node node = (Graph.Node) iter.next();
			System.out.print(node.getElement() + " ");
		}
		System.out.println("");
		iter = graph.neighbors(nodeE);
		System.out.println("Visiting all neighbors of node in graph:");
		while (iter.hasNext()) {
			Graph.Node node = (Graph.Node) iter.next();
			System.out.print(node.getElement() + " ");
		}
		System.out.println("");
		iter = graph.successors(nodeA);
		System.out.println("Visiting all successors of node in graph:");
		while (iter.hasNext()) {
			Graph.Node node = (Graph.Node) iter.next();
			System.out.print(node.getElement() + " ");
		}
		System.out.println("");
		iter = graph.edges();
		while(iter.hasNext()) {
			Graph.Edge e = (Graph.Edge) iter.next();
			System.out.print(e.getAttribute());
		}
	KnowledgeGraphFactory g = new KnowledgeGraphFactory();
	graph =  g.createStudentGraph();
	iter = graph.nodes();
	System.out.println("Visiting all nodes in graph:");
	while (iter.hasNext()) {
		Graph.Node node = (Graph.Node) iter.next();
		System.out.print(node.getElement() + ", ");
	}
	}
}
