package util;

import gameScenes.tetrisTD;

import java.util.HashMap;

import towers.base.BaseTower;
import towers.base.TowerType;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Rectangle;

public class MenuInputProcessor implements InputProcessor {

	final tetrisTD game;
	private int idGen = 0;
	
	public MenuInputProcessor(final tetrisTD game) {
		this.game = game;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {

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

		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		this.game.currMouseLoc = new int[]{screenX - screenX%32, 768 - 32 - screenY + screenY%32};
		
		//int[] unroundedMouse = new int[]{screenX, 768 - screenY};

		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
