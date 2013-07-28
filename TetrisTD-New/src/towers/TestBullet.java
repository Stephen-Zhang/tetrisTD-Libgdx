package towers;

import projectiles.Projectile;

import com.badlogic.gdx.graphics.Texture;

import enemies.Enemy;

public class TestBullet extends Projectile {
	public TestBullet(Enemy e, int[] spawnPoint) {
		this.target = e;
		this.pos = spawnPoint;
		this.pos[0] += 12;
		this.pos[1] += 12;
		this.speed = 20;
		this.damage = 1;
		this.sprite = new Texture("bullets/testBullet.png");
	}
}
