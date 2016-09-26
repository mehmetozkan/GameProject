import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;


public class Bomba{

	int X,Y;
	Random rnd=new Random();
	Random rnd1=new Random();
	Color renkler[]={Color.pink,Color.yellow,Color.orange};
	Color setreng;
	Bomba() 
	{
		setreng=renkler[rnd.nextInt(3)];
		X=(rnd1.nextInt(610)/2)+(rnd.nextInt(610)/2); //(rnd.nextInt(500)/2)+(rnd1.nextInt(500)/2);
		Y=-(5+rnd1.nextInt(45));
	
	}
 	Bomba(int xx,int yy,int c) 
	{
			//-20561 -256 -14336
			this.X=xx;
			this.Y=yy;
			
			if(c==-20561)
			setreng=Color.pink;
			else if(c==-256)
			setreng=Color.yellow;
			else if(c==-14336)
			setreng=Color.orange;	
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
