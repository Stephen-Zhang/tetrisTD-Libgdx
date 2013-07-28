package projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;

import enemies.Enemy;

public abstract class Projectile {
	public Enemy target;
	public double speed;
	public int[]pos = new int[2];
	public double[] dir = new double[2];
	public int damage;
	public Texture sprite;
	public float[] hitboxVertices = new float[]{
			0,0,
			0,8,
			8,8,
			8,0};
	
	//TODO possible other effects
		
	
	public void getDirection() {
		int[] targetLoc = target.getPos();
		double[] targetCenter = {targetLoc[0]+16, targetLoc[1]+16};
		//Distance to sprite center. assumed to be 32 atm.
		double distance = Math.sqrt( Math.pow((targetCenter[0]-pos[0]),2) + Math.pow((targetCenter[1]-pos[1]), 2) );
		dir[0] = (targetCenter[0] - pos[0])/distance;
		dir[1] = (targetCenter[1] - pos[1])/distance;
	}

	public Polygon getHitbox() {
		float [] returnVal = new float[hitboxVertices.length];
		for (int i = 0; i < hitboxVertices.length; i++) {
			if (i % 2 == 0) {
				returnVal[i] = hitboxVertices[i] + pos[0];
			} else {
				returnVal[i] = hitboxVertices[i] + pos[1];			
			}
		}
		return new Polygon(returnVal);
	}

	public void updateMovement() {
		// TODO Auto-generated method stub
		getDirection();
		pos[0] += dir[0]*speed;
		pos[1] += dir[1]*speed;
	}
}
