package util;

import gameScenes.tetrisTD;
import towers.base.BaseTower;
import towers.base.TowerType;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

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
		if (character == 't') {
			game.player.holding = TowerType.TEST_TOWER;
		}
		if (character == 'y') {
			game.player.holding = TowerType.ATK_SPEED_TOWER;
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
				
				this.game.player.holding = TowerType.NULL;
			}
		} else if (button == Input.Buttons.RIGHT) {
			//For now, right clicking gets rid of the tower.
			if (this.game.player.holding != TowerType.NULL) {
				this.game.player.holding = TowerType.NULL;
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
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
