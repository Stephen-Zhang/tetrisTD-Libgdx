package enemies;

import util.DebuffStatusType;

public class NoDebuff extends Debuff {
	
	public NoDebuff() {
		this.debuff = DebuffStatusType.NONE;
		this.buffStrength = 0;
		this.duration = 0;
	}

	@Override
	public void updateDebuff(Debuff newDebuff) {}

}
