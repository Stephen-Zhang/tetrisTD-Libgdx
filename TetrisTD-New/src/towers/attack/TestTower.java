package towers.attack;

import projectiles.Projectile;
import projectiles.TestBullet;
import util.utilityFunctions;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;

import enemies.Enemy;

public class TestTower extends AttackTower {
	
	public int key = Input.Keys.T;
	
	private Polygon shape = new Polygon(new float[]{ 
		0, 0, 
		64, 0,
		64, 64,
		32, 64,
		32, 32,
		0, 32,
	});
	
	private float[] shapeBody = new float[] {
		0, 0,
		1, 0,
		1, 1,
	};
	
	private float[] rangeBody = new float[] {
		-1, -1,
		-1, 0,
		-1, 1,
		0, 1,
		0, 2,
		1, 2,
		2, 2,
		2, 1,
		2, 0,
		2, -1,
		1, -1,
		0, -1,
	};
	
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
	private int gold = 10;
	private String description = "This tower is a test tower for single targets. Basic without numbers tuned";
	
	private String iconPath = "towers/icons/testTowerIcon.png";
	private String spritePath = "towers/sprites/testTowerSprite.png";

	public TestTower() {
		fireRate = 1;
		damage = 10;
		center = new int[]{0,0};
	}
	
	public TestTower(float rotation) {
		super();
		
		this.rotation = rotation;
		shape.setOrigin(16, 16);
		range.setOrigin(16, 16);
		shape.rotate(rotation);
		range.rotate(rotation);

		Polygon tempShapeBody = new Polygon(shapeBody);
		tempShapeBody.rotate(rotation);
		shapeBody = tempShapeBody.getTransformedVertices();

		Polygon tempRangeBody = new Polygon(rangeBody);
		tempRangeBody.rotate(rotation);
		rangeBody = tempRangeBody.getTransformedVertices();

		Polygon tempOffset = new Polygon(offset);
		tempOffset.rotate(rotation);
		offset = tempOffset.getTransformedVertices();
	}
	
	public TestTower(int id, int[] cent, float rotation) {
		super();
		this.id = id;
		center = cent;
		
		this.rotation = rotation;
		
		//Rotate the damn shapeBody
		shape.setOrigin(16, 16);
		range.setOrigin(16, 16);
		shape.rotate(rotation);
		range.rotate(rotation);
		Polygon tempShapeBody = new Polygon(shapeBody);
		tempShapeBody.rotate(rotation);
		shapeBody = tempShapeBody.getTransformedVertices();

		Polygon tempRangeBody = new Polygon(rangeBody);
		tempRangeBody.rotate(rotation);
		rangeBody = tempRangeBody.getTransformedVertices();

		Polygon tempOffset = new Polygon(offset);
		tempOffset.rotate(rotation);
		offset = tempOffset.getTransformedVertices();

	}
	
	public String getName() {
		return name;
	}

	public String getSpritePath() {
		return spritePath;
	}

	public String getIconPath() {
		return iconPath;
	}

	public int getCost() {
		return gold;
	}
	
	public float[] getShapeBody() {
		return getShapeBody(this.center);
	}
	
	/**
	 * Takes in mouse location and returns adjusted grid coordinates of shape body
	 * @param mouseLoc
	 * @return
	 */
	public float[] getShapeBody(int[] mouseLoc) {
		//normalize mouse location into grid coordinates here
		int mouseX = mouseLoc[0]/32;
		int mouseY = mouseLoc[1]/32;
		float[] retVal = shapeBody.clone();
		for (int i = 0; i < retVal.length; i += 2) {
			retVal[i] += mouseX;
			retVal[i+1] += mouseY;
		}
		return retVal;
	}
	
	public float[] getRangeBody() {
		return getRangeBody(this.center);
	}

	public float[] getRangeBody(int[] mouseLoc) {
		//normalize mouse location into grid coordinates here
		int mouseX = mouseLoc[0]/32;
		int mouseY = mouseLoc[1]/32;
		float[] retVal = rangeBody.clone();
		for (int i = 0; i < retVal.length; i += 2) {
			retVal[i] += mouseX;
			retVal[i+1] += mouseY;
		}
		return retVal;
	}
	
	@Override
	public float[] getShape(int[] mouseLoc) {
		float[] retVal = shape.getTransformedVertices().clone();
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
		float[] retVal = range.getTransformedVertices().clone();
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

	public String getDescript() {
		return description;
	}

	public void acquireTargets(DelayedRemovalArray<Enemy> enemies) {
		//Lose old targets
		if (this.target.size > 0 && !Intersector.overlapConvexPolygons(this.target.get(0).getHitbox(), this.range)) {
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

	public void fire(DelayedRemovalArray<Projectile> bullets) {
		for (Enemy e : this.target) {
			bullets.add(new TestBullet(e, this.damage, this.center.clone()));
		}
	}

}
