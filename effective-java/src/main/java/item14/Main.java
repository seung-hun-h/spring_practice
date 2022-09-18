package item14;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) {
		BigDecimal bigDecimal1 = new BigDecimal("1.0");
		BigDecimal bigDecimal2 = new BigDecimal("1.00");

		Set<BigDecimal> hashSet = new HashSet<>() {
			{
				add(bigDecimal1);
				add(bigDecimal2);
			}
		};

		Set<BigDecimal> treeSet = new TreeSet<>() {
			{
				add(bigDecimal1);
				add(bigDecimal2);
			}
		};

		System.out.println("hashSet = " + hashSet);
		System.out.println("treeSet = " + treeSet);
	}
}
