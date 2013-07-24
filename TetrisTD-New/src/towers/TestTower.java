package towers;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Polygon;

import enemies.Enemy;

public class TestTower extends Tower {
	
	public int key = Input.Keys.T;
	
	
	private float[] shape =new float[]{ 
		0, 0, 
		32, 0,
		64, 0,
		64, 32,
		64, 64,
		32, 64,
		32, 32,
		0, 32,
	};

	
	private String name = "Test Tower";
	private int gold = 100;
	private String description = "This tower is a test tower for single targets.";
	
	private String iconPath = "towers/testTower.png";
	private String spritePath = "towers/testTower.png";

	public TestTower() {
		center = new double[]{0,0};
	}
	
	public TestTower(double[] cent) {
		fireRate = 1;
		
	}
	
	@Override
	public String getName() {
		return name;
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
		return gold;
	}

	@Override
	public float[] getShape(int[] mouseLoc) {
		float[] retVal = shape.clone();
		for (int i = 0; i < retVal.length; i++ ) {
			if (i % 2 == 0) {
				//Even and 0
				retVal[i] += mouseLoc[0];
			} else {
				retVal[i] = retVal[i] + mouseLoc[1];
			}
		}
		return retVal;
	}

	@Override
	public Polygon getRange() {
		return null;
	}

	@Override
	public String getDescript() {
		return description;
	}

	@Override
	public void acquireTargets(ArrayList<Enemy> enemies,
			HashMap<Enemy, ArrayList<Tower>> addTtoE,
			HashMap<Enemy, ArrayList<Tower>> remTfromE,
			HashMap<Tower, ArrayList<Enemy>> addEtoT,
			HashMap<Tower, ArrayList<Enemy>> remEfromT) {
		// TODO Auto-generated method stub
		
	}

}
