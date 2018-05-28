package entityLinkingGraph;

import java.util.HashSet;
import java.util.Set;

public class Vertex {
	private Integer documentId;
	private Set<Vertex> neighbors;
	private boolean isVisit;

	public Vertex(int documentId) {
		this.documentId = new Integer(documentId);
		this.neighbors = new HashSet<>();
		this.isVisit = false;
	}

	public int getData() {
		return documentId;
	}

	public void setData(int documentId) {
		this.documentId = new Integer(documentId);
	}

	public Set<Vertex> getNeighbours() {
		return neighbors;
	}

	public void removeNeighbour(Vertex neighbour) {
		this.neighbors.remove(neighbour);
	}

	public void addNeighbour(Vertex neighbour) {
		this.neighbors.add(neighbour);
	}

	public boolean isVisit() {
		return isVisit;
	}

	public void setVisit(boolean isVisit) {
		this.isVisit = isVisit;
	}

	@Override
	public String toString() {
		return this.documentId.equals(null) ? "NULL" : this.documentId.toString();
	}

	@Override
	public int hashCode() {
		return this.documentId;
	}

	@Override
	public boolean equals(Object obj) {
		return this.documentId.intValue() == ((Vertex) obj).documentId.intValue();
	}

}
