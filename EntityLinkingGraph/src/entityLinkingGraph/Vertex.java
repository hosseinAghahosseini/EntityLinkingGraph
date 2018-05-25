package entityLinkingGraph;

import java.util.LinkedList;

public class Vertex {
	private Integer documentId;
	private LinkedList<Vertex> neighbours;

	public Vertex(int documentId) {
		this.documentId = new Integer(documentId);
		this.neighbours = new LinkedList<>();
	}

	public int getData() {
		return documentId;
	}

	public void setData(int documentId) {
		this.documentId = new Integer(documentId);
	}

	public LinkedList<Vertex> getNeighbours() {
		return neighbours;
	}

	public void removeNeighbour(Vertex neighbour) {
		this.neighbours.remove(neighbour);
	}

	public void addNeighbour(Vertex neighbour) {
		this.neighbours.add(neighbour);
	}

	@Override
	public String toString() {
		return this.documentId.equals(null) ? "NULL" : this.documentId.toString();
	}
}
