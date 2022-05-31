package Portal2D.src;

import java.util.ArrayList;

public abstract class Level {
	public abstract void updateCube(ArrayList<Cube> cubes);
	public abstract void updateButton(ArrayList<Button> buttons);
	public abstract void updateEnemy(ArrayList<Enemy> enemies);
	public abstract void updateGoal(Goal goal);
	public abstract void updateWalls(ArrayList<Wall> walls);
	public abstract void updatePlayer(Player player);
}
