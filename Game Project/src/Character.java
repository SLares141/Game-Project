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
	
	protected BufferedImage _sprite, _menuSprite, _smallMenuSprite;
	private int _strStat, _defStat, _strMagic, _defMagic;
	private int _totalHealth, _currentHealth, _totalMagic, _currentMagic;
	private int _levelStat, _expStat, _money;
	private boolean _isDead, _usedDef;
	private Weapon _weapon;
	private Armor _armor;
	private String _name;
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
		_totalMagic = 		10;
		_currentMagic = 	_totalMagic;
		_levelStat = 		1;
		//_expStat = 			0;
		_money = 			0;
		_isDead = 			false;

		_usedDef =		false;
		_weapon = 			null;
		_armor = 			null;
		_name = "Insert name here";

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
	public String getName() 	{ return _name; }
	
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

	public String getWeapName() { return _weapon.getName(); }
	public Armor getArmor()		{ return _armor; }
	public String getArmName()  { return _armor.getName(); }

	public BufferedImage getSprite() 	{ return _sprite; }
	public BufferedImage getMenuSprite() { return _menuSprite; }
	public BufferedImage getSmallMenuSprite() { return _smallMenuSprite; }
	
	// setters

	public void setStr(int i){ _strStat = i; }
	public void setDef(int i) { _defStat = i; }
	public void setStrMag(int i){ _strMagic = i; }
	public void setDefMag(int i){ _defMagic = i; }
	public void setHealth(int i) { _currentHealth = i; }
	public void setTotalHealth(int i) { _totalHealth = i; }
	public void setMagic(int i ) { _currentMagic = i; } 
	public void setTotalMagic(int i) { _totalMagic = i; }
	public void setLevel(int i){ _levelStat = i; }
	public void setExp(int i) { _expStat = i; }
	public void setMoney(int i) { _money = i; }
	public void setIsDead(boolean b) { _isDead = b; }
	public void setWeapon(Weapon w) { _weapon = w; } // set the weapon object
	public void setArmor(Armor a) { _armor = a; } // set the armor object
	public void setWeapName(String w) { _weapon.setName(w); }
	public void setArmName(String a) { _armor.setName(a); }
	
	public void setLocation(Coordinate c) { _loc = c; }
	public void setMap(int s) { _currentMap = s; }
	public void setSprite(BufferedImage bi) { _sprite = bi; }

	
	// this can serve as a loop condition during battle to determine a game over or victory
	public boolean isDead() {
		if (_currentHealth <= 0)
			return true;
		return false;
	}
	
	// this indicates if "defend" was used on the previous turn, to note if _defStat should be restored
	public boolean usedDefend() { return _usedDef; }
	
	int tempHealth, totalAttack, totalDefense, damageDone;
	// regular attack favors defender, so it uses floor
	public int attack(Character c) {
		damageDone = 0;
		totalAttack = _strStat + this.getWeapon().getStrModifier();
		totalDefense = c.getDef() + c.getArmor().getDefMod();
		if (c.usedDefend()) {
			if (Math.floor((totalAttack - totalDefense) / 2) >= 0) {// make sure health does not increase after attack()
				damageDone = (int) (Math.floor((totalAttack - totalDefense) / 2));
				tempHealth = c.getHealth() - damageDone;
				c.setHealth(tempHealth);
				c.restoreDef();
			} // else, no change
		} else {
			if (totalDefense <= totalAttack) { // make sure health does not increase after attack()
				damageDone = totalAttack - totalDefense;
				tempHealth = c.getHealth() - damageDone;
				c.setHealth(tempHealth);
			} // else, no change
		}
		return damageDone;
	}
	
	// special attack does ~1.5x damage
	// special attack favors attacker, so it uses ceil
	public int specialAttack(Character c) {
		damageDone = 0;
		Random r = new Random();
		if (r.nextInt(100) >= 60){ // successfully special attack 40% of the time
			System.out.println("Special Attack Hits");
			totalAttack = _strStat + this.getWeapon().getStrModifier();
			totalDefense = c.getDef() + c.getArmor().getDefMod();
			if (c.usedDefend()) {
				if (Math.floor((totalAttack - totalDefense) * 1.5 / 2) >= 0) {
					damageDone = (int) Math.ceil((totalAttack - totalDefense) * 1.5 / 2);
					tempHealth = c.getHealth() - damageDone;
					c.setHealth(tempHealth);
					c.restoreDef();
				} // else, no change
			} else {
				if (Math.ceil((totalAttack - totalDefense) * 1.5) >= 0) {
					damageDone = (int) Math.ceil((totalAttack - totalDefense) * 1.5);
					tempHealth = c.getHealth() - damageDone;
					c.setHealth(tempHealth);
					c.restoreDef();
				} // else, no change
			}
			return damageDone;
		} else {
			System.out.println("Special Attack Missed");
			return -1;
		}
	}
	
	// each magic attack may need to be its own object with its own stats and required magic points
	// magic attack favors defender, so it uses floor
	public int magicAttack(Character c) {
		damageDone = 0;
		totalAttack = _strMagic; //+ this.getWeapon().get       // should weapon boost magic attack?...
		totalDefense = c.getMagicDef() + c.getArmor().getDefMagMod();
		if (c.usedDefend()) {
			if (Math.floor((totalAttack - totalDefense) / 2) >= 0) {// make sure health does not increase after attack()
				damageDone = (int) (Math.floor((totalAttack - totalDefense) / 2));
				tempHealth = c.getHealth() - damageDone;
				c.setHealth(tempHealth);
				_currentMagic -= 2;
				c.restoreDef();
			} // else, no change
		} else {
			if (totalDefense <= totalAttack) { // make sure health does not increase after attack()
				damageDone = totalAttack - totalDefense;
				tempHealth = c.getHealth() - damageDone;
				c.setHealth(tempHealth);
				_currentMagic -= 2;
			} // else, no change

		}
		return damageDone;
	}
	

	public boolean hasWeapon() {
		if(_weapon != null)
			return true;
		else
			return false;
	}
	public boolean hasArmor() {
		if(_armor != null)
			return true;
		else
			return false;
	}
	
	public void use(Consumable cons) {
		if(cons.restoresHP()) {
			if((_totalHealth - _currentHealth) > cons.getRestoreAmt()) 
				_currentHealth += cons.getRestoreAmt();
			else
				_currentHealth = _totalHealth;
		}
		else {
			if((_totalMagic - _currentMagic) > cons.getRestoreAmt()) 
				_currentMagic += cons.getRestoreAmt();
			else
				_currentMagic = _totalMagic;
		}
	}
	public void defend() { _usedDef = true; }
	public void restoreDef() { _usedDef = false; }


	
	public void equip(Weapon w) {
		_weapon = w;
		//weapon modifier function

	}
	public void equip(Armor a) {
		_armor = a;
		//armor modifier functions
	}
}
