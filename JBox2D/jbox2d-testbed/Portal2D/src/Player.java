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

public class Player {
	private Image img; 	
	private AffineTransform tx;
	private int x;
	private int y;
	private int vx;
	private int vy;
	private double gravity=1;
	private boolean left;
	private boolean ground;
	private boolean dead;
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		ground=false;
		img = getImage("joey.gif"); 
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
	public void setVX(int vx) {
		this.vx = vx;
	}
	public void setVY(int vy) {
		this.vy = vy;
	}
	public boolean getGround() {
		return ground;
	}
	public void setGround(boolean ground) {
		this.ground=ground;
	}
	public boolean getDead() {
		return dead;
	}
	public void setDead(boolean dead) {
		this.dead=dead;
	}
	public int getVY() {
		return vy;
	}
	public int getVX() {
		return vx;
	}
	public void turnLeft() {
		img = getImage("joeyleft.gif");
		left = true;
	}
	public void turnRight() {
		img = getImage("joey.gif");
		left = false;
	}
	public boolean getIsLeft() {
		return left;
	}

	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		
		//call update to update the actualy picture location
		update();
		
		
		
		
		if(!dead) g2.drawImage(img, tx, null);
		
		

	}
	
	private void update() {
	
		x += vx;
		if(!ground) {
			y+=vy;
			if(vy<20) {
				vy+=gravity;
			}
		}
		
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
