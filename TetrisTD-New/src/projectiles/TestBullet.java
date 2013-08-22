package projectiles;

import com.badlogic.gdx.graphics.Texture;

import enemies.Enemy;

public class TestBullet extends Projectile {
	public TestBullet(Enemy e, int damage, int[] spawnPoint) {
		this.target = e;
		this.pos = spawnPoint;
		this.pos[0] += 12;
		this.pos[1] += 12;
		this.speed = 10;
		this.damage = damage;
		this.sprite = new Texture("bullets/testBullet.png");
	}
}
