package towers;

import projectiles.Projectile;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.DelayedRemovalArray;

import enemies.Enemy;

public class TestTower extends Tower {
	
	public int key = Input.Keys.T;
	
	private Polygon shape = new Polygon(new float[]{ 
		0, 0, 
		64, 0,
		64, 64,
		32, 64,
		32, 32,
		0, 32,
	});
	
	private float[] offset = new float[] {
		1, 1,
		-1, 1,
		-1, -1,
		1, -1,
		1, -1,
		1, -1,
	};

	private Polygon range = new Polygon(new float[]{
		-32, -32,
		96, -32,
		96, 96,
		0, 96,
		0, 64,
		-32, 64,
	});
	
	private String name = "Test Tower";
	private int gold = 100;
	private String description = "This tower is a test tower for single targets.";
	
	private String iconPath = "towers/testTower.png";
	private String spritePath = "towers/testTower.png";

	public TestTower() {
		fireRate = 1;
		center = new int[]{0,0};
	}
	
	public TestTower(int[] cent) {
		super();
		center = cent;
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
		float[] retVal = shape.getVertices().clone();
		for (int i = 0; i < retVal.length; i++ ) {
			if (i % 2 == 0) {
				//Even and 0
				retVal[i] += offset[i] + mouseLoc[0];
			} else {
				retVal[i] += offset[i] + mouseLoc[1];
			}
		}
		return retVal;
	}

	@Override
	public float[] getRange(int[] mouseLoc) {
		float[] retVal = range.getVertices().clone();
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
	public String getDescript() {
		return description;
	}

	@Override
	public void acquireTargets(DelayedRemovalArray<Enemy> enemies) {
		//Lose old targets
		if (this.target.size > 0 && Intersector.overlapConvexPolygons(this.target.get(0).getHitbox(), this.range)) {
			this.target.get(0).towersAttacking.removeValue(this, false);
			this.target.removeIndex(0);
		}
		//Acquire new targets
		for (Enemy e : enemies) {
			if (Intersector.overlapConvexPolygons(e.getHitbox(), new Polygon(this.getRange(this.center))) ) {
				//Intersects! Now to determine closest to goal enemy
				//No target yet...
				if (this.target.size == 0) {
					this.target.add(e);
					e.towersAttacking.add(this);
				} else {
					if (e.distToGoal() < this.target.get(0).distToGoal()) {
						this.target.get(0).towersAttacking.removeValue(this, false);
						this.target.set(0, e);
						e.towersAttacking.add(this);
					}
				}
			}
		}
	}

	@Override
	public void fire(DelayedRemovalArray<Projectile> bullets) {
		for (Enemy e : this.target) {
			bullets.add(new TestBullet(e, this.center.clone()));
		}
	}

}
