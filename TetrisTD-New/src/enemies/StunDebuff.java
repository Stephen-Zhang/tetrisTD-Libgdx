package enemies;

import util.DebuffStatusType;


public class StunDebuff extends Debuff {

	public StunDebuff(float duration, float strength) {
		this.buffStrength = strength;
		this.duration = duration;
		this.debuff = DebuffStatusType.STUN;
	}
	
	@Override
	public void updateDebuff(Debuff newDebuff) {
		//Do nothing. stuns should not stack or refresh. Once it runs out it can be reapplied. cannot chain stun.
	}
	
}
