package towers;

import java.util.Set;

import projectiles.Projectile;
import util.StatusType;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.ObjectSet;

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
	public int[] gridLocation = null;//Temporary placeholder while i fill it
	public Texture sprite;
	
	public abstract String getName();

	public abstract String getSpritePath();
	public abstract String getIconPath();
	
	public abstract int getCost();
	public abstract float[] getShape(int[] mouseLoc);
	public abstract float[] getShapeBody();
	public abstract float[] getShapeBody(int[] mouseLoc);
	public abstract float[] getRange(int[] mouseLoc);
	public abstract String getDescript();
	
	private ObjectSet<StatusType> buffs = new ObjectSet<StatusType>();
	
	public int[] getCenter() {
		return center;
	}

	public void setCenter(int[] center) {
		this.center = center;
	}

	public ObjectSet<StatusType> getBuffs() {
		return buffs;
	}

	public void setBuffs(ObjectSet<StatusType> buffs) {
		this.buffs = buffs;
	}

	public abstract void acquireTargets(DelayedRemovalArray<Enemy> enemies);

	public abstract void fire(DelayedRemovalArray<Projectile> bullets);
	
	public void applyBuffs() {
		for (StatusType st: buffs) {
			switch(st) {
			//ATTACK SPEED INCREASE BY 15%
			case INCREASE_ATK_SPEED:
				this.fireRate /= 1.15;
				break;
			}
		}
	}
	
	public boolean isBuffTower() {
		return false;
	}
	
	public void getTowersInRange(Array<Tower> array) {
		
	}
}
