package projectiles;

import enemies.Enemy;
import enemies.NoDebuff;

public class TestBullet extends Projectile {
	public TestBullet() {
		this.speed = 10;
		this.sprite = "bullets/testBullet.png";
		this.name = "Test Bullet";
		this.debuff= new NoDebuff();
	}
	
	public TestBullet(Enemy e, int damage, int[] spawnPoint) {
		this();
		this.target = e;
		this.pos = spawnPoint;
		this.pos[0] += 12;
		this.pos[1] += 12;
		this.damage = damage;
	}
}
