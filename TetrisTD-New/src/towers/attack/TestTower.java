package towers.attack;

import projectiles.Projectile;
import projectiles.TestBullet;
import towers.base.TowerType;
import util.utilityFunctions;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;

import enemies.Enemy;

public class TestTower extends AttackTower {
	public TestTower() {
		fireRate = 1;
		damage = 10;
		center = new int[]{0,0};
		name = "Test Tower";
		cost = 10;
		description = "This tower is a test tower for single targets. Basic without numbers tuned";
		iconPath = "towers/icons/testTowerIcon.png";
		spritePath = "towers/sprites/testTowerSprite.png";
		key = Input.Keys.T;
		towerType = TowerType.TEST_TOWER;
		
		shape = new Polygon(new float[]{ 
			0, 0, 
			64, 0,
			64, 64,
			32, 64,
			32, 32,
			0, 32,
		});
	
		shapeBody = new float[] {
			0, 0,
			1, 0,
			1, 1,
		};
		
		rangeBody = new float[] {
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
		
		offset = new float[] {
			1, 1,
			-1, 1,
			-1, -1,
			1, -1,
			1, -1,
			1, -1,
		};

		range = new Polygon(new float[]{
			-32, -32,
			96, -32,
			96, 96,
			0, 96,
			0, 64,
			-32, 64,
		});
	}
	
	public TestTower(float rotation) {
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
	
	public TestTower(int id, int[] cent, float rotation) {
		this(rotation);
		this.id = id;
		center = cent;
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
