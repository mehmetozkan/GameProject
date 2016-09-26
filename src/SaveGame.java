import java.io.File;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public  class  SaveGame {
	static public Vector<Players> addplayers=new Vector<Players>();

	
	public SaveGame(String name,int level,int healt,int spink,int sorange,int syellow,int hit,String totalscore,Vector<String> BalonPosition) throws Exception{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder   builder      = null;
		builder = factory.newDocumentBuilder();
		
		Document doc = builder.newDocument();
		Element root = doc.createElement("PLAYERS");
		doc.appendChild(root);
		
		//burada son oyunun kaydýný tutuyor ama biz ilk baþta kaydediyoruz
		
		root.appendChild(setSave(doc, name, level+"", healt+"", spink+"", sorange+"", syellow+"",hit+"",totalscore,BalonPosition));

		File yourFile = new File("Players.xml");
		if(yourFile.exists()) {
		new ReadPlayer();
		//buradada read olarak okuduðumuz xml yi önceki deðerleri kaybetmemek için alýp tekrar devamýna ekliyoruz.
		for(Players pp:addplayers){
			root.appendChild(setSave(doc, pp.name,pp.level,pp.healt,pp.Color_Score[0],pp.Color_Score[1],pp.Color_Score[2],pp.hit,pp.totalscore,BalonPosition));
		}
		addplayers.removeAll(addplayers);
		}
        
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer trans = tf.newTransformer();

		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("Players.xml"));
		trans.transform(source, result);
	}
	public Element setSave(Document doc,String name,String level,String healt,String spink,String sorange,String syellow,String hit,String tscore,Vector<String> BalnPostion) throws Exception
	{
		Element player = doc.createElement("Player");
		// chapter.setAttribute(name, value)
		Element title   = doc.createElement("name") ;
		Text   txt = doc.createTextNode(name);
		title.appendChild(txt);
		player.appendChild(title);

		Element p= doc.createElement("Color_Score");
		p.setAttribute("ScorePink", spink+"");
		p.setAttribute("ScoreOrange",sorange+"");
		p.setAttribute("ScoreYellow", syellow+"");
		Text   totalscore = doc.createTextNode(tscore);
		p.appendChild(totalscore);
		player.appendChild( p );

		Element hits= doc.createElement("Hit");
		Text   texthit = doc.createTextNode(hit);
		hits.appendChild(texthit);
		player.appendChild( hits );

		Element levels= doc.createElement("Level");
		Text   textlevel = doc.createTextNode(level+"");
		levels.appendChild(textlevel);
		player.appendChild( levels );

		Element Healts= doc.createElement("Healt");
		Text   txtheals = doc.createTextNode(healt+"");
		Healts.appendChild(txtheals);
		player.appendChild(Healts);
		
        Element ballons = doc.createElement("Ballons");
		for(int i=0;i<BalnPostion.size();i++)
		{
		  Element ballon=doc.createElement("Ballon");
		  ballon.setAttribute("BalonColor",(BalnPostion.get(i)).split(":")[2]);
		   String posion=(BalnPostion.get(i)).split(":")[0]+":"+(BalnPostion.get(i)).split(":")[1];
			ballon.appendChild(doc.createTextNode(posion));
		
			ballons.appendChild(ballon);
		}
		player.appendChild(ballons);

		/*
		for(int i=0;i<BalnPostion.size();i++)
		{
		    Element ballon=doc.createElement("Ballon"+i);
		    ballon.setAttribute("BalonColor",(BalnPostion.get(i)).split(":")[2]);
		    String posion=(BalnPostion.get(i)).split(":")[0]+":"+(BalnPostion.get(i)).split(":")[1];
			ballon.appendChild(doc.createTextNode(posion));
		
			player.appendChild(ballon);
		}*/
        
        
        
		return player; 
	}

}
