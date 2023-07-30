import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseListenerExample extends Frame implements MouseListener{ 
    MouseListenerExample(){  
        addMouseListener(this);
        setSize(600,600);  
        setLayout(null);  
        setVisible(true);  
    }  
    @Override
    public void mouseClicked(MouseEvent e) {
    	int x = e.getX();
        int y = e.getY();
        System.out.println(x+","+y);
        if (e.getButton() == MouseEvent.BUTTON1) {
        } else if (e.getButton() == MouseEvent.BUTTON2) {
            // Handle right-click
        }
    } 
    public void mouseEntered(MouseEvent e) {  
    }  
    public void mouseExited(MouseEvent e) {  
    }  
    public void mousePressed(MouseEvent e) {  
    	int x = e.getX();
        int y = e.getY();
        System.out.println(x+","+y);
        if (e.getButton() == MouseEvent.BUTTON1) {
        } else if (e.getButton() == MouseEvent.BUTTON2) {
            // Handle right-click
        }
    }  
    public void mouseReleased(MouseEvent e) {  
    } 
}  