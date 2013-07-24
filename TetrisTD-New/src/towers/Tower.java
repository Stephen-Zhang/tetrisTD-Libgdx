package towers;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.math.Polygon;

import enemies.Enemy;

public abstract class Tower {

	public double fireRate = 1;
	public boolean canFire = true;
	public int cooldown = 0;
	public int[] iconLoc = new int[2];
	
	protected double[] center = new double[2];
	
	protected static float[] shape;
	protected static float[] range;
	
	public ArrayList<Enemy> target = new ArrayList<Enemy>();
	
	public abstract String getName();

	public abstract String getSpritePath();
	public abstract String getIconPath();
	
	public abstract int getCost();
	public abstract float[] getShape(int[] mouseLoc);
	public abstract float[] getRange(int[] mouseLoc);
	public abstract String getDescript();
	
	public abstract void acquireTargets(ArrayList<Enemy> enemies, HashMap<Enemy, ArrayList<Tower>> addTtoE, HashMap<Enemy, ArrayList<Tower>> remTfromE, HashMap<Tower, ArrayList<Enemy>> addEtoT, HashMap<Tower, ArrayList<Enemy>> remEfromT);

	//public abstract void fire(ArrayList<Projectile> addB);

}
