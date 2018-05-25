package entityLinkingGraph;

public class CastingTo<T> {

	private Object objectToCast;
	private String type;

	public CastingTo(Object objectToCast, String type) {
		this.objectToCast = objectToCast;
		this.type = type;

	}

	@SuppressWarnings("unchecked")
	public T getCastedObject() {
		Class<T> castedClass = null;
		try {
			castedClass = (Class<T>) Class.forName(this.type);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return castedClass.cast(this.objectToCast);
	}

}
