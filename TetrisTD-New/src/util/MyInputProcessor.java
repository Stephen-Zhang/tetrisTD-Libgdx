package util;

import gameScenes.tetrisTD;
import towers.base.BaseTower;
import towers.base.TowerType;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.OrderedMap;

public class MyInputProcessor implements InputProcessor {

	final tetrisTD game;
	private int idGen = 0;
	
	public MyInputProcessor(final tetrisTD game) {
		this.game = game;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (this.game.player.holding != TowerType.NULL && keycode == Input.Keys.ESCAPE) {
			this.game.player.holding = TowerType.NULL;
		}
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		//TODO: Change these to rely on tower.key instead of hardcode.
		if (character == 't') {
			game.player.holding = TowerType.TEST_TOWER;
		}
		if (character == 'y') {
			game.player.holding = TowerType.ATK_SPEED_TOWER;
		}
		if (character == 'r') {
			game.player.holding = TowerType.TEST_AOE_TOWER;
		}
		if (character == 'u') {
			game.player.holding = TowerType.SLOW_TOWER;
		}
		if (this.game.getCurrLevel().sendEarly && !this.game.getCurrLevel().done && character == 'p') {
			this.game.getCurrLevel().getNextWave();
		}
		return false;		
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		if (button == Input.Buttons.LEFT) {
			if (this.game.player.holding != TowerType.NULL && this.game.player.canPlaceTower && this.game.player.gold >= this.game.player.getCostOfTower()) {
				this.game.player.gold -= this.game.player.getCostOfTower();
				
				BaseTower placeThis = this.game.player.makeNewTower(idGen++);
				this.game.placeTower(placeThis);
				
				if (placeThis.isBuffTower()) {
					placeThis.getTowersInRange(this.game.getTowers());
				}
				
				this.game.player.holdingRotation = 0;
				this.game.player.holding = TowerType.NULL;
			} else if (this.game.getGfxUserInterface().getHovering() != null) {
				
				this.game.player.holding = this.game.getGfxUserInterface().getHovering().getTowerType();
			}
		} else if (button == Input.Buttons.RIGHT) {
			//For now, right clicking rotates the shape
			if (this.game.player.holding != TowerType.NULL) {
				this.game.player.holdingRotation = (this.game.player.holdingRotation + 90f) % 360f;
			}
		}
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		this.game.player.currMouseLoc = new int[]{screenX - screenX%32, 768 - 32 - screenY + screenY%32};
		
		int[] unroundedMouse = new int[]{screenX, 768 - screenY};
		
		//Not holding anything.
		if (this.game.player.holding == TowerType.NULL) {

			//Hover over an icon...
			BaseTower hovering = null;
			OrderedMap<Rectangle, BaseTower> iconSetMap = this.game.getGfxUserInterface().getIconToTower();
			for (Rectangle rect : iconSetMap.keys()) {
				if (rect.contains(unroundedMouse[0], unroundedMouse[1])) {
					//Hovering over a mouse, set hovering to that tower
					hovering = iconSetMap.get(rect);
					break;
				}
			}

			this.game.getGfxUserInterface().setHovering(hovering);
		}

		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
