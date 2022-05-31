package Portal2D.src;

import java.util.ArrayList;

public class Level3 extends Level {

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
		enemies.add(new Enemy(380, 520, 500));
		enemies.add(new Enemy(330, 520, 500));
	}
	
	public void updateGoal(Goal goal)
	{
		goal.setX(1000);
		goal.setY(250);		
	}
	
	public void updateWalls(ArrayList<Wall> walls)
	{
		for(int i = walls.size()-1;i>2;i--) {
			walls.remove(i);
		}
		walls.add(new Wall(0, 900, 400, 1000, true, 3, false));
		walls.add(new Wall(400, 750, 1000, 850, true, 4, false));
		walls.add(new Wall(800, 600, 950, 650, true, 5, false));
		walls.add(new Wall(0, 570, 450, 620, true, 6, false));
		walls.add(new Wall(420, 275, 450, 535, false, 7, true));
		walls.add(new Wall(0, 100, 450, 250, true, 8, false));
		walls.add(new Wall(450, 400, 1200, 450, false, 9, false));
		walls.add(new Wall(0, 300, 100, 350, false, 10, false));
		walls.add(new Wall(450, 350, 1200, 400, true, 11, false));
	}
	
	public void updatePlayer(Player player)
	{		
		player.setStartY(869);
		player.setY(player.getStartY());
		player.setX(player.getStartX());			
	}
}
