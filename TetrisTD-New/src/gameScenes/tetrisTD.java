package gameScenes;

import levels.Level;
import player.Player;
import projectiles.Projectile;
import towers.base.BaseTower;
import util.utilityFunctions;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;

import enemies.Enemy;

public class tetrisTD extends Game {

	SpriteBatch batch;
	BitmapFont font;
	public Player player;
	protected DelayedRemovalArray<Enemy> enemies;
	protected Array<BaseTower> towers;
	protected DelayedRemovalArray<Projectile> bullets;
	protected Integer[] field;
	private Level currLevel;

	public Level getCurrLevel() {
		return currLevel;
	}

	public void setCurrLevel(Level currLevel) {
		this.currLevel = currLevel;
	}

	public Integer[] getField() {
		return field;
	}
	
	public void setField(Integer[] field) {
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

	public Array<BaseTower> getTowers() {
		return towers;
	}

	public void setTowers(Array<BaseTower> towers) {
		this.towers = towers;
	}
	
	public void placeTower(BaseTower tower) {
		this.towers.add(tower);
		tower.setGridLocation(utilityFunctions.flattenShape(tower.getShapeBody(), 24));
		for (int i : tower.getGridLocation()) { 
			this.field[i] = tower.getId();
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