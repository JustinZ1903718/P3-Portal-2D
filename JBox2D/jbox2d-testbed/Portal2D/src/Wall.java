package Portal2D.src;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Wall{

	//add location attributes
	private int topX;
	private int topY;
	private int bx;
	private int by;
	private boolean prePortal;
	private boolean portal;
	private int num;
	private boolean exist;
	public Wall(int tox, int ty, int x, int y, boolean portal, int num) {
		topX=tox;
		topY=ty;
		bx=x;
		by=y;
		this.portal=portal;
		prePortal = false;
		this.num = num;
		exist=true;
	}

	public int getTopX() {
		return this.topX;
	}
	public int getTopY() {
		return this.topY;
	}
	public int getBx() {
		return this.bx;
	}
	public int getBy() {
		return this.by;
	}
	public boolean getPortal() {
		return portal;
	}
	public boolean getExist() {
		return exist;
	}
	public void setExist(boolean e) {
		exist=e;
	}
	public boolean getPrePortal() {
		return prePortal;
	}
	public void setPrePortal(boolean prePortal) {
		this.prePortal = prePortal;
	}
	public int getNum() {
		return num;
	}
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;

		//call update to update the actualy picture location
		
		Color c=new Color(120, 120, 120);
		if(portal) {
			c=new Color(180, 180, 180);
		}
		g2.setColor(c);
		if(exist) {
			g2.fillRect(topX, topY, bx-topX, by-topY);
		}




	}
	/* update the picture variable location */




}
