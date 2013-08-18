package towers.status;

import projectiles.Projectile;
import util.StatusType;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.DelayedRemovalArray;

import enemies.Enemy;

public class AtkSpeedTower extends StatusTower{

	private Polygon shape = new Polygon(new float[] {
		0, 0,
		32, 0,
		32, 96,
		0, 96
	});
	
	private Polygon range = new Polygon(new float[] {
		-32, -32,
		64, -32,
		64, 128,
		-32, 128
	});
	
	private float[] shapeBody = new float[] {
		0, 0,
		0, 1,
		0, 2
	};
	
	private float[] rangeBody = new float[] {
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

	private String name = "Attack Speed Tower";
	private int gold = 50;
	private String description = "This tower is a test tower for status buffs.";
	
	private String iconPath = "towers/atkSpeedTower.png";
	private String spritePath = "towers/atkSpeedTower.png";	
	
	public AtkSpeedTower() {
		
	}
	
	public AtkSpeedTower(int id, int[] center) {
		this.id = id;
		this.center = center;
		this.buffs.add(StatusType.INCREASE_ATK_SPEED);
		this.buffStrengthBase = .50; //50% atkSpeed
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Attack Speed Boost Tower";
	}

	@Override
	public String getSpritePath() {
		// TODO Auto-generated method stub
		return spritePath;
	}

	@Override
	public String getIconPath() {
		// TODO Auto-generated method stub
		return iconPath;
	}

	@Override
	public int getCost() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public float[] getShape(int[] mouseLoc) {
		float[] retVal = shape.getVertices().clone();
		for (int i = 0; i < retVal.length; i++ ) {
			if (i % 2 == 0) {
				//Even and 0
				retVal[i] += mouseLoc[0];
			} else {
				retVal[i] += mouseLoc[1];
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
				retVal[i] += mouseLoc[1];
			}
		}
		return retVal;
	}

	@Override
	public String getDescript() {
		// TODO Auto-generated method stub
		return "Increases attack speed by"+this.buffStrengthBase+"%!";
	}

	public float[] getShapeBody() {
		// TODO Auto-generated method stub
		return getShapeBody(this.center);
	}

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

}
