package projectiles;

import util.DebuffStatusType;

import com.badlogic.gdx.graphics.Texture;

import enemies.Enemy;

public class SlowBullet extends Projectile {
	public SlowBullet() {
		this.speed = 5;
		this.sprite = "bullets/iceBullet.png";
		this.name = "Slow Bullet";
		this.debuff = DebuffStatusType.SLOW;
		this.debuffDuration = 3; //Seconds
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
