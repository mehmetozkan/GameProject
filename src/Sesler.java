import java.applet.Applet;
import java.applet.AudioClip;
import java.util.Random;

public class Sesler {

 
	private AudioClip shoot;
  	private AudioClip splat;
	private AudioClip pop;
 
	public Sesler() {

		try {
			shoot = Applet.newAudioClip(getClass().getResource("laser.wav"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	 
		try {
			splat = Applet.newAudioClip(getClass().getResource("splat.wav"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			pop = Applet.newAudioClip(getClass().getResource("pop.wav"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	public void playPop() {
		try {
			new Thread() {
				public void run() {

					int rand = new Random().nextInt(2);
					if (rand == 0) {
						pop.play();
					}
					if (rand == 1) {
						splat.play();
					}
				}
			}.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void playShoot() {
		try {
			new Thread() {
				public void run() {

					shoot.play();
				}
			}.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 

}
