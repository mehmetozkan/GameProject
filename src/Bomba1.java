import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;


public class Bomba1{

	    int X,Y;
 

        Color setreng;
	    Bomba1(int xx,int yy,Color c) 
		{

	    	this.X=xx;
	    	this.Y=yy;
	    	setreng=c;
	
		}
	    public void draw(Graphics g) {
	
	        g.setColor(setreng);
			g.fillOval(X, Y, 25, 25);
		}
		public void move()
		{
		      Random r=new Random();
			  Y=Y+3-r.nextInt(4); //hareket kýsmý hýzý	
		}
	 
		public Rectangle getBounds() {
			return new Rectangle(X, Y, 25, 25);
		}

}
