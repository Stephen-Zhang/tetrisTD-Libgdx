package enemies;

import java.awt.Point;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class TestEnemy extends Enemy {

	public TestEnemy(double[] startingLoc, Array<Point>waypts) {
		this.maxHealth = 20;
		this.currHealth = 20;
		this.pos = startingLoc;
		this.bounty = 10;
		this.waypoints = waypts;
		
		getNextDest();
		
		this.sprite = new Texture("enemies/TestEnemy.png");
	}
}
