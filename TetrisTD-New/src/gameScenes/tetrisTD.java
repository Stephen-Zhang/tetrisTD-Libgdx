package gameScenes;

import levels.Level;
import player.Player;
import projectiles.Projectile;
import towers.Tower;
import util.utilityFunctions;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;

import enemies.Enemy;

public class tetrisTD extends Game {

	SpriteBatch batch;
	BitmapFont font;
	public Player player;
	protected DelayedRemovalArray<Enemy> enemies;
	protected Array<Tower> towers;
	protected DelayedRemovalArray<Projectile> bullets;
	protected boolean[] field;
	private Level currLevel;

	public Level getCurrLevel() {
		return currLevel;
	}

	public void setCurrLevel(Level currLevel) {
		this.currLevel = currLevel;
	}

	public boolean[] getField() {
		return field;
	}
	
	public void setField(boolean[] field) {
		this.field = field;
	}
	
	public Array<Projectile> getBullets() {
		return bullets;
	}

	public void setBullets(DelayedRemovalArray<Projectile> bullets) {
		this.bullets = bullets;
	}

	public DelayedRemovalArray<Enemy> getEnemies() {
		return enemies;
	}

	public void setEnemies(DelayedRemovalArray<Enemy> enemies) {
		this.enemies = enemies;
	}

	public Array<Tower> getTowers() {
		return towers;
	}

	public void setTowers(Array<Tower> towers) {
		this.towers = towers;
	}
	
	public void placeTower(Tower tower) {
		this.towers.add(tower);
		tower.gridLocation = utilityFunctions.flattenShape(tower.getShapeBody(), 24);
		for (int i : tower.gridLocation) {
			this.field[i] = true;
		}
	}

	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
	}

	@Override
	public void render() {		
		super.render();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
