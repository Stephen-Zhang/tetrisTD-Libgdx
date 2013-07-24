package gameScenes;

import java.util.Iterator;
import java.util.List;

import levels.Level;
import levels.LevelOne;
import player.Player;
import towers.Tower;
import towers.TowerType;
import util.MyInputProcessor;
import util.utilityFunctions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;

import enemies.Enemy;

public class GameScreen implements Screen {
	
	final tetrisTD game;
	
	Level currLevel;
	private TiledMap map;

	float totalTime;
	
	OrthographicCamera cam;
	
	public DelayedRemovalArray<Enemy> enemies;
	public Array<Tower> towers;
	
	public GameScreen(final tetrisTD game, int level) {
		this.game = game;
		Gdx.input.setInputProcessor(new MyInputProcessor(game));
		
		if (level == 1) {
			currLevel = new LevelOne();
			currLevel.getNextWave();
		}
		
		map = currLevel.getMap();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 32, 24);

		enemies = new DelayedRemovalArray<Enemy>();
		towers = new Array<Tower>();
		
		game.player = new Player(20, 100);
	}

	@Override
	public void render(float delta) {
		//Update Camera
		float unitScale = 1 / 32f;
		OrthogonalTiledMapRenderer renderer = new OrthogonalTiledMapRenderer(map, unitScale);
		renderer.setView(cam);
		renderer.render();
		
		//Update time trackers
		totalTime += Gdx.graphics.getDeltaTime()*1000;
		currLevel.updateLevelTime(delta*1000);		
		
		//Draw all enemies and towers and bullets
		game.batch.begin();
		
		//Draw Menu
		game.font.setColor(Color.WHITE);
		if (game.player.lives > 0) {
			game.font.draw(game.batch, "Lives: "+game.player.lives, 840, 732);
			game.font.draw(game.batch, "Gold: "+game.player.gold, 840, 700);
		} else {
			List<String> gameOverMsg = utilityFunctions.wrap("Game Over! Unfortunately you have no more lives left.", game.font, 150);
			Iterator<String> iter = gameOverMsg.iterator();
			int height = 668;
			while(iter.hasNext()) {
				game.font.draw(game.batch, iter.next(), 840, height);
				height -= game.font.getLineHeight();
			}
		}
		

		//Draw mouse buildings
		if (game.player.holding != TowerType.NULL) {
			ShapeRenderer drawShapes = new ShapeRenderer();
			drawShapes.setColor(Color.GREEN);
			
			drawShapes.begin(ShapeType.Line);
			drawShapes.polygon(game.player.getTowerShape());
			drawShapes.setColor(Color.BLUE);
			drawShapes.polygon(game.player.getTowerRange());
			drawShapes.end();
		}

		//Enemies
		for (Enemy e : enemies) {
			float[] position = utilityFunctions.doubleToFloat(e.getPos());
			game.batch.draw(e.sprite, position[0], position[1]);
		}
		game.batch.end();
		
		//Update the game
			//Update the enemy spawns
		if (currLevel.currWave.nextEnemyAvail() && currLevel.currWave.timeForNextEnemy(currLevel.getLevelTime())) {
			enemies.add(currLevel.currWave.getNextEnemy());
		}
		if (currLevel.nextWaveAvail() && currLevel.timeForNextWave()) {
			currLevel.getNextWave();
		}
		
		for (Enemy e: enemies) {
			if ( e.arrivedAtDest() && e.nextDestExists() ) {
				e.getNextDest();
			}
			if (e.success) {
				enemies.removeValue(e, false);
				if (game.player.lives > 0) {
					game.player.lives--;
				}
				continue;
			} else {
				e.updateMovement(Gdx.graphics.getDeltaTime());
			}
		}
		
		//Check for keyboard events
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
