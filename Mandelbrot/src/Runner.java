import java.awt.Graphics;

public class Runner {

	public static void main(String[] args) {
		Complex c1 = new Complex(1, 4);
		Complex c2 = new Complex(-5.4, 3);
		System.out.println("Shoudd return -4.4 + 7.0i, returns " + c1.Add(c2));
		Complex c3 = new Complex(1, 2);
		Complex c4 = new Complex(-3, -1);
		System.out.println("Should return -1.0 - 7.0i, returns " + c3.Multiply(c4));
		Complex c5 = c3.Multiply(c4);
		System.out.println("Should return -48.0 + 14.0i, returns " + c5.Power(2));
		Point p1 = new Point(0, 300);
		System.out.println(p1.convert());
		Point p2 = new Point(450, 200);
		System.out.println(p2.convert());
		System.out.println(c1.distance());
	}

}
