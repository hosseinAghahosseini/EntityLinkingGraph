package entityLinkingGraph;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public class Graph {

	private Set<Vertex> vertices;

	public Set<Vertex> getVertices() {
		return vertices;
	}

	private int countVertices;
	private int countEdges;

	public Graph() {
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

	private Vertex findVertex(int documentId) {
		return this.vertices.stream().filter(vertex -> documentId == vertex.getData()).findAny().orElse(null);
	}

	public int getCountVertices() {
		return countVertices;
	}

	public int getCountEdges() {
		return countEdges;
	}

}
