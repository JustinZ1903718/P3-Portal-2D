package Portal2D.src;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Enemy {

	//add location attributes
	private Image img; 	
	private AffineTransform tx;
	private int x;
	private int y;
	private double angle;
	private double inc=0.03;
	private int length;
	private boolean dead;
	private int cooldown;

	public Enemy(int x, int y, int length) {
		this.x = x;
		this.y = y;
		angle=Math.PI/2;
		dead=false;
		this.length=length;
		img = getImage("BigManEnemy.gif"); //load the image for Tree
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
	public void setCooldown(int c) {
		cooldown=c;
	}
	public int getCooldown() {
		return this.cooldown;
	}
	public double getSlope() {
		return Math.tan(angle);
	}
	public boolean getDead() {
		return dead;
	}
	public void setDead(boolean d) {
		dead=d;
		if(dead)
			img = getImage("BigManEnemyDead.gif");
		else {
			img = getImage("BigManEnemy.gif");
		}
	}
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;

		//call update to update the actualy picture location

		g2.drawImage(img, tx, null);
		/*
		if(!dead) {
			g2.setColor(Color.red);
			g2.setStroke(new BasicStroke(4));
			g2.drawLine(x+20, y+20, (int) (x+20-length*Math.cos(angle)), (int) (y+20-length*Math.sin(angle)));
			angle+=inc;
			if(angle<0||angle>Math.PI) {
				inc=-1*inc;
			}
		}
		*/
	}
	/* update the picture variable location */


	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(0.8, 0.8);
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
