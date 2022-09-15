package item10;

import java.util.Objects;

public final class CaseInsensitiveString {
	private final String s;

	public CaseInsensitiveString(String s) {
		this.s = Objects.requireNonNull(s);
	}

	// 대칭성 위배
	// @Override
	// public boolean equals(Object obj) {
	// 	if (obj instanceof CaseInsensitiveString) {
	// 		return s.equalsIgnoreCase(((CaseInsensitiveString)obj).s);
	// 	}
	//
	// 	if (obj instanceof String) {
	// 		return s.equalsIgnoreCase((String)obj);
	// 	}
	//
	// 	return false;
	// }

	@Override
	public boolean equals(Object obj) {
		return obj instanceof CaseInsensitiveString
			&& ((CaseInsensitiveString)obj).s.equals(s);
	}
}
