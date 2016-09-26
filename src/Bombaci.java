import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;


public class Bombaci {
	int x, dx,dy;
	int y;

	public Bombaci() {
		x=320;
		y =370;
	}
	int m=0;
	public void move (){ x = x+dx; m=m+dy;}

	public void draw(Graphics g) {
		g.setColor(Color.BLUE);

		g.fillOval((640-110)/2, 415, 110, 25);

		if(x<320){
			g.drawLine(x,360+m,300+m,430); //sol bacak azalacak	 
			g.drawLine(x,360+m,340+m,430); //sað bacak azalacak

		}
		else
		{
			g.drawLine(x,360-m,300+m,430); //sol bacak azalacak
			g.drawLine(x,360-m,340+m,430); //sað bacak azalacak
		}
		//g.drawLine(x,370,300,430); 
		//g.drawLine(x,370,340,430);	

		//g.fillRect(x, y, 40, 15);
		//g.fillRect(x + 15, y - 5, 10, 10);

	}

	public void keyPressed (KeyEvent e){
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_LEFT){dx= -2; dy=2; }
		if(key == KeyEvent.VK_RIGHT){dx= 2; dy=-2; }

	}

	public void keyReleased (KeyEvent e){
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_LEFT){dx= 0; dy=0;}
		if(key == KeyEvent.VK_RIGHT){dx= 0; dy=0;}}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 40, 15);
	}
}










