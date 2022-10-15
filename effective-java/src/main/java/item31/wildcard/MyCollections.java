package item31.wildcard;

import java.util.Collection;
import java.util.Objects;

public class MyCollections {
	public static <E extends Comparable<? super E>> E max(Collection<? extends E> elements) {
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
