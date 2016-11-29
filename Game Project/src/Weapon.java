
// each particular weapon may need to extend this
public class Weapon extends Item {
	
	private int _strStat, _value;
	
	public Weapon(String name, int strStat) {
		super(name);
		_strStat = strStat;
	}
	
	public int getStrStat() { return _strStat; }

}
