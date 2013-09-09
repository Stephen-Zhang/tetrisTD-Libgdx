package towers.attack;

import projectiles.Projectile;
import towers.base.BaseTower;
import towers.base.TowerType;
import towers.status.AtkSpeedTower;
import util.TowerStatusType;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectSet;

import enemies.Enemy;

public abstract class AttackTower implements BaseTower{
	protected int id;
	
	protected int key;
	
	protected TowerType towerType;
	
	/**
	 * Attack timing variables:
	 * fireRate value : seconds
	 * cooldown value : seconds
	 * canFire  value : boolean
	 */

	protected float rotation = 0;
	protected double fireRate = 1;
	protected int damage = 10;
	protected boolean canFire = true;
	protected double cooldown = 0;
	
	protected int[] center = new int[2];
	protected int[] gridLocation = null;

	protected int[] iconLoc = new int[2];
	
	protected double atkSpeedBonus = 0.0;
	
	protected Polygon shape;
	protected Polygon range;
	protected float[] shapeBody;
	protected float[] rangeBody;
	protected float[] offset;
	
	protected String name; 
	protected int cost;
	protected String description;
	
	protected String iconPath;
	protected String spritePath;
	
	public DelayedRemovalArray<Enemy> target = new DelayedRemovalArray<Enemy>();
	private ObjectMap<TowerStatusType, ObjectSet<Integer>> buffs = new ObjectMap<TowerStatusType, ObjectSet<Integer>>();
	
	public int[] getCenter() {
		return center;
	}
	
	public double getFireRate() {
		return fireRate/(1+atkSpeedBonus);
	}
	
	public boolean getCanFire() {
		return canFire;
	}
	
	public void setCanFire(boolean bool) {
		this.canFire = bool;
	}

	public double getCooldown() {
		return cooldown;
	}

	public void setCooldown(double cooldown) {
		this.cooldown = cooldown;
	}

	public int[] getGridLocation() {
		return gridLocation;
	}

	public void setGridLocation(int[] gridLocation) {
		this.gridLocation = gridLocation;
	}

	public void setBuffs(ObjectSet<TowerStatusType> newBuffs, int towerId) {
		//For each key, add in tower IDs
		for (TowerStatusType key: newBuffs) {
			ObjectSet<Integer> towerIds;
			if (this.buffs.containsKey(key)) {
				towerIds = this.buffs.get(key);
			} else {
				towerIds = new ObjectSet<Integer>();
			}
			towerIds.add(towerId);
			this.buffs.put(key, towerIds);
		}
	}

	public void buffUpdate(Array<BaseTower> towers) {
		for (TowerStatusType key: this.buffs.keys()) {
			ObjectSet<Integer> towerIds = this.buffs.get(key);
			switch(key) {
			case INCREASE_ATK_SPEED:
				atkSpeedBonus = 0;
				for (Integer i : towerIds) {
					//Iterate over all the towerIds, and grab the tower and grab their attack speed buff.
					AtkSpeedTower tower = (AtkSpeedTower) towers.get(i);
					atkSpeedBonus += tower.getBuffStrength();
				}
				break;
			}
		}
	}
	
	public boolean isBuffTower() {
		return false;
	}

	public int getId() {
		return this.id;
	}

	public void getTowersInRange(Array<BaseTower> array) {
		//Do nothing. Doesn't need to know what towers are in range of it yet.
	}

	public float getRotation() {
		return rotation;
	}

	public int getDamage() {
		return this.damage;
	}
	
	public String getName() {
		return name;
	}

	public String getSpritePath() {
		return spritePath;
	}

	public String getIconPath() {
		return iconPath;
	}

	public int getCost() {
		return cost;
	}
	
	public float[] getShapeBody() {
		return getShapeBody(this.center);
	}

	/**
	 * Takes in mouse location and returns adjusted grid coordinates of shape body
	 * @param mouseLoc
	 * @return
	 */
	public float[] getShapeBody(int[] mouseLoc) {
		//normalize mouse location into grid coordinates here
		int mouseX = mouseLoc[0]/32;
		int mouseY = mouseLoc[1]/32;
		float[] retVal = shapeBody.clone();
		for (int i = 0; i < retVal.length; i += 2) {
			retVal[i] += mouseX;
			retVal[i+1] += mouseY;
		}
		return retVal;
	}
	
	public float[] getRangeBody() {
		return getRangeBody(this.center);
	}

	public float[] getRangeBody(int[] mouseLoc) {
		//normalize mouse location into grid coordinates here
		int mouseX = mouseLoc[0]/32;
		int mouseY = mouseLoc[1]/32;
		float[] retVal = rangeBody.clone();
		for (int i = 0; i < retVal.length; i += 2) {
			retVal[i] += mouseX;
			retVal[i+1] += mouseY;
		}
		return retVal;
	}
	
	@Override
	public float[] getShape(int[] mouseLoc) {
		float[] retVal = shape.getTransformedVertices().clone();
		for (int i = 0; i < retVal.length; i++ ) {
			if (i % 2 == 0) {
				//Even and 0
				retVal[i] += offset[i] + mouseLoc[0];
			} else {
				retVal[i] += offset[i] + mouseLoc[1];
			}
		}
		return retVal;
	}

	@Override
	public float[] getRange(int[] mouseLoc) {
		float[] retVal = range.getTransformedVertices().clone();
		for (int i = 0; i < retVal.length; i++ ) {
			if (i % 2 == 0) {
				//Even and 0
				retVal[i] += offset[i] + mouseLoc[0];
			} else {
				retVal[i] += offset[i] + mouseLoc[1];
			}
		}
		return retVal;
	}

	public String getDescript() {
		return description;
	}
	
	public TowerType getTowerType() {
		// TODO Auto-generated method stub
		return this.towerType;
	}
	
	public abstract void acquireTargets(DelayedRemovalArray<Enemy> enemies);
	public abstract void fire(DelayedRemovalArray<Projectile> bullets);
	
}
