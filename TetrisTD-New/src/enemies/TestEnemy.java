package enemies;

import java.awt.Point;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Array;

public class TestEnemy extends Enemy {
	
	public TestEnemy() {		
		this.maxHealth = 20;
		this.currHealth = 20;
		this.bounty = 10;
		this.walkSpeed = 1;
		this.name = "TestEnemy";
		this.spritePath = "enemies/TestEnemy.png";
	}
	
	public TestEnemy(double[] startingLoc, Array<Point>waypts) {
		this();
		this.pos = startingLoc;
		this.waypoints = waypts;
		getNextDest();
	}

}
