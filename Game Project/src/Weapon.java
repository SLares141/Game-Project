
// each particular weapon may need to extend this
public class Weapon extends Item {
	

	private int _strStat, _value;

	
	public Weapon(String name, int strModifier) {
		super(name);
		_strStat = strModifier;
	}
	
	
	//getStrStat
	public int getStrModifier() { return _strStat; }


}
