package projectiles;

import util.DebuffStatusType;

import com.badlogic.gdx.graphics.Texture;

import enemies.Enemy;

public class TestBullet extends Projectile {
	public TestBullet() {
		this.speed = 10;
		this.sprite = "bullets/testBullet.png";
		this.name = "Test Bullet";
		this.debuff = DebuffStatusType.STUN;
		this.debuffDuration = .3;
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
