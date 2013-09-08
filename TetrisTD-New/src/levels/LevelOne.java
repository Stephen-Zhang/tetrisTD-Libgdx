package levels;

import java.awt.Point;

import towers.base.TowerType;

import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.Array;

import enemies.Enemy;
import enemies.TankEnemy;
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
		tOne.add(new Integer(1500));
		tOne.add(new Integer(2000));
		tOne.add(new Integer(2500));
		tOne.add(new Integer(3000));

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
		
		Array<Enemy> wThreeEnemies = new Array<Enemy>();
		wThreeEnemies.add(new TankEnemy(startingLoc.clone(), new Array<Point>(waypts)));
		wThreeEnemies.add(new TestEnemy(startingLoc.clone(), new Array<Point>(waypts)));
		wThreeEnemies.add(new TestEnemy(startingLoc.clone(), new Array<Point>(waypts)));
		wThreeEnemies.add(new TestEnemy(startingLoc.clone(), new Array<Point>(waypts)));
		wThreeEnemies.add(new TestEnemy(startingLoc.clone(), new Array<Point>(waypts)));
		wThreeEnemies.add(new TankEnemy(startingLoc.clone(), new Array<Point>(waypts)));
		wThreeEnemies.add(new TestEnemy(startingLoc.clone(), new Array<Point>(waypts)));
		wThreeEnemies.add(new TestEnemy(startingLoc.clone(), new Array<Point>(waypts)));
		wThreeEnemies.add(new TestEnemy(startingLoc.clone(), new Array<Point>(waypts)));
		wThreeEnemies.add(new TankEnemy(startingLoc.clone(), new Array<Point>(waypts)));
		wThreeEnemies.add(new TankEnemy(startingLoc.clone(), new Array<Point>(waypts)));
		wThreeEnemies.add(new TestEnemy(startingLoc.clone(), new Array<Point>(waypts)));
		wThreeEnemies.add(new TankEnemy(startingLoc.clone(), new Array<Point>(waypts)));
		wThreeEnemies.add(new TestEnemy(startingLoc.clone(), new Array<Point>(waypts)));
		wThreeEnemies.add(new TankEnemy(startingLoc.clone(), new Array<Point>(waypts)));
		wThreeEnemies.add(new TestEnemy(startingLoc.clone(), new Array<Point>(waypts)));
		wThreeEnemies.add(new TankEnemy(startingLoc.clone(), new Array<Point>(waypts)));
		
		Array<Integer> tThree = new Array<Integer>();
		tThree.add(new Integer(0));
		tThree.add(new Integer(1000));
		tThree.add(new Integer(1500));
		tThree.add(new Integer(2000));
		tThree.add(new Integer(2500));
		tThree.add(new Integer(3500));
		tThree.add(new Integer(4000));
		tThree.add(new Integer(4500));
		tThree.add(new Integer(5000));
		tThree.add(new Integer(6000));
		tThree.add(new Integer(7000));
		tThree.add(new Integer(7300));
		tThree.add(new Integer(7600));
		tThree.add(new Integer(7900));
		tThree.add(new Integer(8300));
		tThree.add(new Integer(8500));
		tThree.add(new Integer(8700));
		
		Array<TowerType> ttOne = new Array<TowerType>();
		ttOne.add(TowerType.TEST_TOWER);
		
		Array<TowerType> ttTwo = new Array<TowerType>();
		ttTwo.add(TowerType.TEST_TOWER);
		ttTwo.add(TowerType.TEST_AOE_TOWER);
		
		Array<TowerType> ttThree = new Array<TowerType>();
		ttThree.add(TowerType.TEST_TOWER);
		ttThree.add(TowerType.TEST_AOE_TOWER);
		ttThree.add(TowerType.ATK_SPEED_TOWER);
		
		Wave testEmpty = new Wave(new Array<Enemy>(), new Array<Integer>(), new Array<TowerType>(), 0, "Press Enter or Space to move onto the next text bubble. You may also " +
				"click on the bubble itself to close it.");
		
		Wave wOne = new Wave(wOneEnemies, tOne, ttOne, 0, "Welcome to Tetris TD Alpha Version .00! This game is a barely playable game but here is the first test level! " +
				"Please build the first tower available to you and destroy your enemies! You may build this tower by clicking on the icon or pressing t " +
				"on your keyboard. Good luck!");
		
		Wave wTwo = new Wave(wTwoEnemies, tTwo, ttTwo, 15000, "Phew. You beat the first wave! but how are you going to deal with this next massive swarm of enemies? " +
				"Hint: You should build some Area of Effect Towers! Good luck!");
		
		Wave wThree = new Wave(wThreeEnemies, tThree, ttThree, 30000, "Looking good! Only one more wave for this level! Can you defeat this mix of tanks and woodland treetrunks?");
		
		waves = new Array<Wave>();
		waves.add(testEmpty);
		waves.add(wOne);
		waves.add(wTwo);
		waves.add(wThree);
	}
}
