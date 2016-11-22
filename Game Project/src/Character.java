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
	private int _levelStat, _expStat, _money;
	private boolean _isDead, _usedDef;
	private Weapon _weapon;
	private Armor _armor;

	private String _weap;
	private String _armo;

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
		_money = 			0;
		_isDead = 			false;
		_usedDef =			false;
		_weapon = 			null;
		_armor = 			null;
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
	public int getMoney()		{ return _money; }
	public boolean getIsDead()	{ return _isDead; }
	public Weapon getWeapon()	{ return _weapon; }
	public Armor getArmor()		{ return _armor; }
	public BufferedImage getSprite() 	{ return _sprite; }
	public BufferedImage getMenuSprite() { return _menuSprite; }


	
	// setters
	public void setStr(int i){ _strStat = i; }
	public void setDef(int i) { _defStat = i; }
	public void setStrMag(int i){ _strMagic = i; }
	public void setdefMag(int i){ _defMagic = i; }
	public void setHealth(int i) { _currentHealth = i; }
	public void setMagic(int i ) { _currentMagic = i; } 
	public void setLevel(int i){ _levelStat = i; }
	public void setExp(int i) { _expStat = i; }
	public void setMoney(int i) { _money = i; }
	public void setIsDead(boolean b) { _isDead = b; }
	public void setWeapon(Weapon w) { _weapon = w; } // set the weapon object
	public void setArmor(Armor a) { _armor = a; } // set the armor object
	public void setWeapon(String w) { _weap = w; }
	public void setArmor(String a) { _armo = a; }
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
	
	int tempHealth, totalAttack, totalDefense;
	public void attack(Character c) {
		totalAttack = _strStat + c.getWeapon().getStrStat();
		totalDefense = c.getDef() + c.getArmor().getDefStat();
		if (c.usedDefend()) {
			if (Math.ceil((totalDefense - totalAttack) / 2) <= 0) {// make sure health does not increase after attack()
				tempHealth = (int) (c.getHealth() + Math.ceil((totalDefense - totalAttack) / 2));
				c.setHealth(tempHealth);
				c.restoreDef();
			} // else, no change
		} else {
			if (totalDefense <= totalAttack) { // make sure health does not increase after attack()
				tempHealth = c.getHealth() + (totalDefense - totalAttack);
				c.setHealth(tempHealth);
			} // else, no change
		}
	}
	
	// special attack does ~1.5x damage
	public boolean specialAttack(Character c) {
		Random r = new Random();
		if (r.nextInt(100) >= 60){ // successfully special attack 40% of the time
			System.out.println("Special Attack Hits");
			totalAttack = _strStat + c.getWeapon().getStrStat();
			totalDefense = c.getDef() + c.getArmor().getDefStat();
			if (c.usedDefend()) {
				if (((int) Math.ceil((totalDefense - totalAttack) * 1.5 / 2)) >= 0) {
					tempHealth = (int) Math.ceil((totalDefense - totalAttack) * 1.5 / 2);
					c.setHealth(tempHealth);
					c.restoreDef();
				} // else, no change
			} else {
				if (((int) Math.ceil((totalDefense - totalAttack) * 1.5)) >= 0) {
					tempHealth = c.getHealth() + (int) Math.ceil((totalDefense - totalAttack) * 1.5);
					c.setHealth(tempHealth);
					c.restoreDef();
				} // else, no change
			}
			return true;
		} else {
			System.out.println("Special Attack Missed");
			return false;
		}
	}
	
	// each magic attack may need to be its own object with its own stats and required magic points
	public void magicAttack(Character c) {
		totalAttack = _strMagic; //+ c.getWeapon().get       // should weapon boost magic attack?...
		totalDefense = c.getMagicDef() + c.getArmor().getDefMagic();
		if (c.usedDefend()) {
			if (Math.ceil((totalDefense - totalAttack) / 2) <= 0) {// make sure health does not increase after attack()
				tempHealth = (int) (c.getHealth() + Math.ceil((totalDefense - totalAttack) / 2));
				c.setHealth(tempHealth);
				_currentMagic -= 2;
				c.restoreDef();
			} // else, no change
		} else {
			if (totalDefense <= totalAttack) { // make sure health does not increase after attack()
				tempHealth = c.getHealth() + (totalDefense - totalAttack);
				c.setHealth(tempHealth);
				_currentMagic -= 2;
			} // else, no change
		}
	}
	
	public void defend() { _usedDef = true; }
	public void restoreDef() { _usedDef = false; }


}
