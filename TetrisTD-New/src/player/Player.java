package player;

import java.util.ArrayList;
import java.util.HashMap;

import towers.attack.TestAoeTower;
import towers.attack.TestTower;
import towers.base.BaseTower;
import towers.base.TowerType;
import towers.status.AtkSpeedTower;

import com.badlogic.gdx.math.Polygon;

public class Player {
	public int gold = 0;
	public int lives;
	public TowerType holding = TowerType.NULL;
	
	public float[] holdingShapeBody = null;
	public Polygon holdingShape = null;
	public float[] holdingRange = null;
	public Polygon holdingRangeBody = null;
	
	public float holdingRotation = 0;
	
	public ArrayList<TowerType> availTowers = new ArrayList<TowerType>();

	public HashMap<float[], Polygon> threeSqShapes = new HashMap<float[], Polygon>();
	public HashMap<float[], Polygon> fourSqShapes = new HashMap<float[], Polygon>();
	public HashMap<float[], Polygon> fiveSqShapes = new HashMap<float[], Polygon>();
	
	public int[] currMouseLoc = new int[2];
	public boolean canPlaceTower = true;

	public Player(int life) {
		lives = life;
		
		populateThrees();
		populateFours();
		populateFives();
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
	
	public void populateThrees() {
		//Straight
		float[] straightBody = new float[]{
			0, 0,
			0, 1,
			0, 2
		};
		Polygon straight = new Polygon(new float[]{
			0, 0,
			32, 0,
			32, 96,
			0, 96
		});
		
		//Bent
		float[] bentBody = new float[]{
			0, 0,
			1, 0,
			0, 1
		};		
		Polygon bent = new Polygon(new float[]{
				0, 0, 
				64, 0,
				64, 64,
				32, 64,
				32, 32,
				0, 32,
		});
		
		threeSqShapes.put(straightBody, straight);
		threeSqShapes.put(bentBody, bent);
	}
	
	public void populateFours() {
		//Straight
		float[] straightBody = new float[] {
			0, 0,
			0, 1,
			0, 2,
			0, 3
		};
		Polygon straight = new Polygon(new float[]{
			0, 0,
			32, 0,
			32, 128,
			0, 128
		});
		
		//Square
		float[] squareBody = new float[] {
			0, 0,
			0, 1,
			1, 0,
			1, 1
		};
		Polygon square = new Polygon(new float[]{
			0, 0,
			0, 64,
			64, 64,
			64, 0
		});
		
		//T-Shape
		float[] tBody = new float[] {
			0, 0,
			1, 0,
			1, 1,
			2, 0
		};
		Polygon t = new Polygon(new float[]{
			0, 0,
			0, 32,
			32, 32,
			32, 64,
			64, 64,
			64, 32,
			96, 32,
			96, 0
		});
		
		//L shape
		float[] lBody = new float[] {
			0, 0,
			0, 1,
			0, 2,
			1, 0
		};
		Polygon l = new Polygon(new float[] {
			0, 0,
			0, 96,
			32, 96,
			32, 32,
			64, 32,
			64, 0
		});
		
		//Reverse L shape
		float[] revLBody = new float[] {
			0, 0,
			1, 0,
			1, 1,
			1, 2
		};
		Polygon revL = new Polygon(new float[] {
			0, 0,
			0, 32,
			32, 32,
			32, 96,
			96, 64,
			96, 0
		});
		
		//Z shape
		float[] zBody = new float[] {
			0, 0,
			0, 1,
			1, 1,
			1, 2
		};
		Polygon z = new Polygon(new float[] {
			0, 0,
			0, 64,
			32, 64,
			32, 96,
			64, 96,
			64, 32,
			32, 32,
			32, 0			
		});
		
		//S shape
		float[] sBody = new float[] {
			0, 0,
			1, 0,
			1, 1,
			2, 1
		};
		Polygon s = new Polygon(new float[] {
			0, 0,
			0, 32,
			32, 32,
			32, 64,
			96, 64,
			96, 32,
			64, 32,
			64, 0
		});
		
		fourSqShapes.put(sBody, s);
		fourSqShapes.put(zBody, z);
		fourSqShapes.put(lBody, l);
		fourSqShapes.put(revLBody, revL);
		fourSqShapes.put(straightBody, straight);
		fourSqShapes.put(squareBody, square);
		fourSqShapes.put(tBody, t);
	}
	
	public void populateFives() {
		
	}
}
