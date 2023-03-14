
public class Point {
	
	public int x, y;
	
	public Point(int x1, int y1) {
		x = x1;
		y = y1;
	}
	
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	//converts a point on the Jframe to a point on the graph, (-3 -> 1), (-1.5i -> 1.5i)
	public Complex convert() {
		return new Complex(((this.x * 0.005) - 2), ((this.y * -.005) + 1.5));
	}
}
