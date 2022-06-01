package Portal2D.src;

import java.util.ArrayList;

public class Level5 extends Level {
	
	public void updateCube(ArrayList<Cube> cubes) 
	{
		cubes.set(0, new Cube(10675, 480));
	}
	
	public void updateButton(ArrayList<Button> buttons)
	{
		for(int i = buttons.size()-1;i>=0;i--) {
			buttons.remove(i);
		}
		buttons.add(new Button(11350, 680, 0));
	}
	
	public void updateEnemy(ArrayList<Enemy> enemies)
	{
		for(int i = enemies.size()-1;i>=0;i--) {
			enemies.remove(i);
		}
		
	}
	
	public void updateGoal(Goal goal)
	{
		goal.setX(14000);
		goal.setY(180);
	}
	
	public void updateWalls(ArrayList<Wall> walls)
	{
		for(int i = walls.size()-1;i>2;i--) {
			walls.remove(i);
		}
		walls.add(new Wall(0, 800, 2000, 900, true, 4, false));
	}
	
	public void updatePlayer(Player p)
	{		
		p.setStartY(700);
		p.setStartX(750);
		p.setY(p.getStartY());
		p.setX(p.getStartX());			
	}
}
