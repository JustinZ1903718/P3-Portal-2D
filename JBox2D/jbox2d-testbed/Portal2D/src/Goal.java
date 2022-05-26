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
public class Goal {
	private int x;
	private int y;
	private boolean open;
	private Image img; 	
	private AffineTransform tx;
	public Goal(int x, int y, boolean open) {
		this.x = x;
		this.y = y;
		this.open = open;
		if(open)
			img = getImage("Door.png"); 
		else 
			img=getImage("closedDoor.png");
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y); 
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
	public boolean getOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
		if(open)
			img = getImage("Door.png"); 
		else 
			img=getImage("closedDoor.png");
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
		tx.scale(1.5,1.5);
	}
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(1.5,1.5);
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

