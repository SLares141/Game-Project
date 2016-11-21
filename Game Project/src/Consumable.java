public class Consumable extends Item {

	private int restoreAmt;
	
	public Consumable(String name, int restoreAmt) {
		super(name);
		this.restoreAmt = restoreAmt;
	}
	
	public int getRestoreAmt() {
		return restoreAmt;
	}
}
