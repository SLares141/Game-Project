import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;


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
	private boolean _isDead, _usedDef;
	private Weapon _weapon;

	private String _weap;

	private Coordinate _loc;
	private int _currentMap;

	
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
		_usedDef =			false;
		_weapon = 			null;
	}
	
	// getters
	public int getStr()			{ return _strStat; }
	public int getDef()			{ return _defStat; }
	public int getMagicStr()	{ return _strMagic; }
	public int getMagicDef()	{ return _defMagic; }
	public int getTotalHealth() { return _totalHealth; }
	public int getHealth() 		{ return _currentHealth; }
	public int getTotalMagic()  { return _totalMagic; } 
	public int getMagic() 		{ return _currentMagic; }

	public Coordinate getLocation(){
		return _loc;
	}
	public int getMap(){
		return _currentMap;
	}
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
	public void setWeapon(String w){_weap = w;}
	public void setLocation(Coordinate c){
		_loc = c;
	}
	public void setMap(int s){
		_currentMap = s;
	}
	public void setSprite(BufferedImage bi){
		_sprite = bi;
	}
	
	// this can serve as a loop condition during battle to determine a game over or victory
	public boolean isDead() {
		if (_currentHealth <= 0)
			return true;
		return false;
	}
	
	// this indicates if "defend" was used on the previous turn, to note if _defStat should be restored
	public boolean usedDefend() { return _usedDef; }
	
	int tempHealth;
	public void attack(Character c) {
		if (c.usedDefend()) {
			tempHealth = (int) (c.getHealth() - Math.ceil((_strStat + c.getDef()) / 2));
			c.setHealth(tempHealth);
		} else {
			tempHealth = c.getHealth() - _strStat + c.getDef();
			c.setHealth(tempHealth);
		}
	}
	
	// special attack does double damage
	public boolean specialAttack(Character c) {
		Random r = new Random();
		if (r.nextInt(100) >= 66){ // successfully special attack 33% of the time
			System.out.println("Special Attack Hits");
			if (c.usedDefend()) {
				tempHealth = (int) (c.getHealth() - Math.ceil((_strStat * 2) + c.getDef()) / 2);
				c.setHealth(tempHealth);
				c.restoreDef();
			} else {
				tempHealth = c.getHealth() - (_strStat * 2) + c.getDef();
				c.setHealth(tempHealth);
			}
			return true;
		} else {
			System.out.println("Special Attack Missed");
			return false;
		}
	}
	
	// each magic attack may need to be its own object with its own stats and required magic points
	public void magicAttack(Character c) {
		if (c.usedDefend()) {
			tempHealth = (int) (c.getHealth() - Math.ceil((_strMagic + c.getMagicDef()) / 2));
			c.setHealth(tempHealth);
			_currentMagic -= 2;
			c.restoreDef();
		} else {
			tempHealth = c.getHealth() - _strMagic + c.getMagicDef();
			c.setHealth(tempHealth);
			_currentMagic -= 2;
		}
	}
	
	public void defend() { _usedDef = true; }
	public void restoreDef() { _usedDef = false; }


}
