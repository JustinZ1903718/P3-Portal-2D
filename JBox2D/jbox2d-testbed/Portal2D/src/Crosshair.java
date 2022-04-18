package Portal2D.src;


import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Crosshair {
	private Image img; 	
	private AffineTransform tx;
	private int x;
	private int y;
	private boolean blue;
	public Crosshair() {
		blue = true;
		img = getImage("blueCrosshair.png"); 
		//load the image for Tree
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y); 				//initialize the location of the image
									//use your variables
	}
	public void setX(int x) {
		this.x = x-19;
	}
	public void setY(int y) {
		this.y = y-40;
	}
	public void setBlue(boolean blue) {
		this.blue = blue;
	}
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		
		//call update to update the actualy picture location
		update();
		
		
		
		
		g2.drawImage(img, tx, null);
		
		

	}
	private void update() {
		tx.setToTranslation(x, y);
		tx.scale(1,1);
		
	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(1,1);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Player.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
}
