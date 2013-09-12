package gameScenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MainMenuScreen implements Screen {

	final tetrisTD game;
	
	OrthographicCamera cam;
	
	public MainMenuScreen(final tetrisTD game) {
		this.game = game;
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 1024, 768);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		cam.update();
		
		game.batch.begin();
		game.font.setColor(new Color(Color.WHITE));
		game.font.draw(game.batch, "Click Anywhere on Screen", 512, 360);
		game.font.draw(game.batch, "To Start the Game", 512, 410);
		game.batch.end();

		if (Gdx.input.isButtonPressed(Buttons.LEFT)) {
			game.setScreen(new LevelSelectScreen(game));
			dispose();
		}
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
