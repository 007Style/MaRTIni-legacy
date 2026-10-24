import java.awt.*;

public class Daneyand extends Frame {

	public static void main(String[] args) {
		new Daneyand();
	}
	
	Image image;

	public Daneyand() {
		image = getToolkit().getImage("martinibk.jpg");
		setSize(384, 384);
		show();
	}
	
	public void paint(Graphics g) {
		g.drawImage(image, 64, 64, this);
	}
}
