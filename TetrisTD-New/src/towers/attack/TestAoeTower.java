package towers.attack;

import projectiles.Projectile;
import projectiles.TestBullet;
import towers.base.TowerType;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.DelayedRemovalArray;

import enemies.Enemy;

public class TestAoeTower extends AttackTower {

public int key = Input.Keys.R;
	
	private Polygon shape = new Polygon(new float[]{ 
		0, 0,
		32, 0,
		32, 32, 
		96, 32,
		96, 64,
		32, 64,
		32, 96,
		0, 96
	});
	
	private float[] shapeBody = new float[] {
		0, 0,
		0, 1,
		0, 2,
		1, 1,
		2, 1,
		
	};

	private Polygon range = new Polygon(new float[]{
		-32 ,-32,
		64, -32,
		64, 0,
		128, 0,
		128, 96,
		96, 96,
		96, 128,
		-32, 128
	});
	
	private float[] rangeBody = new float[] {
		-1, -1,
		0, -1,
		1, -1,
		1, 0,
		2, 0,
		3, 0,
		3, 1,
		3, 2,
		2, 2,
		1, 2,
		1, 3,
		0, 3,
		-1, 3,
	};
	
	private float[] offset = new float[] {
		1, 1, 
		-1, 1,
		-1, 1,
		-1, 1,
		-1, -1,
		-1, -1,
		-1, -1,
		1, -1
	};
	
	public TestAoeTower() {
		fireRate = 1;
		damage = 4;
		center = new int[]{0,0};
	}
	
	public TestAoeTower(float rotation) {
		this();
		
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
	
	public TestAoeTower(int id, int[] cent, float rotation) {
		this();
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
	
	private String name = "Test Aoe Tower";
	private int gold = 10;
	private String description = "This tower attacks all nearby enemies!";
	
	private String iconPath = "towers/icons/testAoeTowerIcon.png";
	private String spritePath = "towers/sprites/testAoeTowerSprite.png";

	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescript() {
		return description;
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

	@Override
	public TowerType getTowerType() {
		return TowerType.TEST_AOE_TOWER;
	}

	@Override
	public void acquireTargets(DelayedRemovalArray<Enemy> enemies) {
		Polygon thisRange = new Polygon(this.getRange(this.center));
		for (Enemy e : target) {
			if (!Intersector.overlapConvexPolygons(e.getHitbox(), thisRange)) {
				this.target.removeValue(e, false);
			}
		}
		for (Enemy e : enemies) {
			if (!this.target.contains(e, false) && Intersector.overlapConvexPolygons(e.getHitbox(), thisRange)) {
				this.target.add(e);
				e.towersAttacking.add(this);
			}
		}
	}

	@Override
	public void fire(DelayedRemovalArray<Projectile> bullets) {
		System.out.println(this.target.size);
		for (Enemy e : this.target) {
			bullets.add(new TestBullet(e, this.damage, this.center.clone()));
		}
	}

}
