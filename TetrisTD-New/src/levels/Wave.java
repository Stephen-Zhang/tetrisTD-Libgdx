package levels;

import towers.base.TowerType;

import com.badlogic.gdx.utils.Array;

import enemies.Enemy;

public class Wave {
	private Array<Enemy> waveOfE;
	private Array<Integer> timings;
	public int startTime;
	private String textEvent = "";
	private Array<TowerType> availTowers;
	
	public Wave(Array<Enemy> e, Array<Integer> t, Array<TowerType> towers, int start, String text) {
		waveOfE = e;
		timings = t;
		availTowers = towers;
		textEvent = text;
		startTime = start;
	}
	
	public boolean timeForNextEnemy(float levelTimer) {
		return (levelTimer > timings.get(0)) ? true : false;
	}
	
	public boolean nextEnemyAvail() {
		return (waveOfE.size > 0) ? true : false;
	}
	
	public Enemy getNextEnemy() {
		timings.removeIndex(0);
		return waveOfE.removeIndex(0);
	}
	
	public Array<TowerType> getAvailTowers() {
		return availTowers;
	}

	public boolean checkTextEvent() {
		if (this.textEvent != "") {
			return true;
		}
		return false;
	}
	
	public String getTextEvent() {
		return this.textEvent;
	}

	public void setTextEvent(String textEvent) {
		this.textEvent = textEvent;
	}
	
}
