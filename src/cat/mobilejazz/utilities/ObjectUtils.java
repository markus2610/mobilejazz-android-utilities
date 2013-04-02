package cat.mobilejazz.utilities;

public class ObjectUtils {

	public static boolean equals(Object a, Object b) {
		if (a == null) {
			return b == null;
		} else {
			return a.equals(b);
		}
	}

	public static int hashCode(Object a) {
		if (a == null) {
			return 0;
		} else {
			return a.hashCode();
		}
	}

}
