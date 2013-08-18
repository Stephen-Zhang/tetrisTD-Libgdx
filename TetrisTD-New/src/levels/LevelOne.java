package levels;

import java.awt.Point;

import towers.base.TowerType;

import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.Array;

import enemies.Enemy;
import enemies.TestEnemy;

public class LevelOne extends Level {

	public LevelOne() {

		levelMap = new TmxMapLoader().load("levels/maps/levelOne.tmx");
		
		startingLoc[0] = 0;
		startingLoc[1] = 12*32;
		
		waypts.add(new Point(4*32, 12*32));
		waypts.add(new Point(4*32, 8*32));
		waypts.add(new Point(21*32, 8*32));
		waypts.add(new Point(21*32, 18*32));
		waypts.add(new Point(26*32, 18*32));
		
		Array<Enemy> wOneEnemies = new Array<Enemy>();
		wOneEnemies.add(new TestEnemy(startingLoc.clone(), new Array<Point>(waypts)));
		wOneEnemies.add(new TestEnemy(startingLoc.clone(), new Array<Point>(waypts)));
		wOneEnemies.add(new TestEnemy(startingLoc.clone(), new Array<Point>(waypts)));
		wOneEnemies.add(new TestEnemy(startingLoc.clone(), new Array<Point>(waypts)));
		wOneEnemies.add(new TestEnemy(startingLoc.clone(), new Array<Point>(waypts)));
		wOneEnemies.add(new TestEnemy(startingLoc.clone(), new Array<Point>(waypts)));
		wOneEnemies.add(new TestEnemy(startingLoc.clone(), new Array<Point>(waypts)));
		
		Array<Integer> tOne = new Array<Integer>();
		tOne.add(new Integer(0));
		tOne.add(new Integer(500));
		tOne.add(new Integer(1000));
		tOne.add(new Integer(1200));
		tOne.add(new Integer(1400));
		tOne.add(new Integer(1600));
		tOne.add(new Integer(2000));

		Array<Enemy> wTwoEnemies = new Array<Enemy>();
		wTwoEnemies.add(new TestEnemy(startingLoc.clone(), new Array<Point>(waypts)));
		wTwoEnemies.add(new TestEnemy(startingLoc.clone(), new Array<Point>(waypts)));
		wTwoEnemies.add(new TestEnemy(startingLoc.clone(), new Array<Point>(waypts)));
		wTwoEnemies.add(new TestEnemy(startingLoc.clone(), new Array<Point>(waypts)));
		wTwoEnemies.add(new TestEnemy(startingLoc.clone(), new Array<Point>(waypts)));
		wTwoEnemies.add(new TestEnemy(startingLoc.clone(), new Array<Point>(waypts)));
		wTwoEnemies.add(new TestEnemy(startingLoc.clone(), new Array<Point>(waypts)));
		wTwoEnemies.add(new TestEnemy(startingLoc.clone(), new Array<Point>(waypts)));
		wTwoEnemies.add(new TestEnemy(startingLoc.clone(), new Array<Point>(waypts)));
		wTwoEnemies.add(new TestEnemy(startingLoc.clone(), new Array<Point>(waypts)));
		wTwoEnemies.add(new TestEnemy(startingLoc.clone(), new Array<Point>(waypts)));
		wTwoEnemies.add(new TestEnemy(startingLoc.clone(), new Array<Point>(waypts)));
		wTwoEnemies.add(new TestEnemy(startingLoc.clone(), new Array<Point>(waypts)));
		wTwoEnemies.add(new TestEnemy(startingLoc.clone(), new Array<Point>(waypts)));
		wTwoEnemies.add(new TestEnemy(startingLoc.clone(), new Array<Point>(waypts)));
		wTwoEnemies.add(new TestEnemy(startingLoc.clone(), new Array<Point>(waypts)));
		wTwoEnemies.add(new TestEnemy(startingLoc.clone(), new Array<Point>(waypts)));
		
		Array<Integer> tTwo = new Array<Integer>();
		tTwo.add(new Integer(0));
		tTwo.add(new Integer(100));
		tTwo.add(new Integer(200));
		tTwo.add(new Integer(300));
		tTwo.add(new Integer(400));
		tTwo.add(new Integer(500));
		tTwo.add(new Integer(600));
		tTwo.add(new Integer(700));
		tTwo.add(new Integer(800));
		tTwo.add(new Integer(900));
		tTwo.add(new Integer(1000));
		tTwo.add(new Integer(1100));
		tTwo.add(new Integer(1200));
		tTwo.add(new Integer(1300));
		tTwo.add(new Integer(1400));
		tTwo.add(new Integer(1500));
		tTwo.add(new Integer(1600));
		
		Array<TowerType> ttOne = new Array<TowerType>();
		ttOne.add(TowerType.TEST_TOWER);
		
		Array<TowerType> ttTwo = new Array<TowerType>();
		ttTwo.add(TowerType.TEST_AOE_TOWER);
		
		Wave testEmpty = new Wave(new Array<Enemy>(), new Array<Integer>(), new Array<TowerType>(), 0, "Press Enter or Space to move onto the next text bubble. You may also " +
				"click on the bubble itself to close it.");
		
		Wave wOne = new Wave(wOneEnemies, tOne, ttOne, 0, "Welcome to Tetris TD Alpha Version .00! This game is a barely playable game but here is the first test level! " +
				"Please build the first tower available to you and destroy your enemies! You may build this tower by clicking on the icon or pressing t " +
				"on your keyboard. Good luck!");
		
		Wave wTwo = new Wave(wTwoEnemies, tTwo, ttTwo, 15000, "Phew. You beat the first wave! but how are you going to deal with this next massive swarm of enemies? " +
				"Hint: You should build some Area of Effect Towers that have now been enabled. I've disabled the original single shot tower. Good luck!");
		
		waves = new Array<Wave>();
		waves.add(testEmpty);
		waves.add(wOne);
		waves.add(wTwo);
	}
}
