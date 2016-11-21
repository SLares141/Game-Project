

// each particular armor item may need to extend this
public class Armor extends Item {
	
	private int defModifier, defMagicModifier;
	
	public Armor(String name, int defModifier, int defMagicModifier) {
		super(name);
		this.defModifier = defModifier;
		this.defMagicModifier = defMagicModifier;
	}
	
	public int getDefMod() {
		return defModifier;
	}
	public int getDefMagMod() {
		return defMagicModifier;
	}
}
