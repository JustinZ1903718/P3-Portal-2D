package Portal2D.src;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Portal{
	//portal class: portal can be orange or blue, horizontal or vertical
	//add location attributes
	private Image img; 	
	private AffineTransform tx;
	private int x;
	private int y;
	private boolean horizontal;
	private boolean orange;
	
	public Portal(int x, int y, boolean orange, boolean horizontal) {
		this.x = x;
		this.y = y;
		this.orange = orange;
		this.horizontal = horizontal;
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y); 				//initialize the location of the image
									//use your variables
	}
	//getters and setters
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
		if(horizontal && orange) {
			img = getImage("OrangePortalHorizontal.png");
		}
		else if(horizontal && !orange) {
			img = getImage("BluePortalHorizontal.png");
		}
		else if(!horizontal && orange) {
			img = getImage("OrangePortal.gif");
		}
		else if(!horizontal && !orange) {
			img = getImage("BluePortal.gif");
		}
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
