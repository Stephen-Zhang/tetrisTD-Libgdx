package towers.base;

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

	public float[] getRangeBody();
	public float[] getRangeBody(int[] mouseLoc);
	
	public int[] getGridLocation();
	public void setGridLocation(int[] gridLocs);
	
	public int getId();
	
	public boolean isBuffTower();
	
	public float getRotation();
	public void getTowersInRange(Array<BaseTower> array);
}
