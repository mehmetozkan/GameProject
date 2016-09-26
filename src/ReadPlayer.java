

import java.io.File;

import javax.xml.parsers.*;

import org.w3c.dom.*;
public class ReadPlayer {
	String name="";
	String[] Color_Score=new String[3];	
	String totalscore="";
	String hit="";
	String level="";
	String healt="";
	String balonposition="";
	// int i=0;
	ReadPlayer() {
		//OyunPaneli.getplayers.removeAll(OyunPaneli.getplayers);
		//SaveGame.addplayers.removeAll(SaveGame.addplayers);

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=null;
		try {
			File yourFile = new File("Players.xml");
			if(yourFile.exists()) {
				builder=factory.newDocumentBuilder();
				Document doc=builder.parse("Players.xml");

				Element root=doc.getDocumentElement();

				NodeList childrens = root.getChildNodes();

				for(int j=0;j<childrens.getLength();j++)
				{
					NodeList childman=childrens.item(j).getChildNodes();


					balonposition="";

					for (int temp = 0; temp < childman.getLength(); temp++) {
						Node child=childman.item(temp);

						if(child instanceof Element){

							Element childElement =(Element)child;

							if(!childElement.getTagName().equals("Ballons"))
							{
								Text textNode=(Text)childElement.getFirstChild();

								if(childElement.getTagName().equals("name") )
								{
									this.name=textNode.getData().toString();
									System.out.println("name"+name);
								}
								if(childElement.getTagName()=="Color_Score")
								{
									this.Color_Score[0]=childElement.getAttribute("ScoreOrange");
									this.Color_Score[1]=childElement.getAttribute("ScorePink");
									this.Color_Score[2]=childElement.getAttribute("ScoreYellow");
									this.totalscore=textNode.getData().toString();
									System.out.println("Color_Score: "+Color_Score[0]+Color_Score[1]+Color_Score[2]+" totalscore:"+totalscore);
								}
								if(childElement.getTagName().equals("Hit"))
								{
									this.hit= textNode.getData().toString();
									System.out.println("Hit:"+hit);
								}
								if(childElement.getTagName().equals("Level"))
								{
									this.level=textNode.getData().toString();
									System.out.println("Level:"+level);
								}
								if(childElement.getTagName().equals("Healt"))
								{
									this.healt=textNode.getData().toString();
									System.out.println("Healt:"+healt);
								}
							}
							if(childElement.getTagName().equals("Ballons"))
							{
								NodeList courseListNode=childElement.getChildNodes();

								for(int x=0;x<courseListNode.getLength();x++)
								{
									Node courseChild=courseListNode.item(x);
									if(courseChild instanceof Element)
									{
										Element courseElement=(Element)courseChild;

										Text textN=(Text)courseElement.getFirstChild();
										String course_name=textN.getData();

										String course_code=courseElement.getAttribute("BalonColor");

										if(balonposition=="")
											balonposition+=course_code+":"+course_name;
										else
											balonposition+=","+course_code+":"+course_name;
									}

								}
							}

						}
					}
					System.out.println(balonposition);
					SaveGame.addplayers.add(new Players(this.name,this.level, this.healt,this.Color_Score,this.hit,this.totalscore,balonposition));
					OyunPaneli.getplayers.add(new Players(this.name,this.level, this.healt,this.Color_Score,this.hit,this.totalscore,balonposition));

				}
				//System.out.println(SaveGame.addplayers.size());
			}
		}catch(Exception e)
		{
			System.out.println(e.toString());
		}

	}	
	
	//public static void main(String[] args) {
		//new ReadPlayer();

	//}


}