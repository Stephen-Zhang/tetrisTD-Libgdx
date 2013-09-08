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

	
	public TestAoeTower() {
		fireRate = 1;
		damage = 4;
		center = new int[]{0,0};
		
		key = Input.Keys.R;
		
		shape = new Polygon(new float[]{ 
			0, 0,
			32, 0,
			32, 32, 
			96, 32,
			96, 64,
			32, 64,
			32, 96,
			0, 96
		});
		
		shapeBody = new float[] {
			0, 0,
			0, 1,
			0, 2,
			1, 1,
			2, 1,
			
		};

		range = new Polygon(new float[]{
			-32 ,-32,
			64, -32,
			64, 0,
			128, 0,
			128, 96,
			96, 96,
			96, 128,
			-32, 128
		});
		
		rangeBody = new float[] {
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
		
		offset = new float[] {
			1, 1, 
			-1, 1,
			-1, 1,
			-1, 1,
			-1, -1,
			-1, -1,
			-1, -1,
			1, -1
		};
		
		name = "Test Aoe Tower";
		cost = 10;
		description = "This tower attacks all nearby enemies!";
		
		iconPath = "towers/icons/testAoeTowerIcon.png";
		spritePath = "towers/sprites/testAoeTowerSprite.png";

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
