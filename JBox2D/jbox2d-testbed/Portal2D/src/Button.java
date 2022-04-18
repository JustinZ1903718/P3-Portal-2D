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

public class Button {
	private Image img; 	
	private AffineTransform tx;
	private int x;
	private int y;
	private boolean pressed;
	public Button(int x, int y) {
		this.x = x;
		this.y = y;
		img = getImage("unpressedButton.png"); 
		//load the image for Tree
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y); 				//initialize the location of the image
									//use your variables
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public boolean getPressed() {
		return pressed;
	}
	public void setPressed(boolean p) {
		pressed = p;
	}
	public void notPressed() {
		img = getImage("unpressedButton.png");
	}
	public void pressed() {
		img = getImage("pressed.png");
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
		tx.scale(0.8,0.8);
		
	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(0.8,0.8);
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
