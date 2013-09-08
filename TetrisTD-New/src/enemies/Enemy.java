package enemies;

import java.awt.Point;

import towers.attack.AttackTower;
import util.StatusType;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;

public abstract class Enemy {
	protected double maxHealth;
	protected double currHealth;
	protected String name;
	protected double slowFactor;
	protected Array<StatusType> debuffs = new Array<StatusType>();
	protected String spritePath;
	
	public double getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(double maxHealth) {
		this.maxHealth = maxHealth;
	}

	public double getCurrHealth() {
		return currHealth;
	}

	public void setCurrHealth(double currHealth) {
		this.currHealth = currHealth;
	}

	protected double[] pos = new double[2];
	protected double[] dir = new double[2];

	public boolean success = false;
	
	public Array<Point> waypoints;
	protected Point destination;
	protected double walkSpeed = 1;

	public DelayedRemovalArray<AttackTower> towersAttacking = new DelayedRemovalArray<AttackTower>();
	public boolean alive = true;
	public int bounty;
	
	public boolean nextDestExists() {
		if (this.waypoints.size == 0) {
			if (arrivedAtDest()) {
				this.success = true;				
			}
			return false;
		}
		return true;
	}

	public boolean arrivedAtDest() {
		if ((int)this.pos[0] == this.destination.x && (int)this.pos[1] == this.destination.y) {
			return true;
		}
		return false;
	}
	
	public void getNextDest() {
		destination = (waypoints.size > 0) ? waypoints.removeIndex(0) : null;
	}
	
	private void adjustDirection() {
		double distance = destination.distance(pos[0], pos[1]);
		dir[0] = (destination.x - pos[0])/distance;
		dir[1] = (destination.y - pos[1])/distance;
	}
	
	public int distToGoal() {
		int dist = 0;
		
		//Waypoints added
		for (int i = waypoints.size-1; i > 0; i--) {
			dist += waypoints.get(i).distanceSq(waypoints.get(i-1));
		}
		
		//closest waypoint to currDestination
		if (waypoints.size > 0) {
			dist += waypoints.get(0).distanceSq(destination);
		}
		
		//currDestination to currPos
		dist += destination.distanceSq(pos[0], pos[1]);
		
		return dist;
	}
	
	public double[] getPos() {
		return pos;
	}
	
	public void updateMovement(float delta) {
		// TODO Auto-generated method stub
		adjustDirection();
		pos[0] += dir[0]*walkSpeed;
		pos[1] += dir[1]*walkSpeed;
	}
	
	public double getSpeed() {
		return this.walkSpeed * this.slowFactor;
	}

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
	
	public String getSpritePath() {
		return this.spritePath;
	}
	
	public String getName() {
		return this.name;
	}

}
