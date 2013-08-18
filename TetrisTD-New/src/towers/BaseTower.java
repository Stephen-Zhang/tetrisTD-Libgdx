package towers;

import com.badlogic.gdx.utils.Array;

public interface BaseTower {
	public String getName();
	public String getDescript();

	public String getSpritePath();
	public String getIconPath();
	
	public int[] getCenter();
	
	public int getCost();

	public float[] getShape(int[] mouseLoc);
	public float[] getRange(int[] mouseLoc);

	public float[] getShapeBody();
	public float[] getShapeBody(int[] mouseLoc);
	
	public int[] getGridLocation();
	public void setGridLocation(int[] gridLocs);
	
	public boolean isBuffTower();
	
	public void getTowersInRange(Array<BaseTower> array);
}
