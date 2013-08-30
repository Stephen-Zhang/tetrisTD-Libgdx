package util;

import java.util.HashMap;

import towers.attack.TestAoeTower;
import towers.attack.TestTower;
import towers.status.AtkSpeedTower;

import com.badlogic.gdx.graphics.Texture;

import enemies.TankEnemy;
import enemies.TestEnemy;

public class TextureManager {
	private HashMap<String, Texture> textureMap = new HashMap<String, Texture>();
	
	public TextureManager() {
		//Towers
		textureMap.put("TestTower_Sprite", new Texture((new TestTower().getSpritePath())));
		textureMap.put("TestTower_Icon", new Texture((new TestTower().getIconPath())));
		textureMap.put("AtkSpeedTower_Sprite", new Texture((new AtkSpeedTower().getSpritePath())));
		textureMap.put("AtkSpeedTower_Icon", new Texture((new AtkSpeedTower().getIconPath())));
		textureMap.put("TestAoeTower_Sprite", new Texture((new TestAoeTower().getSpritePath())));
		textureMap.put("TestAoeTower_Icon", new Texture((new TestAoeTower().getIconPath())));
		
		//Enemies
		textureMap.put("TestEnemy", new Texture((new TestEnemy().getSpritePath())));
		textureMap.put("TankEnemy", new Texture((new TankEnemy().getSpritePath())));

		
		//Bullets
		
	}
	
	public Texture getTextureFromMap(String key) {
		return textureMap.get(key);
	}
}
