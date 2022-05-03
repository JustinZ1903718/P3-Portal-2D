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
<<<<<<< HEAD
	public Wall(int tox, int ty, int x, int y, boolean portal) {
		topX=tox;
		topY=ty;
		bx=x;
		by=y;
		this.portal=portal;
		prePortal = false;

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
=======
	private boolean horizontal;
	public Wall(int tox, int ty, int x, int y, boolean portal, boolean horizontal) {
		topX=tox;
		topY=ty;
		bx=x;
		by=y;
		this.portal=portal;
		prePortal = false;
		this.horizontal = horizontal;

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
	public boolean getHorizontal() {
		return this.horizontal;
>>>>>>> branch 'master' of https://github.com/AdamLevin7/Portal2D.git
	}
	public boolean getPortal() {
		return portal;
	}
	public boolean getPrePortal() {
		return prePortal;
	}
	public void setPrePortal(boolean prePortal) {
		this.prePortal = prePortal;
	}
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;

		//call update to update the actualy picture location
		Color c=new Color(180, 180, 180);
		g2.setColor(c);
		g2.fillRect(topX, topY, bx-topX, by-topY);




	}
	/* update the picture variable location */




}
