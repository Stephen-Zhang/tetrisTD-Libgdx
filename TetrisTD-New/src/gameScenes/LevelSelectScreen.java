package gameScenes;

import java.util.ArrayList;

import util.MenuInputProcessor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class LevelSelectScreen implements Screen {

	final tetrisTD game;
	
	ArrayList<String> levels;	//store level names for display purposes
	int maxX = 200, maxY = 500;	//set the top left corner of the level list
	int boxheight = 30, boxwidth = 100;	//set dimensions of each level display
	int padding = 10;	//padding between levels entries
	
	OrthographicCamera cam;
	InputProcessor gameProcessor;
	
	public LevelSelectScreen(final tetrisTD game) {
		this.game = game;
		this.levels = new ArrayList<String>();
		loadLevels();
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 1024, 768);
		
		gameProcessor = new MenuInputProcessor(game);
		Gdx.input.setInputProcessor(gameProcessor);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		cam.update();
		
		ShapeRenderer textBox = new ShapeRenderer();
		textBox.setColor(new Color(Color.WHITE));
		textBox.begin(ShapeType.Line);
		textBox.rect(200, 600, 600, 100);
		textBox.end();
		
		game.batch.begin();
		game.font.setColor(new Color(Color.YELLOW));
		game.font.draw(game.batch, "LEVEL SELECT", 460, 655 );
		game.batch.end();
		
		renderLevelSelect();
		
		if (Gdx.input.isButtonPressed(Buttons.LEFT)) {
//			System.out.println("Mouse at location: " + this.game.currMouseLoc[0] + "," + this.game.currMouseLoc[1]);
//			System.out.println("level selected: " + selectedLevel(this.game.currMouseLoc[0], this.game.currMouseLoc[1]));
			int level = selectedLevel(this.game.currMouseLoc[0], this.game.currMouseLoc[1]);
			
			if (level >= 0) {
				game.setScreen(new GameScreen(game, level));
				dispose();
			}	
		}
	}

	/**
	 * Reads level config directory to retrieve number of available levels.
	 * Stores level names in 'levels' class variable
	 */
	public void loadLevels() {
		levels.add("Level 1");
		levels.add("Level 2");
	}
	
	/**
	 * Renders level names on screen
	 */
	public void renderLevelSelect() {	
		game.batch.begin();
		game.font.setColor(new Color(Color.WHITE));
		
		for (int i = 0; i < levels.size(); ++i) {
			game.font.draw(game.batch, levels.get(i), maxX + padding, maxY - i*(padding + boxheight) );
		}

		game.batch.end();
	}
	
	/**
	 * Checks mouse location and returns the selected level.
	 * If no level is selected, returns -1
	 * @param mouseX
	 * @param mouseY
	 * @return
	 */
	public int selectedLevel(int mouseX, int mouseY) {
		//check x dimension first
		if (mouseX < (maxX + padding) || mouseX > (maxX + padding + boxwidth))
			return -1;
		int numLevels = levels.size();

		//check y dimension
		if (mouseY > maxY || mouseY < (maxY - levels.size()*(padding + boxheight)))
			return -1;
		
		int level = (maxY - mouseY)/(padding + boxheight) + 1;
		return level;
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
