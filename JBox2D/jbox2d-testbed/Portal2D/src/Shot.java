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

public class Shot{
	
	//add location attributes
	
	private int x;
	private int y;
	private double curx;
	private double cury;
	private int targetx;
	private int targety;
	private int range;
	private double slope;
	private int length=30;
	private boolean orange;
	
	
	public Shot(int x, int y, int targetx, int targety, int range, boolean orange) {
		this.x = x;
		curx=x;
		this.y = y;
		cury=y;
		this.orange = orange;
		this.targetx = targetx;
		this.targety = targety;
		this.range=range;
		if(targetx!=curx) {
			slope=((double) (targety-cury))/(targetx-curx);
		}
		
						//initialize the location of the image
									//use your variables
	}
	
	public double getX() {
		return this.curx;
	}
	public double getY() {
		return this.cury;
	}
	public boolean getOrange() {
		return orange;
	}
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		if(dist(x, y, (int) curx, (int) cury)<range) {
			if(orange) g2.setColor(Color.orange);
			else g2.setColor(Color.blue);
			g2.setStroke(new BasicStroke(4));
			if(targetx>x) {
				g2.drawLine((int) curx, (int) cury, (int) (curx+length/(Math.sqrt(1+slope*slope))), (int) (cury+slope*length/(Math.sqrt(1+slope*slope))));
				curx+=9*((double) 1/Math.sqrt(1+slope*slope));
				cury+=9*((double) slope/Math.sqrt(1+slope*slope));
		
			}
			else if(targetx!=x) {
				g2.drawLine((int) curx, (int) cury, (int) (curx-length/(Math.sqrt(1+slope*slope))), (int) (cury-slope*length/(Math.sqrt(1+slope*slope))));
				curx-=9*(((double) 1)/Math.sqrt(1+slope*slope));
				cury-=9*((double) slope/Math.sqrt(1+slope*slope));
		
			}
		}
		

	}
	/* update the picture variable location */
	public int dist(int x1, int y1, int x2, int y2) {
		return (int) (Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1)));
	}
	
	

}
