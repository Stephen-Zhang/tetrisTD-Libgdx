package towers.attack;

import projectiles.Projectile;
import towers.base.BaseTower;
import towers.status.AtkSpeedTower;
import util.StatusType;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectSet;

import enemies.Enemy;

public abstract class AttackTower implements BaseTower{
	protected int id;
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
	protected int[] gridLocation = null;//Temporary placeholder while i fill it

	protected int[] iconLoc = new int[2];
	
	protected double atkSpeedBonus = 0.0;
	
	public DelayedRemovalArray<Enemy> target = new DelayedRemovalArray<Enemy>();
	private ObjectMap<StatusType, ObjectSet<Integer>> buffs = new ObjectMap<StatusType, ObjectSet<Integer>>();
	
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

	public void setBuffs(ObjectSet<StatusType> newBuffs, int towerId) {
		//For each key, add in tower IDs
		for (StatusType key: newBuffs) {
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
		for (StatusType key: this.buffs.keys()) {
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
	
	public abstract void acquireTargets(DelayedRemovalArray<Enemy> enemies);
	public abstract void fire(DelayedRemovalArray<Projectile> bullets);

	
}
