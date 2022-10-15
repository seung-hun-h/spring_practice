package item31.nonwildcard;

import java.util.Collection;
import java.util.Objects;

public class MyCollections {
	public static <E extends Comparable<E>> E max(Collection<E> elements) {
		if (elements.isEmpty()) {
			throw new IllegalArgumentException("컬렉션이 비어있습니다");
		}

		E result = null;
		for (E element : elements) {
			if (result == null || element.compareTo(result) > 0) {
				result = Objects.requireNonNull(element);
			}
		}

		return result;
	}
}
