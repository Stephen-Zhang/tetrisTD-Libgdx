package enemies;

import util.DebuffStatusType;

public abstract class Debuff {
	protected DebuffStatusType debuff;
	protected float duration;
	protected float buffStrength;
	
	public abstract void updateDebuff(Debuff newDebuff);
	
	public float getDuration() {
		return this.duration;
	}
	public float getStrength() {
		return this.buffStrength;
	}
	public DebuffStatusType getDebuff() {
		return this.debuff;
	}
	public void updateDuration(float delta) {
		this.duration -= delta;
	}
}
