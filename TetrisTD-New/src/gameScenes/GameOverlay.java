package gameScenes;

import java.util.HashMap;

import towers.attack.AttackTower;
import towers.attack.TestAoeTower;
import towers.attack.TestTower;
import towers.base.BaseTower;
import towers.base.TowerType;
import towers.status.AtkSpeedTower;
import util.utilityFunctions;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
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
		towerMapping.put(TowerType.TEST_AOE_TOWER, new TestAoeTower());
		//towerMapping.put(TowerType.NULL, null);
	}
	
	public void drawOverlay() {
		this.game.batch.begin();
		
		//Draw Menu
		game.font.setColor(new Color(Color.WHITE));	
		
		drawLivesAndGold();
		drawSendEarly();
		drawIcons();
		drawHovering();
		
		this.game.batch.end();
	}
	
	private void drawHovering() {
		// TODO Auto-generated method stub
		if (this.hovering != null) {
			
			int height = 300;
			height = utilityFunctions.drawText(game.font, game.batch, this.hovering.getDescript(), 850, height, 150);
			if (this.hovering.isBuffTower()) {
				
			} else {
				height = utilityFunctions.drawText(game.font, game.batch, "Damage: "+((AttackTower)this.hovering).getDamage(), 850, height, 150);
				height = utilityFunctions.drawText(game.font, game.batch,  "Fire Speed: Every "+((AttackTower)this.hovering).getFireRate()+" seconds", 850, height, 150);
			}
		}
	}

	private void drawLivesAndGold() {
		if (game.player.lives > 0) {
			int height = 732;
			height = utilityFunctions.drawText(game.font, game.batch, "Lives: "+game.player.lives, 850, height, 150);
			height = utilityFunctions.drawText(game.font, game.batch, "Gold: "+game.player.gold, 850, height, 150);
		} else {
			int height = 668;
			utilityFunctions.drawText(game.font, game.batch, "Game Over! Unfortunately you have no more lives left.", 850, height, 150);
		}
	}
	
	private void drawSendEarly() {
		//If player is dead, ignore
		if (this.game.player.lives <= 0) {
			return;
		}
		//Send Early Message at height of 668
		int height = 668;
		String displayStr = "";
		if (!this.game.getCurrLevel().nextWaveAvail() && this.game.enemies.size == 0) {
			this.game.getCurrLevel().done = true;
			displayStr = "You finished this level! For now, theres nothing else to do. Congratulations!";
			
		} else if (this.game.getCurrLevel().sendEarly) {
			displayStr = "You finished this wave! You may send the next wave early if you press the key P on the keyboard.";
		}
		utilityFunctions.drawText(game.font, game.batch, displayStr, 850, height, 150);
	}
	
	private void drawIcons() {
		int x = 850;
		int xLimit = 1000;
		int y = 500;
		for (TowerType t : towerMapping.keySet()) {
			BaseTower tempTower = towerMapping.get(t);
			Rectangle iconBox = new Rectangle(x, y, 32, 32);
			
			iconToTower.put(iconBox, tempTower);
			
			game.batch.draw(this.game.getTextureManager().getTextureFromMap(tempTower.getName() + " Icon"), x, y);
			
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
