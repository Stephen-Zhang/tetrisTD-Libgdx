package towers.status;

import java.util.HashSet;

import towers.attack.AttackTower;
import towers.base.BaseTower;
import towers.base.TowerType;
import util.StatusType;
import util.utilityFunctions;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectSet;

public abstract class StatusTower implements BaseTower {
	protected int id;
	protected ObjectSet<StatusType> buffs = new ObjectSet<StatusType>();
	protected int key;
	protected TowerType towerType;

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
	
	/**
	 * This is a decimal value.
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
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getSpritePath() {
		return spritePath;
	}

	@Override
	public String getIconPath() {
		return iconPath;
	}

	@Override
	public int getCost() {
		return this.cost;
	}

	@Override
	public float[] getShape(int[] mouseLoc) {
		float[] retVal = shape.getTransformedVertices().clone();
		for (int i = 0; i < retVal.length; i++ ) {
			if (i % 2 == 0) {
				//Even and 0
				retVal[i] += mouseLoc[0];
			} else {
				retVal[i] += mouseLoc[1];
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
				retVal[i] += mouseLoc[0];
			} else {
				retVal[i] += mouseLoc[1];
			}
		}
		return retVal;
	}

	@Override
	public String getDescript() {
		return "Increases attack speed by "+this.buffStrengthBase*100+"%!";
	}

	public float[] getShapeBody() {
		return getShapeBody(this.center);
	}

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
	
	public TowerType getTowerType() {
		// TODO Auto-generated method stub
		return this.towerType;
	}
	

}
