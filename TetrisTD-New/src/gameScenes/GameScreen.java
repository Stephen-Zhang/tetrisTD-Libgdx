package gameScenes;

import java.util.Iterator;
import java.util.List;

import levels.Level;
import levels.LevelOne;
import player.Player;
import projectiles.Projectile;
import towers.Tower;
import towers.TowerType;
import util.MyInputProcessor;
import util.utilityFunctions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;

import enemies.Enemy;

public class GameScreen implements Screen {
	
	final tetrisTD game;
	
	Level currLevel;
	private TiledMap map;

	float totalTime;
	
	OrthographicCamera cam;
	
	public GameScreen(final tetrisTD game, int level) {
		Texture.setEnforcePotImages(false);
		this.game = game;
		this.game.enemies = new DelayedRemovalArray<Enemy>();
		this.game.towers = new Array<Tower>();
		this.game.bullets = new DelayedRemovalArray<Projectile>();
		Gdx.input.setInputProcessor(new MyInputProcessor(game));
		
		if (level == 1) {
			currLevel = new LevelOne();
			currLevel.getNextWave();
		}
		
		map = currLevel.getMap();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 32, 24);
		
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

			float[] shapeVertices = game.player.getTowerShape();
			
			
			drawShapes.begin(ShapeType.Line);
			this.game.player.canPlaceTower = canPutDown(shapeVertices);
			if (this.game.player.canPlaceTower) {
				drawShapes.setColor(Color.GREEN);
				drawShapes.polygon(shapeVertices);
				drawShapes.setColor(Color.BLUE);
				drawShapes.polygon(game.player.getTowerRange());
			} else {
				drawShapes.setColor(Color.RED);
				drawShapes.polygon(shapeVertices);
			}
			drawShapes.end();
		}

		//Enemies
		for (Enemy e : this.game.enemies) {
			game.batch.draw(e.sprite, e.getPos()[0], e.getPos()[1]);
		}
		
		//Towers
		for (Tower t : this.game.towers) {
			game.batch.draw(new Texture(t.getSpritePath()), t.getCenter()[0], t.getCenter()[1]);
		}
		
		//Bullets
		for (Projectile p : this.game.bullets) {
			game.batch.draw(p.sprite, p.pos[0], p.pos[1]);
		}

		/*************************************************************************
		*
		**DRAWING ENDS HERE
		*
		**************************************************************************/
		game.batch.end();

		//Update the game
			//Update the enemy spawns
		if (currLevel.currWave.nextEnemyAvail() && currLevel.currWave.timeForNextEnemy(currLevel.getLevelTime())) {
			this.game.enemies.add(currLevel.currWave.getNextEnemy());
		}
		if (currLevel.nextWaveAvail() && currLevel.timeForNextWave()) {
			currLevel.getNextWave();
		}
		
		for (Enemy e: this.game.enemies) {
			if ( e.arrivedAtDest() && e.nextDestExists() ) {
				e.getNextDest();
			}
			if (e.success) {
				this.game.enemies.removeValue(e, false);
				if (game.player.lives > 0) {
					game.player.lives--;
				}
				continue;
			} else {
				e.updateMovement(Gdx.graphics.getDeltaTime());
			}
		}
		
		for (Tower t: this.game.towers) {
			t.acquireTargets(this.game.enemies);
			t.fire(this.game.bullets);
		}
		
		for (Projectile p : this.game.bullets) {
			if (!this.game.enemies.contains(p.target, false)) {
				this.game.bullets.removeValue(p, false);
				continue;
			}
			p.updateMovement();
			if (Intersector.overlapConvexPolygons(p.getHitbox(), p.target.getHitbox())) {
				p.target.setCurrHealth(p.target.getCurrHealth() - p.damage);
				if (p.target.getCurrHealth() < 0) {
					//Dead
					for (Tower t : p.target.towersAttacking) {
						t.target.removeValue(p.target, false);
					}
					this.game.player.gold += p.target.bounty;
					this.game.enemies.removeValue(p.target, false);
				}
				this.game.bullets.removeValue(p, false);
			}
		}

	}

	private boolean canPutDown(float[] shapeVertices) {
		// TODO Auto-generated method stub
		TiledMapTileLayer tiledLayer = (TiledMapTileLayer)map.getLayers().get(0);
		for (int i = 0; i < shapeVertices.length-1; i+=2) {
			//Normalized X and Y into the center of each tile.
			int x = (int)((shapeVertices[i])/32);
			int y = (int)((shapeVertices[i+1])/32);
			
			MapProperties tProps = tiledLayer.getCell(x, y).getTile().getProperties();
			if (tProps.containsKey("buildable")) {
				if (tProps.get("buildable").equals("no")) {
					return false;
				}
			}
		}
		Polygon mouseShape = new Polygon(shapeVertices);
		Polygon tempPolygon;
		for (Tower t : this.game.towers) {
			tempPolygon = new Polygon(t.getShape(t.getCenter()));
			if (Intersector.overlapConvexPolygons(tempPolygon, mouseShape)) {
				return false;
			}
		}
		return true;
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
