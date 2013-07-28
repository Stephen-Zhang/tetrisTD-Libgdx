package towers;

import projectiles.Projectile;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.DelayedRemovalArray;

import enemies.Enemy;

public abstract class Tower {

	public double fireRate = 1;
	public boolean canFire = true;
	public int cooldown = 0;
	public int[] iconLoc = new int[2];
	
	protected int[] center = new int[2];
	
	protected static float[] shape;
	protected static float[] range;
	
	public DelayedRemovalArray<Enemy> target = new DelayedRemovalArray<Enemy>();
	public Texture sprite;
	
	public abstract String getName();

	public abstract String getSpritePath();
	public abstract String getIconPath();
	
	public abstract int getCost();
	public abstract float[] getShape(int[] mouseLoc);
	public abstract float[] getRange(int[] mouseLoc);
	public abstract String getDescript();
	
	public int[] getCenter() {
		return center;
	}

	public void setCenter(int[] center) {
		this.center = center;
	}

	public abstract void acquireTargets(DelayedRemovalArray<Enemy> enemies);

	public abstract void fire(DelayedRemovalArray<Projectile> bullets);

}
