
package EntityLinkingGraph;


import java.util.HashSet;
import java.util.Set;

public class Vertex {
	private String documentId;
	private Set<Vertex> neighbors;
	private boolean isVisit;

	public Vertex(String documentId) {
		this.documentId = new String(documentId);
		this.neighbors = new HashSet<>();
		this.isVisit = false;
	}

	public String getData() {
		return documentId;
	}

	public void setData(String documentId) {
		this.documentId = new String(documentId);
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
		return this.documentId.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return this.documentId.equals(((Vertex) obj).documentId);
	}

}
