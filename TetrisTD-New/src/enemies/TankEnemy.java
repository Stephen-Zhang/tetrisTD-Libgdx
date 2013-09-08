package enemies;

import java.awt.Point;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Array;

public class TankEnemy extends Enemy {

	public TankEnemy() {
		this.maxHealth = 50;
		this.currHealth = 50;
		this.bounty = 10;
		this.walkSpeed = .7;
		this.name = "TankEnemy";
		this.spritePath = "enemies/TankEnemy.png";
	}
	
	public TankEnemy(double[] startingLoc, Array<Point>waypts) {
		this();
		this.pos = startingLoc;
		this.waypoints = waypts;
		getNextDest();
	}

}
