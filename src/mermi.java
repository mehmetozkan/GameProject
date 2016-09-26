import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class mermi {

	
	int x = 0;
	int y = 370;
	int vely = 10;
   int gelenaci=0;
   
	public mermi(int x,int aci) {
		this.x = x;
		this.gelenaci=aci;
	}
	public void move() {
		y -= vely;

		x=x-gelenaci;
		
		//if(x<320)
		//x=x-gelenaci;
		//else if(x>320)
	    //x=x-gelenaci;
		//else
		//x=320;
		
		
	}

	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillOval(x, y, 5, 5);
	}
	public Rectangle getBounds() {
		return new Rectangle(x, y, 5, 5);
	}
 
}
