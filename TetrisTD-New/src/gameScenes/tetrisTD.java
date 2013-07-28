package gameScenes;

import player.Player;
import projectiles.Projectile;
import towers.Tower;

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
