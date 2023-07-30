import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Mandelbrot extends Canvas {

public static ArrayList<ArrayList<Point>> epic = new ArrayList<ArrayList<Point>>();

    long start = System.currentTimeMillis();

    boolean b = false;

	public static void main(String[] args) {

		JFrame f = new JFrame("Mandelbrot");

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		Mandelbrot m = new Mandelbrot();
        f.add(m);  
        f.setSize(600, 600);
        f.setVisible(true);

	}

	public Mandelbrot() {
		for (int i = 0; i < 16; i++) {
			epic.add(new ArrayList<Point>());
		}
		for (int i = 0; i < 600; i++) {
        	for (int j = 0; j < 600; j++) {
        		Complex c1 = (new Point(i, j)).convert();
        		Complex c2 = new Complex(0, 0);
        		int k1 = 0;
        		for (int k = 0; k < 100000; k++) {
        			c2 = c2.Power(2).Add(c1);
        			if (c2.distance() > 2) {
        				k1 = k;
        				break;
        			}
        		}
        		if (c2.distance() > 2) epic.get(k1 % 16).add(new Point(i, j));
        	}
		}
	}

	public void paint(Graphics g) {
		if (!b) {
			g.setColor(Color.BLACK);
			g.fillRect(0,0,800,600);
		}
//        for (int i = 0; i < 800; i++) {
//        	for (int j = 0; j < 600; j++) {
//        		Complex c1 = (new Point(i, j)).convert();
//        		Complex c2 = new Complex(0, 0);
//        		int k1 = 0;
//        		for (int k = 0; k < 1000; k++) {
//        			c2 = c2.Power(2).Add(c1);
//        			if (c2.distance() > 2) {
//        				k1 = k;
//        				break;
//        			}
//        		}
//        		if (!(c2.distance() > 2)) g.fillRect(i, j, 1, 1);
//        		else {
//        			int[] picker1 = pickColorMB((k1 + seconds()) % 16);
//        			int[] picker2 = pickColorMB((k1 + seconds() + 1) % 16);
//        			double t = fraction();
//        			int red = (int) (picker1[0] + (t * (picker2[0] - picker1[0]))) % 255;
//        			int green = (int) (picker1[1] + (t * (picker2[1] - picker1[1]))) % 255;
//        			int blue = (int) (picker1[2] + (t * (picker2[2] - picker1[2]))) % 255;
//        			g.setColor(new Color(red, green, blue));
//        			g.fillRect(i, j, 1, 1);
//        			g.setColor(Color.BLACK);
//        		}
//        	}
//        }
		for (int i = 0; i < epic.size() - 1; i++) {
			for (int j = 0; j < epic.get(i).size(); j++) {
				int[] picker1 = pickColorMB((i + seconds()) % 16);
    			int[] picker2 = pickColorMB((i + seconds() + 1) % 16);
    			double t = fraction();
    			int red = (int) (picker1[0] + (t * (picker2[0] - picker1[0]))) % 255;
    			int green = (int) (picker1[1] + (t * (picker2[1] - picker1[1]))) % 255;
    			int blue = (int) (picker1[2] + (t * (picker2[2] - picker1[2]))) % 255;
    			g.setColor(new Color(red, green, blue));
    			g.fillRect((int) epic.get(i).get(j).getX(), (int) epic.get(i).get(j).getY(), 1, 1);
			}
		}
		b = true;
        paint(g);
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
}
//new strat
//have a 2d arraylist(17 by a lot)
//store all points as one of the 17 categories
//row index 16 will remain unchanged
//all others will cycle through the 16 colours