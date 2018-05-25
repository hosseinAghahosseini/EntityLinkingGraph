package entityLinkingGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import one.util.streamex.StreamEx;

public class Graph {

	private List<Vertex> vertices;
	private int countVertices;
	private int countEdges;

	public Graph() {
		this.vertices = new ArrayList<>();
		this.countVertices = 0;
		this.countEdges = 0;
	}

	public void printVertices() {
		this.vertices.stream().map(Vertex::toString).forEach(System.out::printf);
	}

	public void addVertex(int documentId) {
		this.vertices.add(new Vertex(documentId));
		this.countVertices++;
	}

	public void removeVertex(int documentIdToRemove) {

		try {
			this.vertices.set(new Long(
					StreamEx.of(this.vertices).indexOf(vertex -> documentIdToRemove == vertex.getData()).getAsLong())
							.intValue(),
					null);
			this.vertices.removeIf(Objects::isNull);
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
