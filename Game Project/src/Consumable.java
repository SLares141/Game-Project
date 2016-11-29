public class Consumable extends Item {

	private int restoreAmt;
	private boolean restoresHP;
	
	public Consumable(String name, int restoreAmt, boolean restoresHP) {
		super(name);
		this.restoreAmt = restoreAmt;
		this.restoresHP = restoresHP;
	}
	
	public int getRestoreAmt() {
		return restoreAmt;
	}
	public boolean restoresHP() {
		return restoresHP;
	}
}
