import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Mandelbrot2 extends JFrame implements MouseListener {
	
public static ArrayList<ArrayList<Point>> epic = new ArrayList<ArrayList<Point>>();
	
    long start = System.currentTimeMillis();
    boolean b = false;
    static MouseListener l2;
    JLabel label;
    int numZoom = 0;
    int mouseX = 0;
    int mouseY = 0;
    
	public static void main(String[] args) {
		new Mandelbrot2();
	}
	public void calculatePoints() {
		for (int i = 0; i < 16; i++) {
			epic.add(new ArrayList<Point>());
		}
		for (int i = 0; i < 600; i++) {
        	for (int j = 0; j < 600; j++) {
        		Complex c1 = convert((new Point((i), (j))));
        		Complex c2 = new Complex(0, 0);
        		int k1 = 0;
        		for (int k = 0; k < 1000; k++) {
        			c2 = c2.Power(2).Add(c1);
        			if (c2.distance() > 2) {
        				k1 = k;
        				break;
        			}
        		}
        		if (c2.distance() > 2) epic.get(k1 % 16 + (16 * numZoom)).add(new Point(i, j));
        	}
		}
	}
	public Mandelbrot2() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 600);
		this.setLayout(null);
		
		label = new JLabel();
		label.setBounds(0, 0, 600, 600);
		label.setOpaque(true);
		label.addMouseListener(this);
		
		this.add(label);
		this.setVisible(true);
		
	}
	
	public void paint(Graphics g) {
			g.setColor(Color.BLACK);
			g.fillRect(0,0,600,600);
			b = true;
		 calculatePoints();
		for (int i = 16 * numZoom; i < epic.size() - 1; i++) {
			for (int j = 0; j < epic.get(i).size(); j++) {
				int[] picker1 = pickColorMB((i % 16));
    			int[] picker2 = pickColorMB((i + 1) % 16);
    			int red = (int) (picker1[0] + ((picker2[0] - picker1[0]))) % 255;
    			int green = (int) (picker1[1] + ((picker2[1] - picker1[1]))) % 255;
    			int blue = (int) (picker1[2] + ((picker2[2] - picker1[2]))) % 255;
    			g.setColor(new Color(red, green, blue));
    			g.fillRect((int) epic.get(i).get(j).getX(), (int) epic.get(i).get(j).getY(), 1, 1);
			}
		}
		b = true;
//        paint(g);
    }
	static int[][] MBpallet = {
	    { 25,   7,  26},    
	    {  9,   1,  47},    
	    {  4,   4,  73},    
	    {  0,   7, 100},    
	    { 12,  44, 138},    
	    { 24,  82, 177},    
	    { 57, 125, 209},    
	    {134, 181, 229},    
	    {211, 236, 248},    
	    {241, 233, 191},    
	    {248, 201,  95},
	    {255, 170,   0},
	    {204, 128,   0},
	    {153,  87,   0},
	    {106,  52,   3},
	    { 66,  30,  15}
	};

	public int[] pickColorMB(int iter) {
		int i = iter % 16;
		if(i < 16) return MBpallet[i];
		return new int[] {0, 0, 0};
	}
	
	public int seconds() {
		return (((int) (System.currentTimeMillis() - start))/1000) % 16;
	}
	
	public double fraction() {
		return (((double) System.currentTimeMillis() - start)/1000) % 1;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Invoked when the mouse button has been clicked (pressed and released) on a component
		System.out.println("You clicked the mouse");
		final java.awt.Point mousePos = this.getMousePosition();
		if (mousePos != null) {
		  mouseX = mousePos.x;
		  mouseY = mousePos.y;
		}
		repaint();
		numZoom++;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Invoked when a mouse button has been pressed on a component
		System.out.println("You pressed the mouse");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Invoked when a mouse button has been released on a component
		System.out.println("You released the mouse");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Invoked when the mouse enters a component
		System.out.println("You entered the component");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Invoked when the mouse exits a component
		System.out.println("You exited the component");
	}
	//converts a point on the Jframe to a point on the graph, (-3 -> 1), (-1.5i -> 1.5i)
	public Complex convert(Point p) {
		return new Complex((((mouseX + p.getX()) * 0.005 * (Math.pow(2/3., numZoom))) - 2), (((mouseY + p.getY()) * -.005 * (Math.pow(2/3., numZoom))) + 1.5));
	}

}
//new strat
//have a 2d arraylist(17 by a lot)
//store all points as one of the 17 categories
//row index 16 will remain unchanged
//all others will cycle through the 16 colours