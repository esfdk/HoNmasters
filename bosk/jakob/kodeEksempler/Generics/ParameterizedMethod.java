package bosk.jakob.kodeEksempler.Generics;

public class ParameterizedMethod {

	public static <N extends Number> N max(N first, N second) {
		if (first.doubleValue() > second.doubleValue()) {
			return first;
		} else {
			return second;
		}
	}

}
