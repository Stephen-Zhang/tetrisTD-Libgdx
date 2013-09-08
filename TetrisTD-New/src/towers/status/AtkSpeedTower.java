package towers.status;

import projectiles.Projectile;
import towers.base.TowerType;
import util.StatusType;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.DelayedRemovalArray;

import enemies.Enemy;

public class AtkSpeedTower extends StatusTower{

	
	public AtkSpeedTower() {
		shape = new Polygon(new float[] {
			0, 0,
			32, 0,
			32, 96,
			0, 96
		});
			
		range = new Polygon(new float[] {
			-32, -32,
			64, -32,
			64, 128,
			-32, 128
		});
		
		shapeBody = new float[] {
			0, 0,
			0, 1,
			0, 2
		};
		
		rangeBody = new float[] {
			-1, -1,
			-1, 0,
			-1, 1,
			-1, 2,
			-1, 3,
			0, 3,
			1, 3,
			1, 2,
			1, 1,
			1, 0,
			1, -1,
			0, -1
		};

		name = "Attack Speed Tower";
		cost = 50;
		description = "This tower is a test tower for status buffs.";
		
		iconPath = "towers/icons/atkSpeedTowerIcon.png";
		spritePath = "towers/sprites/atkSpeedTowerSprite.png";	

		towerType = towerType.ATK_SPEED_TOWER;
		
		this.buffs.add(StatusType.INCREASE_ATK_SPEED);
		this.buffStrengthBase = .50; //50% atkSpeed
		
	}

	public AtkSpeedTower(float rotation) {
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
	}
	
	public AtkSpeedTower(int id, int[] center, float rotation) {
		this(rotation);
		this.center = center;
		this.id = id;
	}


}
