import java.awt.image.BufferedImage;

/**
 * This class is currently meant to get a model of the stats the main character has.
 * This may be extended to fit the roles of "enemies" and other characters besides
 * the main character.  I'm focusing focusing mainly on combat here, it may not necessarily
 * have to be related to particular character that exists on the "world map."
 * @author JamesHowe
 *
 */
public class Character {
	
	protected BufferedImage _sprite;
	protected BufferedImage _menuSprite;
	private int _strStat, _defStat, _strMagic, _defMagic;
	private int _totalHealth, _currentHealth, _totalMagic, _currentMagic;
	private int _levelStat, _expStat;
	private boolean _isDead;
	private Weapon _weapon;
	
	public Character(){
		_strStat = 			1;
		_defStat = 			0;
		_strMagic = 		3;
		_defMagic = 		1;
		_totalHealth = 		10;
		_currentHealth = 	_totalHealth;
		_totalMagic = 		5;
		_currentMagic = 	_totalMagic;
		_levelStat = 		1;
		_expStat = 			0;
		_isDead = 			false;
		_weapon = 			null;
	}
	
	// getters
	public int getStr()			{ return _strStat; }
	public int getDef()			{ return _defStat; }
	public int getStrMag() 		{ return _strMagic; }
	public int getMagicDef()	{ return _defMagic; }
	public int getTotalHealth() { return _totalHealth; }
	public int getHealth() 		{ return _currentHealth; }
	public int getTotalMagic()  { return _totalMagic; } 
	public int getMagic() 		{ return _currentMagic; }
	public int getLevel()		{ return _levelStat; }
	public int getExp() 		{ return _expStat; }
	public boolean getIsDead()	{ return _isDead; }
	public Weapon getWeapon()	{ return _weapon; }
	
	public BufferedImage getSprite() 	{ return _sprite; }
	public BufferedImage getMenuSprite() { return _menuSprite; }
	
	// setters
	public void setStr(int i){ _strStat = i;}
	public void setDef(int i) {_defStat = i;}
	public void setStrMag(int i){_strMagic = i;}
	public void setdefMag(int i){_defMagic = i;}
	public void setHealth (int i) { _currentHealth = i; }
	public void setMagic (int i ) { _currentMagic = i; } 
	public void setLevel(int i){ _levelStat = i;}
	public void setExp (int i) { _expStat = i; }
	public void setIsDead(boolean b){_isDead = b;}
	public void setWeapon(Weapon w){_weapon = w;}
	
	// this can serve as a loop condition during battle to determine a game over or victory
	public boolean isDead() {
		if (_currentHealth <= 0)
			return true;
		return false;
	}
	
	int tempHealth;
	public void attack(Character c) {
		tempHealth = c.getHealth() - _strStat + c.getDef();
		c.setHealth(tempHealth);
	}
	
	// each magic attack may need to be its own object with its own stats and required magic points
	public void magicAttack(Character c) {
		tempHealth = c.getHealth() - _strMagic + c.getMagicDef();
		c.setHealth(tempHealth);
		_currentMagic -= 2;
	}
}
