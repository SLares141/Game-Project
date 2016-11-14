public class Consumable extends Item {

	protected int restoreAmt;
	
	public Consumable(String name, int restoreAmt) {
		super(name);
		this.restoreAmt = restoreAmt;
	}

	public void use(Character c) {
		if(c != null)
			c.setHealth(c.getHealth() + restoreAmt);
	}	
	
}
