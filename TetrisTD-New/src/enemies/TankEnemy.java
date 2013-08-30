package enemies;

import java.awt.Point;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Array;

public class TankEnemy extends Enemy {

	private String spritePath = "enemies/TankEnemy.png";
	
	public TankEnemy() {
		
	}
	
	public TankEnemy(double[] startingLoc, Array<Point>waypts) {
		this.maxHealth = 50;
		this.currHealth = 20;
		this.pos = startingLoc;
		this.bounty = 10;
		this.waypoints = waypts;
		this.walkSpeed = .3;
		getNextDest();
		this.name = "TankEnemy";
		
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
