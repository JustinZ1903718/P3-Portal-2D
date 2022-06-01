package Portal2D.src;

import java.util.ArrayList;
//level classes store the information of the classes for the level
public class Level1 extends Level{

	public void updateCube(ArrayList<Cube> cubes) 
	{
		cubes.set(0, new Cube(350, 80));
	}
	
	public void updateButton(ArrayList<Button> buttons)
	{
		for(int i = buttons.size()-1;i>=0;i--) {
			buttons.remove(i);
		}
		buttons.add(new Button(450, 380, 6));
	}
	
	public void updateEnemy(ArrayList<Enemy> enemies)
	{
		for(int i = enemies.size()-1;i>=0;i--) {
			enemies.remove(i);
		}
		enemies.add(new Enemy(700, 150, 500));
		enemies.add(new Enemy(900, 150, 500));
		enemies.add(new Enemy(500, 350, 500));
	}
	
	public void updateGoal(Goal goal)
	{
		goal.setX(750);
		goal.setY(110);
	}
	
	public void updateWalls(ArrayList<Wall> walls)
	{
		for(int i = walls.size()-1;i>2;i--) {
			walls.remove(i);
		}
		walls.add(new Wall(200, 400, 1000, 500, true, 3, false));
		walls.add(new Wall(0, 200, 250, 400, true, 4, false));
		walls.add(new Wall(500, 200, 1000, 325, true, 5, false));
		walls.add(new Wall(600, 0, 650, 400, false, 6, true));
	}
	
	public void updatePlayer(Player player)
	{
		player.setY(170);
		player.reset();
		player.setStartY(69);
		player.setStartX(69);
		player.setY(player.getStartY());
		player.setX(player.getStartX());
	}
}
