package entityLinkingGraph;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Stack;

public class MyGraph {

	private Set<Vertex> vertices;
	private int countVertices;
	private int countEdges;

	public MyGraph() {
		this.vertices = new HashSet<Vertex>();
		this.countVertices = 0;
		this.countEdges = 0;
	}

	public void printVertices() {
		this.vertices.stream().map(Vertex::toString).forEach(result -> System.out.print(result + " "));
	}

	public void addVertex(Integer documentId) {
		this.vertices.add(new Vertex(documentId));
		this.countVertices++;

	}

	public void removeVertex(int documentIdToRemove) {

		try {
			// this comment for List Neighbor
			// this.vertices.set(new Long(
			// StreamEx.of(this.vertices).indexOf(vertex -> documentIdToRemove ==
			// vertex.getData()).getAsLong())
			// .intValue(),
			// null);
			this.vertices.remove(this.findVertex(documentIdToRemove));
			this.countVertices--;
		} catch (NoSuchElementException e) {
			System.out.println("no such vertex.");
		}

	}

	public void addEdge(int start, int destination) {
		Vertex startVertex = this.findVertex(start);
		Vertex destinationVertex = this.findVertex(destination);

		if (startVertex != null && destinationVertex != null) {
			this.countEdges++;
			startVertex.addNeighbour(destinationVertex);
		} else {
			System.out.println("vertices does not exist.");
		}
	}

	public void removeEdge(int start, int destination) {
		Vertex startVertex = this.findVertex(start);
		Vertex destinationVertex = this.findVertex(destination);

		if (startVertex != null && destinationVertex != null) {
			this.countEdges--;
			startVertex.removeNeighbour(destinationVertex);
		} else {
			System.out.println("vertices does not exist.");
		}
	}

	public void topologicalSort() {

		this.resetGraph();

		Stack<Vertex> stack = new Stack<>();
		Stack<Vertex> result = new Stack<>();

		for (Vertex vertex : this.vertices) {
			if (!vertex.isVisit()) {
				stack.push(vertex);
				vertex.setVisit(true);

				while (!stack.isEmpty()) {
					Vertex actualVertex = stack.pop();
					if (this.checkNeighbour(actualVertex.getNeighbours()))
						result.push(actualVertex);
					else {
						stack.push(actualVertex);
						for (Vertex neighbour : actualVertex.getNeighbours()) {
							if (!neighbour.isVisit()) {
								stack.push(neighbour);
								neighbour.setVisit(true);
							}
						}
					}
				}
			}
		}

		while (!result.isEmpty()) {
			System.out.print(result.pop() + " ");
		}

	}

	private boolean checkNeighbour(Set<Vertex> neighbours) {
		boolean result = true;
		if (neighbours.isEmpty())
			return result;
		else {
			for (Vertex vertex : neighbours) {
				if (!result)
					break;
				result = result & vertex.isVisit();
			}
			return result;
		}
	}

	private void resetGraph() {
		for (Vertex vertex : this.vertices) {
			vertex.setVisit(false);
		}
	}

	private Vertex findVertex(int documentId) {
		return this.vertices.stream().filter(vertex -> documentId == vertex.getData()).findAny().orElse(null);
	}

	public int getCountVertices() {
		return countVertices;
	}

	public int getCountEdges() {
		return countEdges;
	}

	public Set<Vertex> getVertices() {
		return vertices;
	}

}
