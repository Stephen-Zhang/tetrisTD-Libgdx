package player;

import java.util.ArrayList;

import towers.TestTower;
import towers.Tower;
import towers.TowerType;

import com.badlogic.gdx.math.Polygon;

public class Player {
	public int gold = 0;
	public int lives;
	public TowerType holding = TowerType.NULL;
	public ArrayList<TowerType> availTowers = new ArrayList<TowerType>();
	
	public int[] currMouseLoc = new int[2];

	public Player(int life) {
		lives = life;

	}
	
	public Player(int life, int gold) {
		this(life);
		this.gold = gold;
	}

	//Additional Stuff for players such as place-able towers, achievements, powerups, etc.
	public void addTower(TowerType t) {
		availTowers.add(t);
	}

	public void removeTower(TowerType t) {
		availTowers.remove(t);
	}
	
	public void removeAllTowers() {
		availTowers.clear();
	}
	
	public void addBatchTowers(TowerType[] tTypes) {
		for (TowerType t: tTypes) {
			addTower(t);
		}
	}

	public Tower makeNewTower() {
		double[] cent = {currMouseLoc[0], currMouseLoc[1]};
		switch(holding) {
		case TEST_TOWER: 
			return new TestTower(cent);
		case NULL: 
			return null;
		}
		return null;
	}

	public int getCostOfTower() {
		// TODO Auto-generated method stub
		switch(holding) {
		case TEST_TOWER:
			return new TestTower().getCost();
		case NULL:
			return 0;
		}
		return 0;
	}
	
	public float[] getTowerShape() {
		// TODO Auto-generated method stub
		switch(holding) {
		case TEST_TOWER:
			return new TestTower().getShape(this.currMouseLoc);
		case NULL:
			return null;
		}
		return null;
	}
}
