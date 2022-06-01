package Portal2D.src;

import java.util.ArrayList;

public class LevelManager {
//levelManager is a class that updates the level based on the current level
	private Level currentLevel = new Level1();
	public void updateLevel(ArrayList<Wall> walls, ArrayList<Enemy> enemies, 
			ArrayList<Cube> cubes, Goal goal, Player player, ArrayList<Button> buttons,
			int level) 
	{
		switch(level)
		{
			case 1: currentLevel = new Level1();
			break;

			case 2: currentLevel = new Level2();
			break;
			
			case 3: currentLevel = new Level3();
			break;
			
			case 4: currentLevel = new Level4();
			break;
			
			case 5: currentLevel = new Level5();
			break;
		}
		
		currentLevel.updateCube(cubes);
		currentLevel.updateButton(buttons);
		currentLevel.updateEnemy(enemies);
		currentLevel.updateGoal(goal);
		currentLevel.updateWalls(walls);
		currentLevel.updatePlayer(player);			
	}
}
