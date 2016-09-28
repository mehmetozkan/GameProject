import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;



public class OyunPaneli extends JPanel implements Runnable, KeyListener{
	/**
	 * 20090109 Mehmet ÖZKAN 
	 */
	private static final long serialVersionUID = 1L;
	JFrame frmcl=new JFrame();
	Sesler ses=new Sesler();
	Bombaci p=new Bombaci();
	//int score=0;
	static int[] scoreColor=new int[3];
	int hit=0;
	int hitscore=0;
	int level=1;
	int healts=100;
	int totalscore=0;
	int SpeedTreed=20;

	boolean stopthread=true;
	boolean savegame=false;
	boolean gameover=false;
	Vector<mermi> mermiler = new Vector<mermi>(); 
	Vector<Bomba> bombs = new Vector<Bomba>();
	Vector<String>balonSave=new Vector<String>();

	static public Vector<Players> getplayers=new Vector<Players>();

	Thread t = new Thread(this);
	public OyunPaneli(JFrame frm) {
		this.frmcl=frm;
		DusmanEkle();
		this.setFocusable(true);
		this.addKeyListener(this);
		t.start();
		new ReadPlayer();//
	}
	int enCount,qCount=0;
	public void getDusman(int size)
	{
		if (enCount >= size) {
			bombs.add(new Bomba());
		}
		if (enCount == size) {
			enCount = 0;
		}
		enCount++;
		for(Bomba b:bombs)
		{
			b.move();
		}
	}
	int ballonsize=5;
	public void DusmanEkle()
	{	
		if (hit < ballonsize) {//topa vurma sayýsý ballonsizeden küçükse bir tane balon ekle 
			getDusman(50); //50 count dolana kadar balon getirir 1 snyden 10 count daha az bekletir.
		}
		if (hit==ballonsize) //topa vurma sayýsý balonsize a eþitse
		{
			hit=0; //balonsize ý kadar balon getir
			ballonsize+=5; //ballon size 
			level++;
			SpeedTreed++;
		}
	}
	public void SaveGame()
	{
		savegame=true;
		stopthread=false;
		String name= JOptionPane.showInputDialog(null,"Oyuncu Adý Giriniz","Plase Enter Name",JOptionPane.INFORMATION_MESSAGE);
		if(name!=null && !(name.equals(" ")) && !(name.equals(""))){
			try {

				for(int c=0;c<bombs.size();c++)
				{
			     if((bombs.get(c).Y)>25 && (bombs.get(c).Y)<410)
			     {
			    	 balonSave.add(bombs.get(c).X+":"+bombs.get(c).Y+":"+bombs.get(c).setreng.getRGB());
			     }
				}

				new SaveGame(name,level, healts, scoreColor[0], scoreColor[1], scoreColor[2],hitscore,totalscore+"",balonSave);

				int tgame=JOptionPane.showConfirmDialog(null,"Oyuna Devam etmek istiyormusunuz",
						"game",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(tgame==0){
					mermiler.removeAll(mermiler);  
					savegame=false;
					stopthread=true;
					repaint();
				}
				else if(tgame==1){  
					frmcl.dispose();
					repaint();
				}
			} catch (Exception eE) {
				System.out.println(eE.toString());
			}finally{
				repaint();
			}	
		}else
		{
			savegame=false;
			stopthread=true;
		}
	}
	public void paint(Graphics g)
	{
		super.paint(g);
		g.setColor(Color.black); 
		Font font3 = new Font("Arial",1, 12);
		g.setFont(font3);
		g.drawString("Score: "+totalscore+" Level: "+level,500,15);
		g.drawString("Healt : "+healts,5,15);

		g.setColor(Color.LIGHT_GRAY); 
		g.drawString("Mehmet ÖZKAN",2,42);
		g.drawString("GameProject",2,56);

		if(gameover)
		{
			g.setColor(Color.RED); 
			Font GFONT = new Font("Arial",2,50);
			g.setFont(GFONT);
			g.drawString("Game OVER",190,240);
		}
		if(savegame)
		{
			g.setColor(Color.RED); 
			Font GFONT = new Font("Arial",2,50);
			g.setFont(GFONT);
			g.drawString("Game SAVED",190,240);
		}
		g.setColor(Color.blue); 
		g.fillRect(0, 25, 640,4);

		for (Bomba ms : bombs) {
			ms.draw(g);
		}
		g.setColor(Color.green); 
		g.fillRect(0, 410, 640,4);

		g.setColor(Color.WHITE);


		p.draw(g);
		for (mermi m : mermiler) {
			m.draw(g);
		}
	}
	boolean cntrls=true;

	@Override
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();
		p.keyPressed(e);
		if (key == KeyEvent.VK_SPACE) {
			ses.playShoot();
			mermiler.add(new mermi(p.x,p.m));
		}else{ 

			if(e.isControlDown() && key == KeyEvent.VK_S){	
				SaveGame();
			}
			if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_O){
				loadgame();
			}
		}
	}
	String Choose="";
	public void loadgame()
	{
		stopthread=false;

		if(getplayers.size()>-1){
			String[] names=new String[getplayers.size()];

			int a=0;
			for(Players value: getplayers){
				names[a]=value.name+" "+value.totalscore;
				a++;
			}

			Choose=JOptionPane.showInputDialog(null,
					"lütfen seciniz",
					"choose",
					JOptionPane.INFORMATION_MESSAGE,
					null,
					names,
					names[0])+"";

			System.out.println(Choose);
			if(Choose!=null && !Choose.equals(" ") && !Choose.equals("")){
				for(Players value: getplayers){
					
					//if(value.name.contains(Choose.split(" ")[0]))
					if(value.name.equals(Choose.split(" ")[0]))
					{
						this.healts=Integer.parseInt(value.healt);
						this.level=Integer.parseInt(value.level);
						scoreColor[0]=Integer.parseInt(value.Color_Score[0]);
						scoreColor[1]=Integer.parseInt(value.Color_Score[1]);
						scoreColor[2]=Integer.parseInt(value.Color_Score[2]);
						this.totalscore=Integer.parseInt(value.totalscore);
						//this.hit=Integer.parseInt(value.hit);
						//-14336:135:289,-256:394:263,-256:248:227,-14336:217:102,-20561:369:44
						
						int xcornidat=0;
						int ycornidat=0;
						int colorrenk=0;
						for(String deger1:value.Balonposition.split(","))
						{
							for(int i=0;i<deger1.split(":").length;i++)
							{
							xcornidat=Integer.parseInt(deger1.split(":")[1]);
							ycornidat=Integer.parseInt(deger1.split(":")[2]);	
							colorrenk=Integer.parseInt(deger1.split(":")[0]);
						   
							}
					    bombs.add(new Bomba(xcornidat,ycornidat,colorrenk));
						}
						
						break;
					}
				}	
			}
		}
        stopthread=true;

	}
	@Override
	public void keyReleased(KeyEvent e) {
		p.keyReleased(e);
		int key = e.getKeyCode();
		if(e.isControlDown() && key == KeyEvent.VK_S){	
		}
		if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_O){
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}

	public void Collision()
	{
		for(int i=0;i<bombs.size();i++)
		{
			Bomba dusman=(Bomba)bombs.get(i);
			Rectangle r1=dusman.getBounds();

			for(int x=0;x<mermiler.size();x++)
			{
				mermi yakar=(mermi)mermiler.get(x);
				Rectangle r2=yakar.getBounds();
				if(r1.intersects(r2))
				{
					if(dusman.setreng==Color.pink)
						scoreColor[0]+=1;
					else if (dusman.setreng==Color.orange)
						scoreColor[1]+=3;
					else if(dusman.setreng==Color.yellow) 
						scoreColor[2]+=2;

					ses.playPop();
					bombs.remove(i);
					mermiler.remove(x);
					hit=hit+5;
					hitscore++;
					this.totalscore=(scoreColor[0]+scoreColor[1]+scoreColor[2]);

				}
			}	
		}

		for (int h = 0; h < mermiler.size(); h++) {
			if (mermiler.get(h).y < 20) 
				mermiler.remove(h);
		}

		Rectangle cizgi=new Rectangle(0, 410, 640,4);
		for(int c=0;c<bombs.size();c++)
		{
			Bomba dusman=(Bomba)bombs.get(c);
			Rectangle ciz=dusman.getBounds();

			if(ciz.intersects(cizgi))
			{
				bombs.remove(c);
				healts-=25;

				if(healts==0){
					
					gameover=true;
					repaint();
					stopthread=false;
					bombs.removeAll(bombs);
					try {
						Thread.sleep(1300);
						
					} catch (InterruptedException e) {
						System.out.println(e.toString());
					}finally{
					 gameover=false;
					 hit=0;
					 hitscore=0;
					 level=1;
					 healts=100;
					 totalscore=0;
					 SpeedTreed=20;
					 scoreColor[0]=0;
					 scoreColor[1]=0;
					 scoreColor[2]=0;
					 stopthread=true;
					}
				}
			}
			//if (dusman.Y > 410) 
			//bombs.remove(c);
		}
	}

	@Override
	public void run() {
		while (true) {
			if(stopthread){
				

				p.move();
				for (mermi m : mermiler) { //change foreach 
					m.move();
				}
				Collision();
				repaint(); DusmanEkle();
				if (healts <= 0) {
					mermiler.removeAll(mermiler);  
					bombs.removeAll(bombs);
				}

				try {
					Thread.sleep(1000 / SpeedTreed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
