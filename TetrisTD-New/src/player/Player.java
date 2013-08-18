package player;

import java.util.ArrayList;

import towers.attack.TestTower;
import towers.base.BaseTower;
import towers.base.TowerType;
import towers.status.AtkSpeedTower;

public class Player {
	public int gold = 0;
	public int lives;
	public TowerType holding = TowerType.NULL;
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
			return new TestTower(id, this.currMouseLoc);
		case NULL: 
			return null;
		case TEST_AOE_TOWER:
			break;
		case ATK_SPEED_TOWER:
			return new AtkSpeedTower(id, this.currMouseLoc);
		default:
			break;
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
		case TEST_AOE_TOWER:
			break;
		case ATK_SPEED_TOWER:
			return new AtkSpeedTower().getCost();
		default:
			break;
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
		case TEST_AOE_TOWER:
			break;
		case ATK_SPEED_TOWER:
			return new AtkSpeedTower().getShape(this.currMouseLoc);
		default:
			break;
		}
		return null;
	}

	public float[] getTowerShapeBody() {
		// TODO Auto-generated method stub
		switch(holding) {
		case TEST_TOWER:
			return new TestTower().getShapeBody(this.currMouseLoc);
		case NULL:
			return null;
		case TEST_AOE_TOWER:
			break;
		case ATK_SPEED_TOWER:
			return new AtkSpeedTower().getShapeBody(this.currMouseLoc);
		default:
			break;
		}
		return null;
	}
	
	public float[] getTowerRange() {
		// TODO Auto-generated method stub
		switch(holding) {
		case TEST_TOWER:
			return new TestTower().getRange(this.currMouseLoc);
		case NULL:
			return null;
		case TEST_AOE_TOWER:
			break;
		case ATK_SPEED_TOWER:
			return new AtkSpeedTower().getRange(this.currMouseLoc);
		default:
			break;
		}
		return null;
	}
}
