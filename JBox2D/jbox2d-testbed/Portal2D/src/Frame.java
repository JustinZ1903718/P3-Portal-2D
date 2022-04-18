package Portal2D.src;

import java.awt.Color;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener, MouseMotionListener {

	Player p=new Player(69, 69);
Button b = new Button(250, 250);
	private int p1x=9001;
	private int p1y=9001;
	private int p2x=9001;
	private int p2y=9001;
	 
	private int prevX;
	private int prevY;
	private boolean justTele=false;
	private int frameCount;
	
	private boolean pickUp=false;
	
	Enemy e1=new Enemy(200, 200, 500);
	Enemy e2=new Enemy(300, 300, 500); 
	Enemy e3=new Enemy(400, 400, 500);
	Crosshair c = new Crosshair();
	Cube c1=new Cube(700, 300);
	ArrayList<Laser> lasers=new ArrayList<>();

	public void paint(Graphics g) {
		super.paintComponent(g);
		b.paint(g);
		p.paint(g);
		if(closePB(p, b) || closeBC(b, c1)) {
				b.pressed();
				System.out.println("joey");
			}
		else {
			b.notPressed();
		}
		for(Laser i: lasers) {
			i.paint(g);
		}
		frameCount++;
		Portal p1=new Portal(p1x, p1y, true);
		Portal p2=new Portal(p2x, p2y, false);
		p1.paint(g);
		p2.paint(g);
		c.paint(g);
		e1.paint(g);e1.setCooldown(e1.getCooldown()+1);
		e2.paint(g);e2.setCooldown(e2.getCooldown()+1);
		e3.paint(g);e3.setCooldown(e3.getCooldown()+1);
		if(closeEC(e1, c1)) {
			e1.setDead();
		}
		if(closeEC(e2, c1)) {
			e2.setDead();
		}
		if(closeEC(e3, c1)) {
			e3.setDead();
		}
		if(pickUp) {
			if(!p.getIsLeft()) {
				c1.setX(p.getX()+50);
				c1.setY(p.getY()+10);
			}
			else {
				c1.setX(p.getX()-20);
				c1.setY(p.getY()+8);
			}
		}
		c1.paint(g);
		
		if(dist(e1, p)<200) {
			shoot(e1, p);
		}
		if(dist(e2, p)<200) {
			shoot(e2, p);
		}
		if(dist(e3, p)<200) {
			shoot(e3, p);
		}
		
		boolean teleported=false;
		
		if(p.getX()+20>p1x&&prevX+20<=p1x) {
			if(Math.abs(p.getY()-15-p1y)<50&&!justTele&&p2x!=9001) {
				p.setX(p2x);
				p.setY(p2y);
				justTele=true;
				teleported=true;
			}	
		}
		if(p.getX()<p1x&&prevX>=p1x) {
			if(Math.abs(p.getY()-15-p1y)<50&&!justTele&&p2x!=9001) {
				p.setX(p2x);
				p.setY(p2y);
				justTele=true;
				teleported=true;
			}	
		}
		
		if(p.getX()+20>p2x&&prevX+20<=p2x) {
			if(Math.abs(p.getY()-15-p2y)<50&&!justTele&&p1x!=9001) {
				p.setX(p1x);
				p.setY(p1y);
				justTele=true;
				teleported=true;
			}	
		}
		if(p.getX()<p2x&&prevX>=p2x) {
			if(Math.abs(p.getY()-15-p2y)<50&&!justTele&&p1x!=9001) {
				p.setX(p1x);
				p.setY(p1y);
				justTele=true;
				teleported=true;
			}	
		}
		if(justTele&&!teleported) {
			justTele=false;
		}
		prevX=p.getX();
		prevY=p.getY();
	}
	public int dist(Enemy e, Player p) {
		int xdif=(e.getX()-p.getX())*(e.getX()-p.getX());
		int ydif=(e.getY()-p.getY())*(e.getY()-p.getY());
		return (int) (Math.sqrt(xdif+ydif));
	}
	public void shoot(Enemy e, Player p) {
		if(e.getCooldown()>40 && !e.getDead()) {
			lasers.add(new Laser(e.getX()+20, e.getY()+20, p.getX()+25, p.getY()+20, 250));
			e.setCooldown(0);
		}
	}
	
	
	public static void main(String[] arg) {
		Frame f = new Frame();
		
	}
	
	public Frame() {
		JFrame f = new JFrame("Portal2D-Ed");
		f.setSize(new Dimension(1000, 500));
		f.setBackground(Color.blue);
		f.add(this);
		f.setResizable(false);
		f.setLayout(new GridLayout(1,2));
		f.addMouseListener(this);
		f.addMouseMotionListener(this);
		f.addKeyListener(this);
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		if(arg0.getButton() == MouseEvent.BUTTON3) {
			p1x=arg0.getX()-29;
			if(p1x<p.getX()) {
				p.turnLeft();
			}
			else {
				p.turnRight();
			}
			p1y=arg0.getY()-50;
		}
		else if(arg0.getButton() == MouseEvent.BUTTON1) {
			p2x=arg0.getX()-10;
			if(p2x<p.getX()) {
				p.turnLeft();
			}
			else {
				p.turnRight();
			}
			p2y=arg0.getY()-50;

		}

		
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
			System.out.println(arg0.getKeyCode());
			
			if(arg0.getKeyCode() == 65) {
				p.setVX(-5);
				p.turnLeft();
			}
			
			if(arg0.getKeyCode() == 68) {
				p.setVX(5);
				p.turnRight();
			}
			if(arg0.getKeyCode() == 87) {
				p.setVY(-5);
			}
			
			if(arg0.getKeyCode() == 83) {
				p.setVY(5);
			}
			if(arg0.getKeyCode() == 32 && closePC(p, c1) || pickUp && arg0.getKeyCode() == 32) {
				pickUp = !pickUp;
			}
			
		}
	private boolean closePC(Player p, Cube c1) {
		// TODO Auto-generated method stub
		if(p.getX()-40<=c1.getX() && p.getX()+50>=c1.getX()) {
			if(p.getY()-40<=c1.getY() && p.getY()+30>=c1.getY()) {
				return true;
			}
		}
		return false;
	}
	private boolean closeEC(Enemy e, Cube c1) {
		// TODO Auto-generated method stub
		if(e.getX()-10<=c1.getX() && e.getX()+50>=c1.getX()) {
			if(e.getY()-10<=c1.getY() && e.getY()+50>=c1.getY()) {
				return true;
			}
		}
		return false;
	}
	private boolean closeBC(Button b, Cube c1) {
		// TODO Auto-generated method stub
		if(b.getX()-20<=c1.getX() && b.getX()+20>=c1.getX()) {
			if(b.getY()-20<=c1.getY() && b.getY()+10>=c1.getY()) {
				return true;
			}
		}
		return false;
	}
	private boolean closePB(Player p, Button b) {
		if(p.getX()>=b.getX() && p.getX()-20<=b.getX() && p.getY() <= b.getY()-20&&p.getY()>=b.getY()-40) {
			return true;
		}
		return false;
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		c.setX(e.getX());
		c.setY(e.getY());
	}

	

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode() == 65 || arg0.getKeyCode() == 68) {
			p.setVX(0);
		}
		if(arg0.getKeyCode() == 83 || arg0.getKeyCode() == 87) {
			p.setVY(0);
		}
		if(arg0.getKeyCode() == 32) {
			
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
