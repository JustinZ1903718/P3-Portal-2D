package Portal2D.src;
//it's about drive
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

	Player p=new Player(69, 869);
	Button b = new Button(40, 280, 7);
	private int p1x=9001;
	private int p1y=9001;
	private int p2x=9001;
	private int p2y=9001;
	private int startX =69 ;
	private int startY = 869;
	private int cStartX = 150;
	private int cStartY = 420;


	private int prevX;
	private int prevY;
	private int prevprevX;
	private int prevprevY;

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
	private int wallP1 = -1;
	private int wallP2 = -1;
	private int level = 0;

	Crosshair c = new Crosshair();
	Goal go = new Goal(1000, 400, false);
	
	ArrayList<Enemy> enemies = new ArrayList<>();{
		/*
		enemies.add(new Enemy(700, 150, 500));
		enemies.add(new Enemy(900, 150, 500));
		enemies.add(new Enemy(500, 350, 500));
		*/
		
		enemies.add(new Enemy(400, 450, 500));
		enemies.add(new Enemy(450, 450, 500));
		enemies.add(new Enemy(500, 450, 500));
		enemies.add(new Enemy(550, 450, 500));
		enemies.add(new Enemy(600, 450, 500));
		enemies.add(new Enemy(650, 450, 500));
		enemies.add(new Enemy(700, 450, 500));
		enemies.add(new Enemy(750, 450, 500));
	}
	ArrayList<Wall> walls=new ArrayList<>(); {
		walls.add(new Wall(0, 0, 2000, 10, false, 0));
		walls.add(new Wall(0, 0, 10, 2000, false, 1));
		walls.add(new Wall(1480, 0, 1500, 1000, false, 2));
		
		/*walls.add(new Wall(200, 400, 1000, 500, true, 3));
		walls.add(new Wall(0, 200, 250, 400, true, 4));
		walls.add(new Wall(500, 200, 1000, 325, true, 5));
		walls.add(new Wall(600, 0, 650, 400, false, 6));
		*/
		walls.add(new Wall(0, 900, 200, 1000, true, 3));
		walls.add(new Wall(200, 750, 400, 850, true, 4));
		walls.add(new Wall(100, 540, 200, 580, false, 5));
		walls.add(new Wall(400, 500, 1500, 550, true, 6));
		walls.add(new Wall(800, 200, 820, 500, false, 7));
		walls.add(new Wall(0, 300, 220, 400, true, 8));
	}
	ArrayList<Laser> lasers=new ArrayList<>();
	ArrayList<Shot> s=new ArrayList<>();
	ArrayList<Cube> c1 = new ArrayList<>();{
		c1.add(new Cube(150, 420));
	}

	public void paint(Graphics g) {
		super.paintComponent(g);
		b.paint(g);
		go.paint(g);
		if(b.getPressed()) {
			walls.get(b.getWallIndex()).setExist(false);
			go.setOpen(true);
		}
		else {
			walls.get(b.getWallIndex()).setExist(true);	
			go.setOpen(false);
		}
		if(go.getOpen() && closePG(p, go)) {
			System.out.println("joey poggies");
			level++;
			//updateWalls(walls, level);
			//updateEnemy(enemies, level);
		}
		for(Wall w: walls) {
			w.paint(g);
		}
		for(Laser i: lasers) {
			if(!i.getPaint()) {
				lasers.remove(i);
			}
			i.paint(g);
			if(pickUp&&hitL(c1.get(0).getX()-5, c1.get(0).getY()-15, c1.get(0).getX()+30, c1.get(0).getY()+40, i)) {
				lasers.remove(i);
			}

			for(Wall w: walls) {
				if(w.getExist()&&hitL(w.getTopX(), w.getTopY(), w.getBx(), w.getBy(), i)) {
					lasers.remove(i);
				}
			}
			if(hitL(p.getX()+5, p.getY(), p.getX()+32, p.getY()+40, i)) {
				resetLevel();
			}


		}
		if(closePC(p, c1.get(0))) {
			if(p.getVX() > 0 && p.getX() < c1.get(0).getX()) {
				c1.get(0).setVx(p.getVX());
				c1.get(0).addX();
			}
			if(p.getVX() < 0&&p.getX()>c1.get(0).getX()) {
				c1.get(0).setVx(p.getVX());
				c1.get(0).addX();
			}
		}
		
		Portal p1=new Portal(p1x, p1y, true, p1Horizontal);
		Portal p2=new Portal(p2x, p2y, false, p2Horizontal);
		p1.paint(g);
		p2.paint(g);
		if(p.getY()>1000) {
			resetLevel();
			p.setVY(0);
		}
		c.paint(g);
		for(Enemy e: enemies) {
			e.paint(g);e.setCooldown(e.getCooldown()+1);
			if(dist(e, p)<200) {
				shoot(e, p);
			}
			if(closeEC(e, c1.get(0))) {//change idot
				e.setDead(true);
			}
		}

		if(closePB(p, b) || closeBC(b, c1.get(0))) {
			b.setPressed(true);
		}
		else {
			b.setPressed(false);
		}
		


		

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
			c1.get(0).setVy(p.getVY());
			c1.get(0).setVx(p.getVX());
			if(!p.getIsLeft()) {
				c1.get(0).setX(p.getX()+50);
				c1.get(0).setY(p.getY()+10);
			}
			else {
				c1.get(0).setX(p.getX()-20);
				c1.get(0).setY(p.getY()+8);
			}
		}
		c1.get(0).paint(g);

		for(Wall w: walls) {
			shootPortal(w, g);
		}

		if(groundW!=null&&(p.getX()+40<groundW.getTopX()||p.getX()>groundW.getBx())) {
			p.setGround(false);p.setVY(0);
			groundW=null;
		}


		teleportPlayer(p1, p2);
		teleportCube(p1, p2, c1.get(0));
		if(p.getGround()) {
			if(prevY == prevprevY) {
				p.setY(groundY);
			}
			p.setVY(0);

		}
		if(justTele) {
			justTele=false;
		}

		prevprevX = prevX;
		prevprevY = prevY;
		prevX=p.getX();
		prevY=p.getY();
	}
	public void updateWalls(ArrayList<Wall> w, int level) {
		for(int i = w.size();i>2;i--) {
			w.remove(i);
		}
	}
	public void updateEnemy(ArrayList<Enemy> e, int level) {
		
	}
	public void updateCube(ArrayList<Cube> c, int level) {
		
	}
	public void updatePlayer(Player p, int level) {
		
	}
	public void updateCubeLoc(Wall w) {
		if(checkLeft(c1.get(0).getX(), c1.get(0).getY(), c1.get(0).getX()+20, c1.get(0).getY()+20, w)) {//wall on the left
			c1.get(0).setX(w.getBx());
		}

		if(checkRight(c1.get(0).getX(), c1.get(0).getY(), c1.get(0).getX()+20, c1.get(0).getY()+20, w)) {//wall on the right
			c1.get(0).setX(w.getTopX()-20);

		}
		if(checkTop(c1.get(0).getX(), c1.get(0).getY(), c1.get(0).getX()+20, c1.get(0).getY()+20, w)) {//wall on the top
			c1.get(0).setY(w.getTopY()-20);c1.get(0).setVy(0);
		}
		if(checkBottom(c1.get(0).getX(), c1.get(0).getY(), c1.get(0).getX()+20, c1.get(0).getY()+20, w)) {//wall on the bottom
			c1.get(0).setY(w.getBy());
		}
	}
	public void teleportCube(Portal p1, Portal p2, Cube c1) {
		if(!p1.getHorizontal()) { //UNCOMBINE CEILING AND FLORR PORTAL IDOT
			if((p1Direction == 3 && c1.getX()+25>p1x&&c1.getVx()>0 && !(c1.getX()-10> p1x))|| (p1Direction == 2 && c1.getX()-40<p1x&&c1.getVx()<0 &&c1.getX()+10> p1x)) { //orange portal on right wall || portal on left wall
				if(Math.abs(c1.getY()-15-p1y)<50&&p2x!=9001) {
					if(p2Direction == 0) { // top, bottom, left, right
						c1.setVx(-10);
						c1.setX(p2x+20);
						c1.setY(p2y - 50);
					}
					else if(p2Direction == 1) {
						c1.setX(p2x+20);
						c1.setY(p2y+20);
					}
					if(p2Direction == 2) {
						c1.setVx(5);
						c1.setX(p2x+20);
						c1.setY(p2y);
					}
					else if(p2Direction == 3) {
						c1.setVx(-5);
						c1.setX(p2x - 10);
						c1.setY(p2y);
					}
				}

			}
		}
		else {
			if(p1Direction == 0 && c1.getY()+15>p1y&&c1.getVy()>0 && !(c1.getY()> p1y)|| p1Direction == 1 && c1.getY()-15<p1y&&c1.getVy()<0 && !(c1.getY()< p1y)) { //orange portal is on floor or ceiling
				if(Math.abs(c1.getX()-15-p1x)<50&&p2x!=9001) {
					if(p2Direction == 0) { // top, bottom, left, right
						if(p1Direction == 0) {
							c1.setVy(-1*c1.getVy());

						}
						c1.setX(p2x+20);
						c1.setY(p2y - 50);
					}
					else if(p2Direction == 1) {
						c1.setX(p2x+20);
						c1.setY(p2y+20);
					}
					if(p2Direction == 2) {
						c1.setVx(5);c1.setVy(0);
						c1.setX(p2x+20);
						c1.setY(p2y);
					}
					else if(p2Direction == 3) {
						c1.setVx(-5);c1.setVy(0);
						c1.setX(p2x - 10);
						c1.setY(p2y);
					}

				}	
			}
		}
		if(!p2.getHorizontal()) {
			if(p2Direction == 3 && c1.getX()+25>p2x&&c1.getVx()>0 && !(c1.getX()> p2x)|| p2Direction == 2 && c1.getX()-25<p2x&&c1.getVx()<0 && !(c1.getX()< p2x)) { //blue portal on right || left wall
				if(Math.abs(c1.getY()-15-p2y)<50&&p1x!=9001) {
					if(p1Direction == 0) { // top, bottom, left, right
						c1.setVy(-10);
						c1.setX(p1x+20);
						c1.setY(p1y - 50);

					}
					else if(p1Direction == 1) {
						c1.setX(p1x+20);
						c1.setY(p1y+20);
					}
					if(p1Direction == 2) {
						c1.setX(p1x + 20);
						c1.setY(p1y);
						c1.setVx(5);c1.setVy(0);
					}
					else if(p1Direction == 3) {
						c1.setVx(-5);c1.setVy(0);
						c1.setX(p1x - 10);
						c1.setY(p1y);
					}

				}	

			}

		}
		else {
			if(p2Direction == 0 && c1.getY()+15>p2y&&c1.getVy()>0  && !(c1.getY()> p2y)|| p2Direction == 1 && c1.getY()-15<p2y&&c1.getVy()<0  && !(c1.getY()< p2y)) { //blue portal is on floor || ceiling
				if(Math.abs(c1.getX()-15-p2x)<50&&p1x!=9001) {
					if(p1Direction == 0) { // top, bottom, left, right
						if(p2Direction == 0) {
							c1.setVy(-1*c1.getVy());
						}
						c1.setX(p1x+20);
						c1.setY(p1y - 50);

					}
					else if(p1Direction == 1) {
						c1.setX(p1x+20);
						c1.setY(p1y+20);
					}
					if(p1Direction == 2) {
						c1.setVx(5);c1.setVy(0);
						c1.setX(p1x + 20);
						c1.setY(p1y);
						groundW=null;
						p.setGround(false);
					}
					else if(p1Direction == 3) {
						c1.setVx(-5);c1.setVy(0);
						c1.setX(p1x - 10);
						c1.setY(p1y);
					}

				}	
			}
		}
	}


	


	public void teleportPlayer(Portal p1, Portal p2) {
		if(!p1.getHorizontal()) { //UNCOMBINE CEILING AND FLORR PORTAL IDOT
			if(p1Direction == 3 && p.getVX()>0 && !(p.getX()> p1x)|| p1Direction == 2 &&p.getVX()<0 && !(p.getX()< p1x)) { //orange portal on right wall || portal on left wall
				if((!pickUp&&p.getVX()>0&&p.getX()+35>p1x)||(!pickUp&&p.getVX()<0&&p.getX()-40<p1x)||(pickUp&&p.getVX()>0&&p.getX()+50>p1x)||(pickUp&&p.getVX()<0&&p.getX()-60<p1x)) {
					if(Math.abs(p.getY()-15-p1y)<50&&!justTele&&p2x!=9001) {
						if(p2Direction == 0) { // top, bottom, left, right
							p.setVY(-10);
							groundY=walls.get(wallP2).getTopY()-40;
							groundW = walls.get(wallP2);
							p.setX(p2x);
							p.setY(p2y - 50);
						}
						else if(p2Direction == 1) {
							p.setX(p2x);
							p.setY(p2y+20);
						}
						if(p2Direction == 2) {
							p.setVX(5);
							p.setX(p2x+20);
							p.setY(p2y);
						}
						else if(p2Direction == 3) {
							p.setVX(-5);
							p.setX(p2x - 30);
							p.setY(p2y);
						}
						justTele=true;
						groundW=null;
						p.setGround(false);
					}
				}
			}
		}
		else {
			if(p1Direction == 0 && p.getY()+45>p1y&&p.getVY()>0 && !(p.getY()> p1y)|| p1Direction == 1 && p.getY()-35<p1y&&p.getVY()<0 && !(p.getY()< p1y)) { //orange portal is on floor or ceiling
				if(Math.abs(p.getX()-15-p1x)<50&&!justTele&&p2x!=9001) {
					if(p2Direction == 0) { // top, bottom, left, right
						if(p1Direction == 0) {
							p.setVY(-1*p.getVY());

						}
						p.setX(p2x);
						p.setY(p2y - 50);
					}
					else if(p2Direction == 1) {
						p.setX(p2x);
						p.setY(p2y+20);
					}
					if(p2Direction == 2) {
						p.setVX(5);p.setVY(0);
						p.setX(p2x+20);
						p.setY(p2y);
					}
					else if(p2Direction == 3) {
						p.setVX(-5);p.setVY(0);
						p.setX(p2x - 30);
						p.setY(p2y);
					}
					justTele=true;
					groundW=null;
					p.setGround(false);
				}	
			}
		}
		if(!p2.getHorizontal()) {
			if(p2Direction == 3 && p.getVX()>0 && p.getX()-5<= p2x|| (p2Direction == 2 && p.getVX()<0 && !(p.getX()< p2x))) { //blue portal on right || left wall
				if((!pickUp&&p.getVX()>0&&p.getX()+50>p2x)||(!pickUp&&p.getVX()<0&&p.getX()-12<p2x)||(pickUp&&p.getVX()>0&&p.getX()+80>p2x)||(pickUp&&p.getVX()<0&&p.getX()-30<p2x)) {
					if(Math.abs(p.getY()-15-p2y)<50&&!justTele&&p1x!=9001) {
						if(p1Direction == 0) { // top, bottom, left, right
							p.setVY(-10);
							groundY=walls.get(wallP1).getTopY()-40;
							groundW = walls.get(wallP1);
							p.setX(p1x);
							p.setY(p1y - 50);

						}
						else if(p1Direction == 1) {
							p.setX(p1x);
							p.setY(p1y+20);
						}
						if(p1Direction == 2) {
							p.setVX(5);p.setVY(0);
							p.setX(p1x + 20);
							p.setY(p1y);
							
						}
						else if(p1Direction == 3) {
							p.setVX(-5);p.setVY(0);
							p.setX(p1x - 30);
							p.setY(p1y);
						}
						justTele=true;
						groundW=null;
						p.setGround(false);
					}	

				}
			}

		}
		else {
			if(p2Direction == 0 && p.getY()+45>p2y&&p.getVY()>0  && !(p.getY()> p2y)|| p2Direction == 1 && p.getY()-35<p2y&&p.getVY()<0  && !(p.getY()< p2y)) { //blue portal is on floor || ceiling
				if(Math.abs(p.getX()-15-p2x)<50&&!justTele&&p1x!=9001) {
					if(p1Direction == 0) { // top, bottom, left, right
						if(p2Direction == 0) {
							p.setVY(-1*p.getVY());
						}
						p.setX(p1x);
						p.setY(p1y - 50);

					}
					else if(p1Direction == 1) {
						p.setX(p1x);
						p.setY(p1y+20);
					}
					if(p1Direction == 2) {
						p.setVX(5);p.setVY(0);
						p.setX(p1x + 20);
						p.setY(p1y);
						groundW=null;
						p.setGround(false);
					}
					else if(p1Direction == 3) {
						p.setVX(-5);p.setVY(0);
						p.setX(p1x - 30);
						p.setY(p1y);
						groundW=null;
						p.setGround(false);
					}
					justTele=true;
					groundW=null;
					p.setGround(false);

				}	
			}
		}
	}


	public void updateCollision(int TopLeftX, int TopLeftY, int BotRightX, int BotRightY, Wall w) {

		//update the player's location and status based on a certain wall
		if(checkLeft(TopLeftX, TopLeftY, BotRightX, BotRightY, w)) {//wall on the left
			if(pickUp && p.getIsLeft()) {
				p.setX(w.getBx() + 20);
			}
			else {
				p.setX(w.getBx());
			}
			wallLeft=true; holdLeft=false;holdRight=false;

			return;
		}
		else {wallRight=false;}
		if(checkRight(TopLeftX, TopLeftY, BotRightX, BotRightY, w)) {//wall on the right
			p.setX(w.getTopX()-(BotRightX-TopLeftX));
			wallRight=true;	
			holdRight=false;holdLeft=false;
			return;
		}
		else {wallLeft=false;}
		if(checkTop(TopLeftX, TopLeftY, BotRightX, BotRightY, w)) {//wall on the top
			p.setGround(true);
			groundY=w.getTopY()-40;
			groundW=w;

		}
		if(checkBottom(TopLeftX, TopLeftY, BotRightX, BotRightY, w)) {//wall on the bottom
			p.setY(w.getBy()+12);p.setVY((int) 0.5*p.getVY());
		}
	}

	private boolean hitL(int tx, int ty, int bx, int by, Laser l) {
		double curx;double cury;double bigx;
		if(l.getX()<l.getOtherX()) {
			curx=l.getX();bigx=l.getOtherX();
			cury=l.getY();
		}
		else {
			curx=l.getOtherX();bigx=l.getX();
			cury=l.getOtherY();
		}
		while(curx<bigx) {
			if(curx>tx&&curx<bx) {
				if(cury<by&&cury>ty) {
					return true;
				}
			}
			curx++;
			cury+=l.getSlope();
		}
		return false;
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
		Shot s1 = new Shot(p.getX()+20,p.getY()+10, x, y, 1500, orange);
		if(s.size() == 0) s.add(0, s1);
		else s.set(0, s1);
	}
	public void resetLevel() {
		p.setX(startX);
		p.setY(startY);
		if(pickUp) pickUp = !pickUp;
		c1.get(0).setX(cStartX);
		c1.get(0).setY(cStartY);
		enemies.get(0).setDead(false);
		enemies.get(1).setDead(false);
		enemies.get(2).setDead(false);
		p1x = 9001;
		p1y = 9001;
		p2x = 9001;
		p2y = 9001;
		p.setVY(0);
		if(s.get(0) != null) s.remove(0);
	}
	public void shootPortal(Wall w, Graphics g) {
		if(w.getExist()) {

			if(s.size()!=0) {
				if(collision((int)s.get(0).getX(), (int)s.get(0).getY(), (int)s.get(0).getX(), (int)s.get(0).getY(), w)) {

					if(!w.getPrePortal()) {
						if(!w.getPortal()) {
							if(s.size()!=0) {
								s.remove(0);
							}
						}
						else {
							if(s.get(0).getOrange()) {
								wallP1 = w.getNum();
								if(checkTop((int)s.get(0).getX(), (int)s.get(0).getY(), (int)s.get(0).getX(), (int)s.get(0).getY(), w)) {
									p1Horizontal = true;
									p1Direction = 0;
									if(s.get(0).getX()-30 < w.getTopX()) p1x = w.getTopX();
									else if(s.get(0).getX()+30 > w.getBx()) p1x = w.getBx()-60;
									else p1x = (int) s.get(0).getX()-30;
									p1y = (int) w.getTopY()-8;

								}
								else if(checkBottom((int)s.get(0).getX(), (int)s.get(0).getY(), (int)s.get(0).getX(), (int)s.get(0).getY(), w)) {
									p1Horizontal = true;
									p1Direction = 1;
									if(s.get(0).getX()-30 < w.getTopX()) p1x = w.getTopX();
									else if(s.get(0).getX()+30 > w.getBx()) p1x = w.getBx()-60;
									else p1x = (int) s.get(0).getX()-30;
									p1y = (int) w.getBy()-8;
								}
								else if(checkLeft((int)s.get(0).getX(), (int)s.get(0).getY(), (int)s.get(0).getX(), (int)s.get(0).getY(), w)){
									p1Horizontal = false;
									p1Direction = 2;
									p1x = (int) w.getBx()-30;
									if(s.get(0).getY()-30 < w.getTopY()) p1y = w.getTopY();
									else if(s.get(0).getY()+25 > w.getBy()) p1y = w.getBy()-55;
									else p1y = (int) s.get(0).getY()-30;
								}
								else if(checkRight((int)s.get(0).getX(), (int)s.get(0).getY(), (int)s.get(0).getX(), (int)s.get(0).getY(), w)){
									p1Horizontal = false;
									p1Direction = 3;
									System.out.println(s.get(0).getY());
									System.out.println(w.getBy());
									p1x = (int) w.getTopX()-30;
									if(s.get(0).getY() -30 < w.getTopY()) p1y = w.getTopY();
									else if(s.get(0).getY()+25 > w.getBy()) p1y = w.getBy()-55;
									else p1y = (int) s.get(0).getY()-30;
								}
								if(s.size() != 0) s.remove(0);


							}
							else {
								wallP2 = w.getNum();
								if(checkTop((int)s.get(0).getX(), (int)s.get(0).getY(), (int)s.get(0).getX(), (int)s.get(0).getY(), w)) {
									p2Horizontal = true;
									p2Direction = 0;
									if(s.get(0).getX()-30 < w.getTopX()) p2x = w.getTopX();
									else if(s.get(0).getX()+30 > w.getBx()) p2x = w.getBx()-60;
									else p2x = (int) s.get(0).getX()-30;
									p2y = (int) w.getTopY()-8;

								}
								else if(checkBottom((int)s.get(0).getX(), (int)s.get(0).getY(), (int)s.get(0).getX(), (int)s.get(0).getY(), w)) {
									p2Horizontal = true;
									p2Direction = 1;
									if(s.get(0).getX()-30 < w.getTopX()) p2x = w.getTopX();
									else if(s.get(0).getX()+30 > w.getBx()) p2x = w.getBx()-60;
									else p2x = (int) s.get(0).getX()-30;
									p2y = (int) w.getBy()-5;
								}
								else if(checkLeft((int)s.get(0).getX(), (int)s.get(0).getY(), (int)s.get(0).getX(), (int)s.get(0).getY(), w)){
									p2Horizontal = false;
									p2Direction = 2;
									p2x = (int) w.getBx();
									p2y = (int) (s.get(0).getY());
									if(s.get(0).getY()-30 < w.getTopY()) p2y = w.getTopY();
									else if(s.get(0).getY()+25 > w.getBy()-5) p2y = w.getBy()-55;
									else p2y = (int) s.get(0).getY()-30;
								}
								else if(checkRight((int)s.get(0).getX(), (int)s.get(0).getY(), (int)s.get(0).getX(), (int)s.get(0).getY(), w)){
									p2Horizontal = false;
									p2Direction = 3;
									p2x = (int) w.getTopX();
									if(s.get(0).getY()-30 < w.getTopY()) p2y = w.getTopY();
									else if(s.get(0).getY()+25 > w.getBy()) p2y = w.getBy()-55;
									else p2y = (int) s.get(0).getY()-30;
								}
								if(s.size() !=0) s.remove(0);
							}
						}
					}
					w.setPrePortal(true);
				}
				else {
					w.setPrePortal(false);
					s.get(0).paint(g);
				}
			}






		
			if(pickUp&&p.getIsLeft()) {
				updateCollision(p.getX()-20, p.getY(), p.getX()+50, p.getY()+40, w);
			}
			else if(pickUp&&!p.getIsLeft()) {
				updateCollision(p.getX(), p.getY(), p.getX()+70, p.getY()+40, w);
			}
			else {
				updateCollision(p.getX(), p.getY(), p.getX()+45, p.getY()+40, w);
			}
			
			
			updateCubeLoc(w);
		}
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
		if(arg0.getKeyCode() == 82) {
			resetLevel();
		}



		if((arg0.getKeyCode() == 32 && closePC(p, c1.get(0)) )|| (pickUp && arg0.getKeyCode() == 32)) {
			pickUp = !pickUp;
		}

	}
	private boolean closePC(Player p, Cube c1) {
		// TODO Auto-generated method stub
		if(p.getX()-20<=c1.getX() && p.getX()+50>=c1.getX()) {
			if(p.getY()-40<=c1.getY() && p.getY()+30>=c1.getY()) {
				return true;
			}
		}
		return false;
	}
	private boolean closePG(Player p, Goal g1) {
		// TODO Auto-generated method stub
		if(p.getX()-40<=g1.getX() && p.getX()+50>=g1.getX()) {
			if(p.getY()-50<=g1.getY() && p.getY()+30>=g1.getY()) {
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
