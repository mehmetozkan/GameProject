
public   class Players {

	String name="";
	String[] Color_Score=new String[3];	
	String totalscore="";
	String hit="";
	String level="";
	String healt="";
    String Balonposition="";
    
	public Players(String name,String level,String healt,String[] cscore,String hit,String tscore,String balonposi) {
		this.name=name;
		this.level=level;
		this.healt=healt; 
		this.Color_Score= cscore;
		this.hit=hit;
		this.totalscore=tscore;
		this.Balonposition=balonposi;
	}
}
