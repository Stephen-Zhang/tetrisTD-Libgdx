package towers.status;

import java.util.HashSet;

import towers.attack.AttackTower;
import towers.base.BaseTower;
import util.StatusType;
import util.utilityFunctions;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectSet;

public abstract class StatusTower implements BaseTower {
	protected int id;
	protected ObjectSet<StatusType> buffs = new ObjectSet<StatusType>();

	/**
	 * This is a decimal value. Can be changed otherwise.
	 */
	protected double buffStrengthBase = .20;
	
	protected float rotation = 0;
	protected int[] center;

	protected Array<BaseTower> affectedTowers = new Array<BaseTower>();
	
	protected int[] gridLocation = null;
	
	public void getTowersInRange(Array<BaseTower> towers) {

		HashSet<Integer> flattenedRange = new HashSet<Integer>();
		
		for (int index : utilityFunctions.flattenShape(this.getRangeBody(), 24)) {
			flattenedRange.add(new Integer(index));
		}

		for (BaseTower t : towers) {
			if (!t.isBuffTower()) {
				//This range overlaps a tower's range
				for (int t_index : t.getGridLocation()) {
					if (flattenedRange.contains(new Integer(t_index))) {
						this.affectedTowers.add(t);
						((AttackTower) t).setBuffs(this.buffs, this.id);
					}
				}
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

	public float getRotation() {
		return rotation;
	}
	
}
