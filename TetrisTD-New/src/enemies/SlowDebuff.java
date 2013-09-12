package enemies;

import util.DebuffStatusType;

public class SlowDebuff extends Debuff {

	public SlowDebuff(float duration, float strength) {
		this.duration = duration;
		this.buffStrength = strength;
		this.debuff = DebuffStatusType.SLOW;
	}
	
	@Override
	public void updateDebuff(Debuff newDebuff) {
		//Takes the stronger of the slows and the longer duration. Otherwise it will simply refresh.
		if (this.debuff == newDebuff.getDebuff()) {
			float duration = Math.max(this.duration, newDebuff.getDuration());
			float strength = Math.max(this.buffStrength, newDebuff.getDuration());
			
			this.duration = duration;
			this.buffStrength = strength;
		}
	}

}
