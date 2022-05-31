package Portal2D.src;

import java.util.ArrayList;

public class Level4 extends Level {
	
	public void updateCube(ArrayList<Cube> cubes) 
	{
		cubes.set(0, new Cube(675, 480));
	}
	
	public void updateButton(ArrayList<Button> buttons)
	{
		for(int i = buttons.size()-1;i>=0;i--) {
			buttons.remove(i);
		}
		buttons.add(new Button(1350, 680, 10));
	}
	
	public void updateEnemy(ArrayList<Enemy> enemies)
	{
		for(int i = enemies.size()-1;i>=0;i--) {
			enemies.remove(i);
		}
		enemies.add(new Enemy(1200, 650, 500));
		enemies.add(new Enemy(1250, 650, 500));
	}
	
	public void updateGoal(Goal goal)
	{
		goal.setX(1400);
		goal.setY(180);
	}
	
	public void updateWalls(ArrayList<Wall> walls)
	{
		for(int i = walls.size()-1;i>2;i--) {
			walls.remove(i);
		}
		walls.add(new Wall(0, 900, 350, 1000, true, 3, false));
		walls.add(new Wall(500, 700, 800, 800, true, 4, false));
		walls.add(new Wall(1000, 700, 1500, 800, true, 5, false));
		walls.add(new Wall(650, 520, 1500, 570, false, 6, false));
		walls.add(new Wall(650, 570, 1500, 620, true, 7, false));

		walls.add(new Wall(750, 450, 850, 490, false, 8, false));
		walls.add(new Wall(850, 390, 950, 430, false, 9, false));
		walls.add(new Wall(950, 320, 1050, 360, false, 10, true));
		walls.add(new Wall(1100, 320, 1300, 360, false, 11, false));
		walls.add(new Wall(1300, 320, 1500, 360, true, 12, false));
		walls.add(new Wall(1300, 300, 1330, 320, false, 12, false));
	}
	
	public void updatePlayer(Player player)
	{		
		player.setStartY(869);
		player.setY(player.getStartY());
		player.setX(player.getStartX());			
	}}
