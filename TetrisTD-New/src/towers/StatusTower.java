package towers;

import util.StatusType;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectSet;

public abstract class StatusTower implements BaseTower {
	protected int id;
	protected ObjectSet<StatusType> buffs = new ObjectSet<StatusType>();

	/**
	 * This is a decimal value. Can be changed otherwise.
	 */
	protected double buffStrengthBase = .20;
	
	protected int[] center;

	protected Array<BaseTower> affectedTowers = new Array<BaseTower>();
	
	protected int[] gridLocation = null;
	
	public void getTowersInRange(Array<BaseTower> towers) {
		//Needs adjustment. for now, hits every tower on map. Need to make changes to the grid location code to fix this issue.
		for (BaseTower t : towers) {
			//This range overlaps a tower's range
			if (/*TODO*/!t.isBuffTower()) {
				this.affectedTowers.add(t);
				((AttackTower) t).setBuffs(this.buffs, this.id);
			}
		}
	}
	
	public int getId() {
		return id;
	}

	public boolean isBuffTower() {
		return true;
	}

	public int[] getCenter() {
		return center;
	}
	
	public int[] getGridLocation() {
		return gridLocation;
	}

	public void setGridLocation(int[] gridLocation) {
		this.gridLocation = gridLocation;
	}

	public double getBuffStrength() {
		return buffStrengthBase / affectedTowers.size;
	}
}
