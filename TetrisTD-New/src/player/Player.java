package player;

import java.util.ArrayList;
import java.util.HashMap;

import towers.attack.TestAoeTower;
import towers.attack.TestTower;
import towers.base.BaseTower;
import towers.base.TowerType;
import towers.status.AtkSpeedTower;

public class Player {
	public int gold = 0;
	public int lives;
	public TowerType holding = TowerType.NULL;
	public float holdingRotation = 0;
	public ArrayList<TowerType> availTowers = new ArrayList<TowerType>();

	public int[] currMouseLoc = new int[2];
	public boolean canPlaceTower = true;

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

	public BaseTower makeNewTower(int id) {
		switch(holding) {
		case TEST_TOWER: 
			return new TestTower(id, this.currMouseLoc, this.holdingRotation);
		case NULL: 
			return null;
		case TEST_AOE_TOWER:
			return new TestAoeTower(id, this.currMouseLoc, this.holdingRotation);
		case ATK_SPEED_TOWER:
			return new AtkSpeedTower(id, this.currMouseLoc, this.holdingRotation);
		default:
			break;
		}
		return null;
	}

	public int getCostOfTower() {
		// TODO Auto-generated method stub
		switch(holding) {
		case TEST_TOWER:
			return new TestTower(this.holdingRotation).getCost();
		case NULL:
			return 0;
		case TEST_AOE_TOWER:
			return new TestAoeTower(this.holdingRotation).getCost();
		case ATK_SPEED_TOWER:
			return new AtkSpeedTower(this.holdingRotation).getCost();
		default:
			break;
		}
		return 0;
	}
	
	public float[] getTowerShape() {
		// TODO Auto-generated method stub
		switch(holding) {
		case TEST_TOWER:
			return new TestTower(this.holdingRotation).getShape(this.currMouseLoc);
		case NULL:
			return null;
		case TEST_AOE_TOWER:
			return new TestAoeTower(this.holdingRotation).getShape(this.currMouseLoc);
		case ATK_SPEED_TOWER:
			return new AtkSpeedTower(this.holdingRotation).getShape(this.currMouseLoc);
		default:
			break;
		}
		return null;
	}

	public float[] getTowerShapeBody() {
		// TODO Auto-generated method stub
		switch(holding) {
		case TEST_TOWER:
			return new TestTower(this.holdingRotation).getShapeBody(this.currMouseLoc);
		case NULL:
			return null;
		case TEST_AOE_TOWER:
			return new TestAoeTower(this.holdingRotation).getShapeBody(this.currMouseLoc);
		case ATK_SPEED_TOWER:
			return new AtkSpeedTower(this.holdingRotation).getShapeBody(this.currMouseLoc);
		default:
			break;
		}
		return null;
	}
	
	public float[] getTowerRange() {
		// TODO Auto-generated method stub
		switch(holding) {
		case TEST_TOWER:
			return new TestTower(this.holdingRotation).getRange(this.currMouseLoc);
		case NULL:
			return null;
		case TEST_AOE_TOWER:
			return new TestAoeTower(this.holdingRotation).getRange(this.currMouseLoc);
		case ATK_SPEED_TOWER:
			return new AtkSpeedTower(this.holdingRotation).getRange(this.currMouseLoc);
		default:
			break;
		}
		return null;
	}
}
