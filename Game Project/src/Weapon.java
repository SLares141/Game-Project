
// each particular weapon may need to extend this
public class Weapon extends Item {
	
	private int strModifier;
	
	public Weapon(String name, int strModifier) {
		super(name);
		this.strModifier = strModifier;
	}
	
	public int getStrModifier() {
		return strModifier;
	}
}
