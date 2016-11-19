

// each particular armor item may need to extend this
public class Armor extends Item {
	
	private int _defStat, _defMagic, _value;
	
	public Armor(String name, int defStat, int defMagic) {
		super(name);
		_defStat = defStat;
		_defMagic = defMagic;
	}
	
	public int getDefStat() { return _defStat; }
	public int getDefMagic() { return _defMagic; }

}
