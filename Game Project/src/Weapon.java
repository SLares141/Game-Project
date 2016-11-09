
// each particular weapon may need to extend this
public class Weapon extends Item {
	
	private int _strStat;
	
	public Weapon(String name, int strStat) {
		super(name);
		_strStat = strStat;
	}

}
