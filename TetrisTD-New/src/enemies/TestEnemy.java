package enemies;

import java.awt.Point;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Array;

public class TestEnemy extends Enemy {

	public TestEnemy(int[] startingLoc, Array<Point>waypts) {
		this.maxHealth = 20;
		this.currHealth = 20;
		this.pos = startingLoc;
		this.bounty = 10;
		this.waypoints = waypts;
		
		getNextDest();
		
		this.sprite = new Texture("enemies/TestEnemy.png");
		
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
}
