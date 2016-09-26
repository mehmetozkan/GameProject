import javax.swing.JFrame;


public class Main extends JFrame {
	/**
	 * 20090109 Mehmet ÖZKAN 
	 */
	private static final long serialVersionUID = 1L;
	
	OyunPaneli p=new OyunPaneli(this);
	static Main m; 
	public Main() {
		this.setSize(640, 480);
		this.setTitle("CIU STAR WARS");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		this.add(p);
	 
	}

	public static void main(String[] args) {
	m=new Main();
	}

}
