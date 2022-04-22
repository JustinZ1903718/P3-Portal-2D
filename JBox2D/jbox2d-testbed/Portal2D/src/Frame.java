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
	private int xPressFrame=0;

	private int groundY;
	private boolean releaseInAir=false;
	private boolean movingRight=false;
	private boolean movingLeft=false;

	private boolean justTele=false;

	private int frameCount;

	private boolean pickUp=false;
	private boolean prePortal = false;

	Enemy e1=new Enemy(200, 200, 500);
	Enemy e2=new Enemy(300, 300, 500); 
	Enemy e3=new Enemy(400, 400, 500);
	Crosshair c = new Crosshair();
	Cube c1=new Cube(700, 300);
	
	Wall w=new Wall(0, 400, 1000, 500, false);
	ArrayList<Laser> lasers=new ArrayList<>();
	ArrayList<Shot> s=new ArrayList<>();


	public void paint(Graphics g) {
		frameCount++;
		super.paintComponent(g);
		b.paint(g);

		w.paint(g);
		for(Laser i: lasers) {
			i.paint(g);
		}
		Portal p1=new Portal(p1x, p1y, true);
		Portal p2=new Portal(p2x, p2y, false);
		p1.paint(g);
		p2.paint(g);
		c.paint(g);
		e1.paint(g);e1.setCooldown(e1.getCooldown()+1);
		e2.paint(g);e2.setCooldown(e2.getCooldown()+1);
		e3.paint(g);e3.setCooldown(e3.getCooldown()+1);

		if(closePB(p, b) || closeBC(b, c1)) {
			b.pressed();
			System.out.println("joey");
		}
		else {
			b.notPressed();
		}
		if(closeEC(e1, c1)) {
			e1.setDead();
		}
		if(closeEC(e2, c1)) {
			e2.setDead();
		}
		if(closeEC(e3, c1)) {
			e3.setDead();
		}


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
		
		if(s.size()!=0) {
			if(collision((int)s.get(0).getX(), (int)s.get(0).getY(), (int)s.get(0).getX(), (int)s.get(0).getY(), w)) {
				if(!prePortal) {
					if(s.get(0).getOrange()) {
						p1x = (int) s.get(0).getX();
						p1y = (int) s.get(0).getY()-50;
					}
					else {
						p2x = (int) s.get(0).getX();
						p2y = (int) s.get(0).getY()-50;
					}
				}
				prePortal = true;
			}
			else {
				prePortal = false;
				s.get(0).paint(g);
			}
			
		}
		if(!p1.getHorizontal()) {
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
		}
		else {
			
		}
		if(!p2.getHorizontal()) {
			if(p.getX()+20>p2x&&prevX+20<=p2x) {
				if(Math.abs(p.getY()-15-p2y)<50&&!justTele&&p1x!=9001) {
					p.setX(p1x);
					p.setY(p1y);
					justTele=true;
					teleported=true;
				}	
			}
		}
		
		if(checkTl(p.getX(), p.getY(), p.getX()+45, p.getY()+40, w)&&checkBl(p.getX(), p.getY(), p.getX()+45, p.getY()+40, w)) {//wall on the left
			p.setX(prevX);
		}
		if(checkTr(p.getX(), p.getY(), p.getX()+45, p.getY()+40, w)&&checkBr(p.getX(), p.getY(), p.getX()+45, p.getY()+40, w)) {//wall on the left
			p.setX(prevX);
		}
		if(checkBr(p.getX(), p.getY(), p.getX()+45, p.getY()+40, w)&&checkBl(p.getX(), p.getY(), p.getX()+45, p.getY()+40, w)) {//wall on the left
			p.setGround(true);
			if(releaseInAir) {
				p.setVX(0);
			}
			groundY=w.getTopY()-40;
		}
		if(checkTr(p.getX(), p.getY(), p.getX()+45, p.getY()+40, w)&&checkTl(p.getX(), p.getY(), p.getX()+45, p.getY()+40, w)) {//wall on the left
			p.setY(prevY);
		}

		if(p.getGround()) {
			p.setY(groundY);
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
		if(p.getGround()) {
			if(!movingRight&&!movingLeft) {
				p.setVX(0);
			}
			if(movingRight&&!movingLeft) {
				p.setVX(5);
			}
			if(!movingRight&&movingLeft) {
				p.setVX(-5);
			}
		}
		p.paint(g);
		

		if(p.getX()+20>p1x&&prevX+20<=p1x) {
			if(Math.abs(p.getY()-15-p1y)<50&&!justTele&&p2x!=9001) {
				p.setX(p2x);
				p.setY(p2y);
				justTele=true;
				teleported=true;
				p.setGround(false);p.setVY(0);
			}	
		}
		if(p.getX()<p1x&&prevX>=p1x) {
			if(Math.abs(p.getY()-15-p1y)<50&&!justTele&&p2x!=9001) {
				p.setX(p2x);
				p.setY(p2y);
				justTele=true;
				teleported=true;
				p.setGround(false);p.setVY(0);
			}	
		}

		if(p.getX()+20>p2x&&prevX+20<=p2x) {
			if(Math.abs(p.getY()-15-p2y)<50&&!justTele&&p1x!=9001) {
				p.setX(p1x);
				p.setY(p1y);
				justTele=true;
				teleported=true;
				p.setGround(false);p.setVY(0);
			}	
		}
		if(p.getX()<p2x&&prevX>=p2x) {
			if(Math.abs(p.getY()-15-p2y)<50&&!justTele&&p1x!=9001) {
				p.setX(p1x);
				p.setY(p1y);
				justTele=true;
				teleported=true;
				p.setGround(false);p.setVY(0);
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
	public boolean collision(int tx, int ty, int bx, int by, Wall w) {
		if(checkTl(tx, ty, bx, by, w)) {
			return true;
		}
		if(checkTr(tx, ty, bx, by, w)) {
			return true;
		}
		if(checkBl(tx, ty, bx, by, w)) {
			return true;
		}
		if(checkBr(tx, ty, bx, by, w)) {
			return true;
		}
		return false;
	}

	public boolean checkTl(int tx, int ty, int bx, int by, Wall w) {
		if(w.getTopX()<tx&&w.getBx()>tx) {
			if(w.getTopY()<ty&&w.getBy()>ty) {
				return true;
			}
		}
		return false;
	}
	public boolean checkTr(int tx, int ty, int bx, int by, Wall w) {
		if(w.getTopX()<bx&&w.getBx()>bx) {
			if(w.getTopY()<ty&&w.getBy()>ty) {
				return true;
			}
		}
		return false;
	}
	public boolean checkBl(int tx, int ty, int bx, int by, Wall w) {
		if(w.getTopX()<tx&&w.getBx()>tx) {
			if(w.getTopY()<by&&w.getBy()>by) {
				return true;
			}
		}
		return false;
	}
	public boolean checkBr(int tx, int ty, int bx, int by, Wall w) {
		if(w.getTopX()<bx&&w.getBx()>bx) {
			if(w.getTopY()<by&&w.getBy()>by) {
				return true;
			}
		}
		return false;
	}
	public void shootPortal(Player p, int x, int y, boolean orange) {
		Shot s1 = new Shot(p.getX(),p.getY(), x, y, 1500, orange);
		if(s.size() == 0) s.add(0, s1);
		else s.set(0, s1);
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
			shootPortal(p, arg0.getX(), arg0.getY(), true);
			if(p1x<p.getX()) {
				p.turnLeft();
			}
			else {
				p.turnRight();
			}
		}
		else if(arg0.getButton() == MouseEvent.BUTTON1) {
			shootPortal(p, arg0.getX(), arg0.getY(), false);
			if(p2x<p.getX()) {
				p.turnLeft();
			}
			else {
				p.turnRight();
			}

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
			movingRight=false;movingLeft=true;
			p.setVX(-5);
			p.turnLeft();
		}

		if(arg0.getKeyCode() == 68) {
			movingRight=true;movingLeft=false;
			p.turnRight();
		}
		if(arg0.getKeyCode() == 87&&p.getGround()) {
			p.setVY(-12);
			p.setGround(false);
		}


		if((arg0.getKeyCode() == 32 && closePC(p, c1) )|| (pickUp && arg0.getKeyCode() == 32)) {
			System.out.println("We stay picky");
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
		if(p.getX()+20>=b.getX() && p.getX()-20<=b.getX() && p.getY() <= b.getY()-20&&p.getY()>=b.getY()-40) {
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
		if((arg0.getKeyCode() == 65 || arg0.getKeyCode() == 68)) {
			System.out.println("released");
			movingRight=false;
			movingLeft=false;
			if(p.getGround()) {
				p.setVX(0);
			}
			else {
				releaseInAir=true;
			}
		}
		if((arg0.getKeyCode() == 83 || arg0.getKeyCode() == 87)&&p.getGround()) {
			p.setVY(0);
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
