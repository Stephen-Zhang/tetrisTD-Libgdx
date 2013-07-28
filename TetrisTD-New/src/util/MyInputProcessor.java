package util;

import gameScenes.tetrisTD;
import towers.TestTower;
import towers.TowerType;

import com.badlogic.gdx.InputProcessor;

public class MyInputProcessor implements InputProcessor {

	final tetrisTD game;
	
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		if (character == 't') {
			game.player.holding = TowerType.TEST_TOWER;
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
		if (this.game.player.holding != TowerType.NULL && this.game.player.canPlaceTower && this.game.player.gold >= this.game.player.getCostOfTower()) {
			this.game.player.gold -= this.game.player.getCostOfTower();
			this.game.getTowers().add(this.game.player.makeNewTower());
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
