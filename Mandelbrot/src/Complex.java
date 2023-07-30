
public class Complex {
	public double real;
	public double imaginary;
	
	public Complex (double r, double i) {
		real = r;
		imaginary = i;
	}
	
	public Complex Add(Complex i) {
		return new Complex(this.real + i.real, this.imaginary + i.imaginary);
	}
	
	public Complex Multiply(Complex i) {
		return new Complex(((this.real) * i.real) + (this.imaginary * i.imaginary * -1), (this.real * i.imaginary) + (this.imaginary * i.real));
	}
	
	public Complex Power(double j) {
		if (j == 0) return new Complex(1, 0);
		Complex res = this;
		for (int i = 1; i < j; i++) {
			res = res.Multiply(this);
		} return res;
	}
	
	public String toString() {
		return this.real + " " + (this.imaginary < 0? "-" : "+") + " " + Math.abs(this.imaginary) + "i";
	}
	
	public double distance() {
		return Math.pow((this.real*this.real) + (this.imaginary * this.imaginary), 0.5);
	}
}
