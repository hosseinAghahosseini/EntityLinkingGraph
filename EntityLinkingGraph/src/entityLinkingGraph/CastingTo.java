package entityLinkingGraph;

public class CastingTo<T> {

	private Object objectToCast;
	private Class<?> type;

	public CastingTo(Object objectToCast, Class<?> type) {
		this.objectToCast = objectToCast;
		this.type = type;

	}

	// for more class override this method
	@SuppressWarnings("unchecked")
	public T getCastedObject() {
		try {
			if (this.type == Integer.class) {
				return (T) (Class.forName("java.lang.Integer").cast(Integer.parseInt(this.objectToCast.toString())));
			}
			if (this.type == String.class) {
				return (T) (Class.forName("java.lang.String").cast(this.objectToCast.toString()));
			}
			if (this.type == Long.class) {
				return (T) (Class.forName("java.lang.Long").cast(Long.parseLong(this.objectToCast.toString())));
			}
			if (this.type == Float.class) {
				return (T) (Class.forName("java.lang.Float").cast(Float.parseFloat(this.objectToCast.toString())));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
