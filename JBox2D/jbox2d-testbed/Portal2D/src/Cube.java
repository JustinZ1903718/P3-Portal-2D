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

public class Cube {
	//The cube presses buttons, kills enemies, and blocks lasers
	private Image img; 	
	private AffineTransform tx;
	private int x;
	private int y;
	private int vx;
	private int vy;
	//is affected by gravity
	private int gravity=1;
	//starting location at the start of a level
	private int startX;
	private int startY;
	public Cube(int x, int y) {
		this.x=x;
		this.y=y;
		this.startX = x;
		this.startY = y;
		img = getImage("Cube.gif"); 
		//load the image for Tree
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y); 				//initialize the location of the image
									//use your variables
	}
	//getters and setters
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
	public void setVx(int v) {
		vx=v;
	}
	public int getVx() {
		return vx;
	}
 	public int getVy() {
		return vy;
	}
 	
	public void setVy(int vy) {
		this.vy = vy;
	}
	public void addX() {
		x+=vx;
	}
	public int getStartX() {
		return startX;
	}
	public int getStartY() {
		return startY;
	}
	public void setStartX(int startX) {
		this.startX = startX;
	}
	public void setStartY(int startY) {
		this.startY = startY;
	}
	public void reset() {
		x = startX;
		y = startY;
		vy = 0;
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
		tx.scale(0.4, 0.4);
		y+=vy;
		vy+=gravity;
	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(0.4,0.4);
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
