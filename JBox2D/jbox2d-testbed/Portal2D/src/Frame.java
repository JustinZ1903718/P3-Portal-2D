package Portal2D.src;
//fo
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

	private int groundY;
	private Wall groundW=null;
	private boolean wallRight=false;
	private boolean wallLeft=false;
	private boolean releaseInAir=false;
	private boolean holdLeft=false;
	private boolean holdRight=false;
	private boolean recentLeft=false;

	private boolean justTele=false;
	private int p1Direction = -1;
	private int p2Direction = -1;

	private boolean pickUp=false;
	private boolean p1Horizontal = false;
	private boolean p2Horizontal = false;
	private int frameCount = 0;

	Enemy e1=new Enemy(200, 200, 500);
	Enemy e2=new Enemy(300, 300, 500); 
	Enemy e3=new Enemy(400, 400, 500);
	Crosshair c = new Crosshair();
	Cube c1=new Cube(700, 300);

	ArrayList<Wall> walls=new ArrayList<>(); {
		walls.add(new Wall(0, 400, 1000, 500, false));
		walls.add(new Wall(0, 200, 400, 400, false));
		walls.add(new Wall(500, 200, 1000, 325, false));
	}
	ArrayList<Laser> lasers=new ArrayList<>();
	ArrayList<Shot> s=new ArrayList<>();


	public void paint(Graphics g) {
		frameCount++;
		super.paintComponent(g);
		b.paint(g);

		for(Wall w: walls) {
			w.paint(g);
		}
		for(Laser i: lasers) {
			i.paint(g);
		}
		Portal p1=new Portal(p1x, p1y, true, p1Horizontal);
		Portal p2=new Portal(p2x, p2y, false, p2Horizontal);
		p1.paint(g);
		p2.paint(g);
		c.paint(g);
		e1.paint(g);e1.setCooldown(e1.getCooldown()+1);
		e2.paint(g);e2.setCooldown(e2.getCooldown()+1);
		e3.paint(g);e3.setCooldown(e3.getCooldown()+1);

		if(closePB(p, b) || closeBC(b, c1)) {
			b.pressed();
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


		if(!holdLeft&&!holdRight) {
			p.setVX(0);
		}
		if(holdRight&&!holdLeft) {
			p.setVX(5);
		}
		if(!holdRight&&holdLeft) {
			p.setVX(-5);
		}
		if(holdRight&&holdLeft) {
			if(recentLeft) {
				p.setVX(-5);
			}
			else {
				p.setVX(5);
			}
		}
		p.paint(g);
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
		
		for(Wall w: walls) {


			if(s.size()!=0) {
				if(collision((int)s.get(0).getX(), (int)s.get(0).getY(), (int)s.get(0).getX(), (int)s.get(0).getY(), w)) {
					
					if(!w.getPrePortal()) {
						if(s.get(0).getOrange()) {
							if(checkTop((int)s.get(0).getX(), (int)s.get(0).getY(), (int)s.get(0).getX(), (int)s.get(0).getY(), w)) {
								p1Horizontal = true;
								p1Direction = 0;
								p1x = (int) s.get(0).getX()-30;
								p1y = (int) w.getTopY()-10;
								
							}
							else if(checkBottom((int)s.get(0).getX(), (int)s.get(0).getY(), (int)s.get(0).getX(), (int)s.get(0).getY(), w)) {
								p1Horizontal = true;
								p1Direction = 1;
								p1x = (int) s.get(0).getX()-30;
								p1y = (int) w.getBy();
							}
							else if(checkLeft((int)s.get(0).getX(), (int)s.get(0).getY(), (int)s.get(0).getX(), (int)s.get(0).getY(), w)){
								p1Horizontal = false;
								p1Direction = 2;
								p1x = (int) w.getBx()-30;
								p1y = (int) (s.get(0).getY());
							}
							else if(checkRight((int)s.get(0).getX(), (int)s.get(0).getY(), (int)s.get(0).getX(), (int)s.get(0).getY(), w)){
								p1Horizontal = false;
								p1Direction = 3;
								p1x = (int) w.getTopX()-30;
								p1y = (int) (s.get(0).getY());
							}
							if(s.size() != 0) s.remove(0);
							

						}
						else {
							if(checkTop((int)s.get(0).getX(), (int)s.get(0).getY(), (int)s.get(0).getX(), (int)s.get(0).getY(), w)) {
								p2Horizontal = true;
								p2Direction = 0;
								p2x = (int) s.get(0).getX();
								p2y = (int) w.getTopY()-5;
								
							}
							else if(checkBottom((int)s.get(0).getX(), (int)s.get(0).getY(), (int)s.get(0).getX(), (int)s.get(0).getY(), w)) {
								p2Horizontal = true;
								p2Direction = 1;
								p2x = (int) s.get(0).getX();
								p2y = (int) w.getBy();
							}
							else if(checkLeft((int)s.get(0).getX(), (int)s.get(0).getY(), (int)s.get(0).getX(), (int)s.get(0).getY(), w)){
								p2Horizontal = false;
								p2Direction = 2;
								p2x = (int) w.getBx();
								p2y = (int) (s.get(0).getY());
							}
							else if(checkRight((int)s.get(0).getX(), (int)s.get(0).getY(), (int)s.get(0).getX(), (int)s.get(0).getY(), w)){
								p2Horizontal = false;
								p2Direction = 3;
								p2x = (int) w.getTopX();
								p2y = (int) (s.get(0).getY());
							}
							if(s.size() != 0) s.remove(0);
						}
					}
					w.setPrePortal(true);
				}
				else {
					w.setPrePortal(false);
					s.get(0).paint(g);
				}

			}







			if(checkLeft(p.getX(), p.getY(), p.getX()+45, p.getY()+40, w)) {//wall on the left
				p.setX(prevX);
				wallLeft=true; holdLeft=false;holdRight=false;
				System.out.println("Left");
			}
			else {wallRight=false;}
			if(checkRight(p.getX(), p.getY(), p.getX()+45, p.getY()+40, w)) {//wall on the right
				p.setX(prevX);
				wallRight=true;	
				System.out.println("Right");
				holdRight=false;holdLeft=false;

			}
			else {wallLeft=false;}
			if(checkTop(p.getX(), p.getY(), p.getX()+45, p.getY()+40, w)) {//wall on the top
				p.setGround(true);
				System.out.println("Top");
				if(releaseInAir) {
					p.setVX(0);
					releaseInAir=false;
				}
				groundY=w.getTopY()-40;
				groundW=w;
//pls woml  v     vfg 
			}
			if(checkBottom(p.getX(), p.getY(), p.getX()+45, p.getY()+40, w)) {//wall on the bottom
				p.setY(prevY);p.setVY((int) 0.5*p.getVY());
				System.out.println("Bottom");
			}
		}


		if(groundW!=null&&(p.getX()+40<groundW.getTopX()||p.getX()>groundW.getBx())) {
			p.setGround(false);p.setVY(0);
			groundW=null;
		}


		if(p.getGround()) {
			p.setY(groundY);
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
					 if(p2Direction == 2) {
						p.setX(p2x + 30);
						p.setY(p2y);
					}
					else if(p2Direction == 3) {
						p.setX(p2x - 30);
						p.setY(p2y);
					}
					justTele=true;
					teleported=true;
				}	
			}
		}
		else {
			if(p.getY()+50>p1y&&prevY+50<=p1y) {
				if(Math.abs(p.getX()-15-p1x)<50&&!justTele&&p2x!=9001) {
					if(p2Direction == 0) { // top, bottom, left, right
						p.setX(p2x);
						p.setY(p2y - 30);
						if(p1Direction == 0) p.setVY(-1*p.getVY());
					}
					else if(p2Direction == 1) {
						p.setX(p2x);
						p.setY(p2y + 30);
					}
					justTele=true;
					teleported=true;
				}	
			}
			if(p.getX()+50<p1y&&prevY+50>=p1y) {
				if(Math.abs(p.getX()-15-p1x)<50&&!justTele&&p2x!=9001) {
					p.setX(p2x);
					p.setY(p2y-40);
					justTele=true;
					teleported=true;
				}	
			}
		}
		if(!p2.getHorizontal()) {
			if(p.getX()<p2x&&prevX>=p2x) {
				if(Math.abs(p.getY()-15-p2y)<50&&!justTele&&p1x!=9001) {
					if(p1Direction == 2) {
						p.setX(p1x + 30);
						p.setY(p1y);
					}
					else if(p1Direction == 3) {
						p.setX(p1x - 30);
						p.setY(p1y);
					}
					justTele=true;
					teleported=true;
				}	
			}
		}
		else {
			if(p.getY()+50>p2y&&prevY+50<=p2y) {
				if(Math.abs(p.getX()-15-p2x)<50&&!justTele&&p1x!=9001) {
					if(p1Direction == 0) { // top, bottom, left, right
						p.setX(p1x);
						p.setY(p1y - 40);
						if(p2Direction == 0) p.setVY(-1*p.getVY());
					}
					else if(p1Direction == 1) {
						p.setX(p1x);
						p.setY(p1y + 30);
					}
					justTele=true;
					teleported=true;
				}	
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
	public void shoot(Enemy e, Player wp) {
		if(e.getCooldown()>40 && !e.getDead()) {
			lasers.add(new Laser(e.getX()+20, e.getY()+20, p.getX()+25, p.getY()+20, 250));
			e.setCooldown(0);
		}
	}
	public boolean collision(int tx, int ty, int bx, int by, Wall w) {
		if(checkLeft(tx, ty, bx, by, w)) {
			return true;
		}
		if(checkRight(tx, ty, bx, by, w)) {
			return true;
		}
		if(checkTop(tx, ty, bx, by, w)) {
			return true;
		}
		if(checkBottom(tx, ty, bx, by, w)) {
			return true;
		}
		return false;
	}
	public boolean checkRight(int tx, int ty, int bx, int by, Wall w) {
		if(w.getTopY()<by&&w.getBy()>ty) {
			if(bx>=w.getTopX()&&bx<=w.getTopX()+20) {
				return true;
			}
		}
		return false;
	}
	public boolean checkLeft(int tx, int ty, int bx, int by, Wall w) {
		if(w.getTopY()<by&&w.getBy()>ty) {
			if(tx<w.getBx()&&tx+40>w.getBx()) {
				return true;
			}
		}
		return false;
	}
	public boolean checkTop(int tx, int ty, int bx, int by, Wall w) {
		if(w.getTopX()<bx&&w.getBx()>tx) {
			if(by>w.getTopY()&&by-30<w.getTopY()) {
				return true;
			}
		}
		return false;
	}
	public boolean checkBottom(int tx, int ty, int bx, int by, Wall w) {
		if(w.getTopX()<bx&&w.getBx()>tx) {
			if(by+12>w.getBy()&&ty-12<w.getBy()) {
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
		f.setSize(new Dimension(1500, 1000));
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

		if(arg0.getKeyCode() == 65&&!wallLeft) {
			holdLeft=true;recentLeft=true;
			p.turnLeft();
		}

		if(arg0.getKeyCode() == 68&&!wallRight) {
			holdRight=true;recentLeft=false;
			p.turnRight();
		}
		if(arg0.getKeyCode() == 87&&p.getGround()) {
			p.setVY(-12);
			groundW=null;
			p.setGround(false);
			
		}


		if((arg0.getKeyCode() == 32 && closePC(p, c1) )|| (pickUp && arg0.getKeyCode() == 32)) {
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
		if(arg0.getKeyCode()==65) {
			holdLeft=false;
			recentLeft=false;
			if(p.getGround()) {
				p.setVX(0);
			}
			else {
				releaseInAir=true;
			}
		}
		if(arg0.getKeyCode()==68) {
			holdRight=false;
			recentLeft=false;
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
