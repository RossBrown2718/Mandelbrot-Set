
public class Point {
	
	public double x, y;
	
	public Point(int x1, int y1) {
		x = x1;
		y = y1;
	}
	
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public Complex convert() {
		return new Complex(((this.getX() * 0.005) - 2), ((this.getY() * -.005) + 1.5));
	}
}
