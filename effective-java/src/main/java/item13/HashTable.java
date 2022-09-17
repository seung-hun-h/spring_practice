package item13;

public class HashTable implements Cloneable {
	private static final int SIZE = 20;
	private Entry[] buckets = new Entry[SIZE];

	private static class Entry {
		final Object key;
		Object value;
		Entry next;

		public Entry(Object key, Object value, Entry next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}

		Entry deepCopy() {
			Entry result = new Entry(key, value, next);
			for (Entry p = result; p.next != null; p = p.next) {
				p.next = new Entry(p.next.key, p.next.value, p.next.next);
			}
			return result;
		}
	}

	@Override
	public HashTable clone() {
		try {
			HashTable result = (HashTable)super.clone();
			result.buckets = new Entry[SIZE];

			for (int i = 0; i < SIZE; i++) {
				if (buckets[i] != null) {
					result.buckets[i] = buckets[i].deepCopy();
				}
			}

			return result;
		} catch (CloneNotSupportedException exception) {
			throw new AssertionError();
		}
	}
}
