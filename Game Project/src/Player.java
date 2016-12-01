import java.awt.List;
import java.io.BufferedReader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;

import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Player extends Character {

	private String _name;

	public boolean loadplayer0 = true;
	BufferedImage sprite = null;
	private String spaddress;
	private String weaponeq;
	//private boolean lostlastbattle;
	private boolean _usedDefend, _beatBoss; 
	private int _enemyLevel, _exp4NextLvl;
	
	public Player() {
		_name = "Straw";
		_usedDefend = false;
		_beatBoss = false;
	}
	
	public void updateExp4NextLvl() {
		if (this.getLevel() == 1)
			_exp4NextLvl = 100;
		else 
			_exp4NextLvl = (int) Math.ceil(this.getExp() + (this.getExp() * 1.25));
	}
	public int getExp4NextLvl() { return _exp4NextLvl; }
	
	public BufferedImage getSprite(){ return sprite; }
	public void setName(String s) { _name = s; }
	public String getName() { return _name; }
	
	public void beatBoss() { _beatBoss = true; }
	public boolean isBossBeat() { return _beatBoss; }
	public void setBeatBoss(boolean b) { _beatBoss = b; } 
	
	public int getEnemyLevel() { return _enemyLevel; }
	public void setEnemyLevel(int i) { _enemyLevel = i; }
	
	public String getspaddress() { return spaddress; }
	public void setspaddress(String s) { spaddress = s; }
	
	public void resetPlayer() { 
		_beatBoss = false;
		_enemyLevel = 0;
	}
	
	public void prepareNextLevel() {
		_beatBoss = false;
		int newEnemyLevel = _enemyLevel + 1;
			//this.setStr(this.getStr());
			//this.setDef(this.getDef());
			//this.setStrMag(this.getMagicStr());
			//this.setdefMag(this.getMagicDef());
			this.setHealth(this.getTotalHealth()); // restore health after beating boss
			this.setMagic(this.getTotalMagic()); // restore magic after beating boss
			//this.setLevel(this.getLevel());
			//this.setExp(this.getExp());
			//this.setMoney(this.getMoney());
			this.setEnemyLevel(newEnemyLevel);
			this.setIsDead(false);
			//this.setWeapName("none");
			//this.setArmName("none");
			//spaddress = "images/strawberry.png";
			this.setLocation(new Coordinate(512, 288)); // go back to starting location
			this.setMap(2);
		}
	
	
	public int findExpUntilNextLvl() {
		return _exp4NextLvl - this.getExp();
	}
	
	public void levelUp() {
		int newLvl = this.getLevel() + 1;
		int newStr = this.getStr() + 1;
		int newDef = this.getDef() + 1;
		int newMagicStr, newMagicDef;
		if (newLvl % 2 == 0) {
			newMagicStr = this.getMagicStr() + 1;
			newMagicDef = this.getMagicDef() + 1;
		}
		else {
			newMagicStr = this.getMagicStr();
			newMagicDef = this.getMagicDef();
		}
		int newHealth = this.getTotalHealth() + 2;
		int newMagic = this.getTotalMagic() + 1;
	
		this.setStr(newStr);
		this.setDef(newDef);
		this.setStrMag(newMagicStr);
		this.setDefMag(newMagicDef);
		this.setTotalHealth(newHealth);
		this.setHealth(newHealth);
		this.setTotalMagic(newMagic);
		this.setMagic(newMagic);
		this.setLevel(newLvl);
		//this.setExp(0);
		//this.setMoney(0);
		//this.setEnemyLevel(0);
		this.setIsDead(false);
		//this.setWeapName("none");
		//this.setArmName("none");
		//spaddress = "images/strawberry.png";
		//this.setLocation(new Coordinate(512, 288));
		//this.setMap(2);
	}
}
