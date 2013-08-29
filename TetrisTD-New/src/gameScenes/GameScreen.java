package gameScenes;

import java.util.Iterator;
import java.util.List;

import levels.Level;
import levels.LevelOne;
import player.Player;
import projectiles.Projectile;
import towers.attack.AttackTower;
import towers.base.BaseTower;
import towers.base.TowerType;
import towers.status.StatusTower;
import util.MyInputProcessor;
import util.OverlayInputProcessor;
import util.utilityFunctions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;

import enemies.Enemy;

public class GameScreen implements Screen {
	
	final tetrisTD game;
	
	Level currLevel;
	private TiledMap map;

	float totalTime;
	String textEvent;
	
	OrthographicCamera cam;
	
	InputProcessor gameProcessor;
	InputProcessor overlayProcessor;
	
	final int fieldWidth = 24;
	final int fieldHeight = 24;
	
	public GameScreen(final tetrisTD game, int level) {
		textEvent = "";
		Texture.setEnforcePotImages(false);
		this.game = game;
		this.game.enemies = new DelayedRemovalArray<Enemy>();
		this.game.towers = new Array<BaseTower>();
		this.game.bullets = new DelayedRemovalArray<Projectile>();
		this.game.field = new Integer[fieldWidth*fieldHeight];
		this.game.gfxUserInterface = new GameOverlay(this.game);
		
		gameProcessor = new MyInputProcessor(game);
		overlayProcessor = new OverlayInputProcessor(game);
		
		Gdx.input.setInputProcessor(gameProcessor);
		
		if (level == 1) {
			this.game.setCurrLevel(new LevelOne());
			this.game.getCurrLevel().getNextWave();
		}
		
		map = this.game.getCurrLevel().getMap();
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
		if (this.textEvent == "") {
			totalTime += Gdx.graphics.getDeltaTime()*1000;
			this.game.getCurrLevel().updateLevelTime(delta*1000);		
		}
		
		/*************************************************************************
		*
		**DRAWING STARTS HERE
		*
		**************************************************************************/
		game.gfxUserInterface.drawOverlay();
		
		game.batch.begin();
		//Draw Icons

		//Draw mouse buildings
		if (game.player.holding != TowerType.NULL) {
			ShapeRenderer drawShapes = new ShapeRenderer();

			this.game.player.canPlaceTower = canPlace(game.player.getTowerShapeBody());
			
			Gdx.gl10.glLineWidth(2);
			drawShapes.begin(ShapeType.Line);
			if (this.game.player.canPlaceTower && this.game.player.gold >= this.game.player.getCostOfTower()) {
				drawShapes.setColor(new Color(Color.GREEN));
				drawShapes.polygon(game.player.getTowerShape());
				drawShapes.setColor(new Color(Color.BLUE));
				drawShapes.polygon(game.player.getTowerRange());
			} else {
				drawShapes.setColor(new Color(Color.RED));
				drawShapes.polygon(game.player.getTowerShape());
			}

			Gdx.gl10.glLineWidth(1);

			drawShapes.end();
			drawShapes.dispose();
		}

		//Enemies
		for (Enemy e : this.game.enemies) {
			float[] floatPos = utilityFunctions.doubleToFloat(e.getPos());
			game.batch.draw(e.sprite, floatPos[0], floatPos[1]);
		}
		
		//Towers
		for (BaseTower t : this.game.towers) {
			//game.batch.draw(new Texture(t.getSpritePath()), t.getCenter()[0], t.getCenter()[1]);
			game.batch.draw(new TextureRegion(new Texture(t.getSpritePath())), t.getCenter()[0], t.getCenter()[1], 16, 16, 96, 96, 1, 1, t.getRotation());
		}
		
		//Bullets
		for (Projectile p : this.game.bullets) {
			game.batch.draw(p.sprite, p.pos[0], p.pos[1]);
		}

		//Draw Overlay for text events
		if (textEvent != "") {
			Gdx.input.setInputProcessor(overlayProcessor);
			
			Sprite overlay = new Sprite(new Texture("util/overlay.png"), 1024, 768);
			overlay.draw(this.game.batch);
			List<String> textEventList = utilityFunctions.wrap(textEvent, game.font, 200);
			Iterator<String> iter = textEventList.iterator();
			int height = 484;
			
			while (iter.hasNext()) {
				game.font.draw(game.batch, iter.next(), 412, height);
				height -= game.font.getLineHeight();
			}

			Gdx.gl10.glLineWidth(3);
			ShapeRenderer textBox = new ShapeRenderer();
			textBox.setColor(new Color(Color.WHITE));
			textBox.begin(ShapeType.Line);
			textBox.rect(362, 264, 300, 300);
			textBox.end();
			textBox.dispose();

		} else {
			Gdx.input.setInputProcessor(gameProcessor);
		}
		

		game.batch.end();
		/************************************************************************
		 * DRAWING ENDS HERE													*
		 ************************************************************************/



		/************************************
		 * UPDATING THE GAME CODE GOES HERE *
		 ************************************/
		//Update the enemy spawns

		textEvent = this.game.getCurrLevel().currWave.getTextEvent();
		if (textEvent == "") {
			if (this.game.getCurrLevel().currWave.nextEnemyAvail() && this.game.getCurrLevel().currWave.timeForNextEnemy(this.game.getCurrLevel().getLevelTime())) {
				this.game.enemies.add(this.game.getCurrLevel().currWave.getNextEnemy());
			}
			if (this.game.getCurrLevel().nextWaveAvail() && this.game.getCurrLevel().timeForNextWave()) {
				this.game.getCurrLevel().getNextWave();
			}
			if (!this.game.getCurrLevel().currWave.nextEnemyAvail() && this.game.enemies.size == 0) {
				this.game.getCurrLevel().sendEarly = true;
			} else {
				this.game.getCurrLevel().sendEarly = false;
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
			
			for (BaseTower t: this.game.towers) {
				if (t.isBuffTower()) {
					StatusTower buffT = (StatusTower) t;
				} else {
					AttackTower atkT = (AttackTower) t;
					atkT.buffUpdate(this.game.towers);
					atkT.acquireTargets(this.game.enemies);
					if(atkT.target.size > 0 && atkT.getCanFire()) {
						atkT.setCooldown(0);
						atkT.fire(this.game.bullets);
						atkT.setCanFire(false);
					} else {
						atkT.setCooldown(atkT.getCooldown() + Gdx.graphics.getDeltaTime());
						if (atkT.getCooldown() >= atkT.getFireRate()) {
							atkT.setCanFire(true);
						}
					}					
				}
			}
			
			for (Projectile p : this.game.bullets) {
				if (!this.game.enemies.contains(p.target, false)) {
					this.game.bullets.removeValue(p, false);
					continue;
				}
				p.updateMovement();
				if (Intersector.overlapConvexPolygons(p.getHitbox(), p.target.getHitbox())) {
					p.target.setCurrHealth(p.target.getCurrHealth() - p.damage);
					if (p.target.getCurrHealth() <= 0) {
						//Dead
						for (AttackTower t : p.target.towersAttacking) {
							t.target.removeValue(p.target, false);
						}
						this.game.player.gold += p.target.bounty;
						this.game.enemies.removeValue(p.target, false);
					}
					this.game.bullets.removeValue(p, false);
				}
			}	
		}
	}
	
	/**
	 * Takes in grid coordinates of a shape (tower, eg) and returns if it can be placed
	 * @param shapeBody
	 * @return
	 */
	private boolean canPlace(float[] shapeBody) {
		int[] flattenedShape = utilityFunctions.flattenShape(shapeBody, fieldWidth);

		for (int i = 0; i < flattenedShape.length; i++) {
			if (flattenedShape[i] >= this.game.field.length) return false;	//HACK: returns false when mouse outside screen range
			if (this.game.field[flattenedShape[i]] != null) {
				return false;
			}
		}
		
		TiledMapTileLayer tiledLayer = (TiledMapTileLayer)map.getLayers().get(0);
		for (int i = 0; i < shapeBody.length-1; i+=2) {
			//Normalized X and Y into the center of each tile.
			int x = (int)(shapeBody[i]);
			int y = (int)(shapeBody[i+1]);
			
			MapProperties tProps = tiledLayer.getCell(x, y).getTile().getProperties();
			if (tProps.containsKey("buildable")) {
				if (tProps.get("buildable").equals("no")) {
					return false;
				}
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
