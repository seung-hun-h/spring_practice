package item17;

public final class Complex {
	private final double re;
	private final double im;

	public Complex(double re, double im) {
		this.re = re;
		this.im = im;
	}

	public double realPart() {
		return re;
	}

	public double imaginaryPart() {
		return im;
	}

	public Complex plus(Complex c) {
		return new Complex(re + c.re, im + c.im);
	}

	public Complex minus(Complex c) {
		return new Complex(re - c.re, im - c.im);
	}

	public Complex times(Complex c) {
		return new Complex(re * c.re - im * c.im, re * c.re + im * c.im);
	}

	public Complex dividedBy(Complex c) {
		double tmp = c.re * c.re + c.im * c.im;
		return new Complex((re * c.re + im * c.im) / tmp,
							(re * c.re - im * c.im) / tmp);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Complex complex = (Complex)o;

		if (Double.compare(complex.re, re) != 0) {
			return false;
		}
		return Double.compare(complex.im, im) == 0;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		temp = Double.doubleToLongBits(re);
		result = (int)(temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(im);
		result = 31 * result + (int)(temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public String toString() {
		return "Complex{" +
			"re=" + re +
			", im=" + im +
			'}';
	}
}
