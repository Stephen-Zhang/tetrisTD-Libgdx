package gameScenes;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import towers.attack.TestTower;
import towers.base.BaseTower;
import towers.base.TowerType;
import towers.status.AtkSpeedTower;
import util.utilityFunctions;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class GameOverlay {
	private final tetrisTD game;

	private HashMap<TowerType, BaseTower> towerMapping = new HashMap<TowerType, BaseTower>();
	private HashMap<Rectangle, BaseTower> iconToTower = new HashMap<Rectangle, BaseTower>();
	
	private BaseTower hovering = null;
	
	public GameOverlay(tetrisTD game) {
		this.game = game;

		towerMapping.put(TowerType.ATK_SPEED_TOWER, new AtkSpeedTower());
		towerMapping.put(TowerType.TEST_TOWER, new TestTower());
		//towerMapping.put(TowerType.NULL, null);
	}
	
	public void drawOverlay() {
		this.game.batch.begin();
		
		//Draw Menu
		game.font.setColor(new Color(Color.WHITE));	
		
		drawLivesAndGold();
		drawSendEarly();
		drawIcons();
		
		this.game.batch.end();
	}
	
	private void drawLivesAndGold() {
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
	}
	
	private void drawSendEarly() {
		//Send Early Message
		if (!this.game.getCurrLevel().nextWaveAvail() && this.game.enemies.size == 0) {
			this.game.getCurrLevel().done = true;
			List<String> lvlComplete = utilityFunctions.wrap("You finished this level! For now, theres nothing else to do. Congratulations!", game.font, 150);
			Iterator<String> iter = lvlComplete.iterator();
			int height = 668;
			while (iter.hasNext()) {
				game.font.draw(game.batch, iter.next(), 840, height);
				height -= game.font.getLineHeight();
			}
		} else if (this.game.getCurrLevel().sendEarly) {
			List<String> sendEarlyMsg = utilityFunctions.wrap("You finished this wave! You may send the next wave early if you press the key P on the keyboard.", game.font, 150);
			Iterator<String> iter = sendEarlyMsg.iterator();
			int height = 668;
			while (iter.hasNext()) {
				game.font.draw(game.batch, iter.next(), 840, height);
				height -= game.font.getLineHeight();
			}
		}
	}
	
	private void drawIcons() {
		int x = 850;
		int xLimit = 1000;
		int y = 500;
		for (TowerType t : towerMapping.keySet()) {
			BaseTower tempTower = towerMapping.get(t);
			Rectangle iconBox = new Rectangle(x, y, 32, 32);

			iconToTower.put(iconBox, tempTower);
			
			game.batch.draw(new Texture(tempTower.getIconPath()), x, y);
			
			x += 50;
			if (x >= xLimit) {
				x = 800;
				y += 50;
			}
		}
	}

	public HashMap<TowerType, BaseTower> getTowerMapping() {
		return towerMapping;
	}

	public void setTowerMapping(HashMap<TowerType, BaseTower> towerMapping) {
		this.towerMapping = towerMapping;
	}

	public HashMap<Rectangle, BaseTower> getIconToTower() {
		return iconToTower;
	}

	public void setIconToTower(HashMap<Rectangle, BaseTower> iconToTower) {
		this.iconToTower = iconToTower;
	}

	public BaseTower getHovering() {
		return hovering;
	}

	public void setHovering(BaseTower hovering) {
		this.hovering = hovering;
	}
}
