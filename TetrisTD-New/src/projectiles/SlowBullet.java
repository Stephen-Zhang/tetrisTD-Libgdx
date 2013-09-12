package projectiles;

import enemies.Enemy;
import enemies.SlowDebuff;

public class SlowBullet extends Projectile {
	public SlowBullet() {
		this.speed = 5;
		this.sprite = "bullets/iceBullet.png";
		this.name = "Slow Bullet";
		this.debuff = new SlowDebuff((float)3, (float).3);
	}
	
	public SlowBullet(Enemy e, int damage, int[] spawnPoint) {
		this();
		this.target = e;
		this.pos = spawnPoint;
		this.pos[0] += 12;
		this.pos[1] += 12;
		this.damage = damage;
	}
}
