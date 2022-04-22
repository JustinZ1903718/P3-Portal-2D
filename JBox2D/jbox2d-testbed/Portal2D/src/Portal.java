package Portal2D.src;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Portal{
	
	//add location attributes
	private Image img; 	
	private AffineTransform tx;
	private int x;
	private int y;
	private boolean horizontal;
	
	public Portal(int x, int y, boolean orange) {
		this.x = x;
		this.y = y;
		horizontal = false;
		if(orange)
			img = getImage("OrangePortal.gif"); //load the image for Tree
		else 
			img = getImage("BluePortal.gif");
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y); 				//initialize the location of the image
									//use your variables
	}
	
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y=y;
	}
	public boolean getHorizontal() {
		return horizontal;
	}
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}
	private void update() {
		tx.setToTranslation(x, y);
		tx.scale(1,1);
		
	}
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		//call update to update the actualy picture location
		update();
		g2.drawImage(img, tx, null);
		
		

	}
	/* update the picture variable location */
	
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(1, 1);
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
