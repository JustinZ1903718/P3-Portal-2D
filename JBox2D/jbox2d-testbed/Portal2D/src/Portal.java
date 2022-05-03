package Portal2D.src;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Portal{
	
	//add location attributes
	private Image img; 	
	private AffineTransform tx;
	private int x;
	private int y;
	private boolean horizontal;
<<<<<<< HEAD
	private boolean orange;
=======
>>>>>>> branch 'master' of https://github.com/AdamLevin7/Portal2D.git
	
	public Portal(int x, int y, boolean orange, boolean horizontal) {
		this.x = x;
		this.y = y;
<<<<<<< HEAD
		this.orange = orange;
		this.horizontal = horizontal;
=======
		horizontal = false;
		if(orange)
			img = getImage("OrangePortal.gif"); //load the image for Tree
		else 
			img = getImage("BluePortal.gif");
>>>>>>> branch 'master' of https://github.com/AdamLevin7/Portal2D.git
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
<<<<<<< HEAD
		
		this.horizontal = horizontal;
=======
		this.horizontal = horizontal;
	}
	private void update() {
		tx.setToTranslation(x, y);
		tx.scale(1,1);
>>>>>>> branch 'master' of https://github.com/AdamLevin7/Portal2D.git
		
	}
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		//call update to update the actualy picture location
		update();
		g2.drawImage(img, tx, null);
		
		

	}
	
	private void update() {
		System.out.println(horizontal);
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
