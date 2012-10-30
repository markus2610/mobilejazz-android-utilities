package cat.mobilejazz.utilities.format;

public class DefaultObjectPrinter implements ObjectPrinter<Object> {
	
	private static final DefaultObjectPrinter instance = new DefaultObjectPrinter();
	
	public static DefaultObjectPrinter getInstance() {
		return instance;
	}

	@Override
	public String toString(Object object) {
		return String.valueOf(object);
	}

}
