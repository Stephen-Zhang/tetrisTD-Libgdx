package levels;

import java.awt.Point;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.Array;

public abstract class Level {
	protected Array<Wave> waves;
	protected TiledMap levelMap;
	protected float levelTime = 0;
	
	public Wave currWave;
	public int[] startingLoc = new int[2];
	public Array<Point> waypts = new Array<Point>();
	public boolean done = false;
	
	public boolean sendEarly = false;

	public boolean nextWaveAvail() {
		return (waves.size > 0) ? true : false;
	}

	public boolean timeForNextWave() {
		return (levelTime > waves.get(0).startTime) ? true : false;
	}
	
	public void getNextWave() {
		resetLevelTime();
		currWave = waves.removeIndex(0);
	}

	public TiledMap getMap() {
		return levelMap;
	}
	
	public void updateLevelTime(float delta) {
		levelTime += delta;
	}
	
	public void resetLevelTime() {
		levelTime = 0;
	}
	
	public float getLevelTime() {
		return levelTime;
	}
}
