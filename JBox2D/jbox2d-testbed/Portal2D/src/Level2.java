package Portal2D.src;

import java.util.ArrayList;

public class Level2 extends Level {

	public void updateCube(ArrayList<Cube> cubes) 
	{
		cubes.set(0, new Cube(150, 420));
	}
	
	public void updateButton(ArrayList<Button> buttons)
	{
		for(int i = buttons.size()-1;i>=0;i--) {
			buttons.remove(i);
		}
		buttons.add(new Button(40, 280, 7));		
	}
	
	public void updateEnemy(ArrayList<Enemy> enemies)
	{
		for(int i = enemies.size()-1;i>=0;i--) {
			enemies.remove(i);
		}
		enemies.add(new Enemy(400, 450, 500));
		enemies.add(new Enemy(450, 450, 500));
		enemies.add(new Enemy(500, 450, 500));
		enemies.add(new Enemy(550, 450, 500));
		enemies.add(new Enemy(600, 450, 500));
		enemies.add(new Enemy(650, 450, 500));
		enemies.add(new Enemy(700, 450, 500));
		enemies.add(new Enemy(750, 450, 500));
	}
	
	public void updateGoal(Goal goal)
	{
		goal.setX(1000);
		goal.setY(400);
	}
	
	public void updateWalls(ArrayList<Wall> walls)
	{
		for(int i = walls.size()-1;i>2;i--) {
			walls.remove(i);
		}
		walls.add(new Wall(0, 900, 200, 1000, true, 3, false));
		walls.add(new Wall(200, 750, 400, 850, true, 4, false));
		walls.add(new Wall(100, 540, 200, 580, false, 5, false));
		walls.add(new Wall(400, 500, 1500, 550, true, 6, false));
		walls.add(new Wall(800, 200, 820, 500, false, 7, true));
		walls.add(new Wall(0, 300, 220, 400, true, 8, false));
	}
	
	public void updatePlayer(Player player)
	{		
		player.setStartY(869);
		player.setY(player.getStartY());
		player.setX(player.getStartX());	
	}
}
