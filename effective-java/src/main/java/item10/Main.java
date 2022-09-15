package item10;

import item10.extend.Color;
import item10.extend.ColorPoint;
import item10.extend.Smell;
import item10.extend.SmellPoint;

public class Main {
	public static void main(String[] args) {
		// CaseInsensitiveString cis = new CaseInsensitiveString("Polish");
		// String str = "polish";
		//
		// System.out.println("cis.equals(str) = " + cis.equals(str)); // true
		// System.out.println("str.equals(cis) = " + str.equals(cis)); // false

		// Point p = new Point(1, 2);
		// ColorPoint cp = new ColorPoint(1, 2, Color.RED);
		//
		// System.out.println("p.equals(cp) = " + p.equals(cp));
		// System.out.println("cp.equals(p1) = " + cp.equals(p));
		//
		// ColorPoint p1 = new ColorPoint(1, 2, Color.RED);
		// Point p2 = new Point(1, 2);
		// ColorPoint p3 = new ColorPoint(1, 2, Color.BLUE);
		//
		// System.out.println("p1.equals(p2) = " + p1.equals(p2));
		// System.out.println("p2.equals(p3) = " + p2.equals(p3));
		// System.out.println("p3.equals(p1) = " + p3.equals(p1));

		ColorPoint colorPoint = new ColorPoint(1, 2, Color.RED);
		SmellPoint smellPoint = new SmellPoint(1, 2, Smell.DIRTY);
		System.out.println("smellPoint.equals(colorPoint) = " + smellPoint.equals(colorPoint));
	}
}
