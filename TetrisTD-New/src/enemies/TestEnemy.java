package enemies;

import java.awt.Point;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Array;

public class TestEnemy extends Enemy {

	private String spritePath = "enemies/TestEnemy.png";
	
	public TestEnemy() {
		
	}
	
	public TestEnemy(double[] startingLoc, Array<Point>waypts) {
		this.maxHealth = 20;
		this.currHealth = 20;
		this.pos = startingLoc;
		this.bounty = 10;
		this.waypoints = waypts;
		this.walkSpeed = 1;
		getNextDest();
		this.name = "TestEnemy";
	}

	@Override
	public Polygon getHitbox() {
		float[] retVal = new float[]{
			0, 0,
			0, 32,
			32, 32,
			32, 0
		};
		for (int i = 0; i < retVal.length; i++) {
			if (i % 2 == 0) {
				retVal[i] += pos[0];
			} else {
				retVal[i] += pos[1];
			}
		}
		return new Polygon(retVal);
	}

	@Override
	public String getSpritePath() {
		// TODO Auto-generated method stub
		return this.spritePath;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}
}
