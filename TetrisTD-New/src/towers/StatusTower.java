package towers;

import util.StatusType;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.ObjectSet;

public abstract class StatusTower extends Tower{
	protected StatusType buff;
	protected int[] center;
	protected Polygon shape;
	protected Polygon range;
	
	public Texture sprite;

	protected Array<Tower> affectedTowers;
	
	public int[] gridLocation;
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
	
	@Override
	public void getTowersInRange(Array<Tower> towers) {
		for (Tower t : towers) {
			float[] towerShapeVert = t.getShape(t.getCenter());
			//This range overlaps a tower's range
			if (/*TODO*/true) {
				ObjectSet<StatusType> newBuffs = t.getBuffs();
				newBuffs.add(this.buff);
				t.setBuffs(newBuffs);
			}
		}
	}
	
	@Override
	public boolean isBuffTower() {
		return true;
	}
}
