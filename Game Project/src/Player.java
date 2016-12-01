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
	
	private Weapon equippedW;
	private Armor equippedA;
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
		
		if(loadplayer0){
			// LOAD PLAYER STATS FROM SAVE FILE
			FileReader inFile;
			try {
				try {
					_sprite = ImageIO.read(new File("images/strawberry.png"));
					_menuSprite = ImageIO.read(new File("images/menuStrawberry.png"));
					_smallMenuSprite = ImageIO.read(new File("images/smallMenuStrawberry.png"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				inFile = new FileReader("PlayerFiles/Player0");
				
				BufferedReader buffReader = new BufferedReader(inFile);
				ArrayList<String> temp = new ArrayList<String>();
				
				String line;
				try {
					while((line = buffReader.readLine()) != null){
						temp.add(line);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				this.setStr(Integer.valueOf(temp.get(0)));
				this.setDef(Integer.valueOf(temp.get(1)));
				this.setStrMag(Integer.valueOf(temp.get(2)));
				this.setDefMag(Integer.valueOf(temp.get(3)));
				this.setHealth(Integer.valueOf(temp.get(4)));

				this.setTotalHealth(Integer.valueOf(temp.get(5)));
				this.setMagic(Integer.valueOf(temp.get(6)));
				this.setTotalMagic(Integer.valueOf(temp.get(7)));
				this.setLevel(Integer.valueOf(temp.get(8)));
				this.setExp(Integer.valueOf(temp.get(9)));
				this.setMoney(Integer.valueOf(temp.get(10)));
				this.setEnemyLevel(Integer.valueOf(temp.get(11)));
				this.setIsDead(Boolean.valueOf(temp.get(12)));
				
				switch(temp.get(13)){
				case "none": default:
					this.setWeapon(new Weapon("none", 0));
					break;
				}
				
				switch(temp.get(14)){
				case "none": default:
					this.setArmor(new Armor("none", 0, 0));
					break;
				}
				
				spaddress = temp.get(15);
				int x = Integer.valueOf(temp.get(16));
				int y = Integer.valueOf(temp.get(17));
				this.setLocation(new Coordinate(x,y));
				this.setMap(Integer.valueOf(temp.get(18)));
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// LOADS SPRITE FROM FILES
			try {
				sprite = ImageIO.read(new File(spaddress));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			
		}
		updateExp4NextLvl();
		
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
	
	public void savePlayer(){
		try{
			PrintWriter wr = new PrintWriter("PlayerFiles/Player0", "UTF-8");
			wr.println(String.valueOf(this.getStr()));
			wr.println(String.valueOf(this.getDef()));
			wr.println(String.valueOf(this.getMagicStr()));
			wr.println(String.valueOf(this.getMagicDef()));
			wr.println(String.valueOf(this.getHealth()));
			wr.println(String.valueOf(this.getTotalHealth()));
			wr.println(String.valueOf(this.getMagic()));
			wr.println(String.valueOf(this.getTotalMagic()));
			wr.println(String.valueOf(this.getLevel()));
			wr.println(String.valueOf(this.getExp()));
			wr.println(String.valueOf(this.getMoney()));
			wr.println(String.valueOf(this.getEnemyLevel()));
			wr.println(String.valueOf(this.getIsDead()));
			wr.println(this.getWeapName());
			wr.println(this.getArmName());
			wr.println(this.spaddress);
			wr.println(String.valueOf(this.getLocation().x));
			wr.println(String.valueOf(this.getLocation().y));
			wr.println(String.valueOf(this.getMap()));
			wr.close();
		} catch (IOException e){
			

		}
	}
	
	/**
	 * This method is a modified version of the savePlayer() method. This method
	 * restores the original player stats for a new game after game over
	 */
	public void resetPlayer(){ // save  and set original stats
		_beatBoss = false;
		_enemyLevel = 0;
		try{
			PrintWriter wr = new PrintWriter("PlayerFiles/Player0", "UTF-8");
			wr.println(String.valueOf(1));
			wr.println(String.valueOf(0));
			wr.println(String.valueOf(3));
			wr.println(String.valueOf(1));
			wr.println(String.valueOf(10));
			wr.println(String.valueOf(10));
			wr.println(String.valueOf(5));
			wr.println(String.valueOf(5));
			wr.println(String.valueOf(1));
			wr.println(String.valueOf(0));
			wr.println(String.valueOf(0));
			wr.println(String.valueOf(0));
			wr.println(String.valueOf(false));
			wr.println("none");
			wr.println("none"); 
			wr.println("images/strawberry.png");
			wr.println(String.valueOf(512));
			wr.println(String.valueOf(288));
			wr.println(String.valueOf(2));
			wr.close();
		} catch (IOException e){
		
		}
		this.setStr(1);
		this.setDef(0);
		this.setStrMag(3);
		this.setDefMag(1);
		this.setHealth(10);
		this.setTotalHealth(10);
		this.setMagic(5);
		this.setTotalMagic(5);
		this.setLevel(1);
		this.setExp(0);
		this.setMoney(0);
		this.setEnemyLevel(0);
		this.setIsDead(false);
		this.setWeapName("none");
		this.setArmName("none");
		spaddress = "images/strawberry.png";
		this.setLocation(new Coordinate(512, 288));
		this.setMap(2);
	}
	
	
	public void prepareNextLevel() {
		_beatBoss = false;
		int newEnemyLevel = _enemyLevel + 1;
		
		try{
			PrintWriter wr = new PrintWriter("PlayerFiles/Player0", "UTF-8");
			wr.println(String.valueOf(this.getStr()));
			wr.println(String.valueOf(this.getDef()));
			wr.println(String.valueOf(this.getMagicStr()));
			wr.println(String.valueOf(this.getMagicDef()));
			wr.println(String.valueOf(this.getTotalHealth())); // restore health after beating boss
			wr.println(String.valueOf(this.getTotalHealth()));
			wr.println(String.valueOf(this.getTotalMagic())); // restore magic after beating boss
			wr.println(String.valueOf(this.getTotalMagic()));
			wr.println(String.valueOf(this.getLevel()));
			wr.println(String.valueOf(this.getExp()));
			wr.println(String.valueOf(this.getMoney()));
			wr.println(String.valueOf(newEnemyLevel));
			wr.println(String.valueOf(false));
			wr.println(this.getWeapName());
			wr.println(this.getArmName());
			wr.println("images/strawberry.png");
			wr.println(String.valueOf(512)); // go back to starting location
			wr.println(String.valueOf(288));
			wr.println(String.valueOf(2));
			wr.close();
		} catch (IOException e){
		
		}
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
		
		
		try{
			PrintWriter wr = new PrintWriter("PlayerFiles/Player0", "UTF-8");
			wr.println(String.valueOf(newStr));
			wr.println(String.valueOf(newDef));
			wr.println(String.valueOf(newMagicStr));
			wr.println(String.valueOf(newMagicDef));
			wr.println(String.valueOf(newHealth));
			wr.println(String.valueOf(newHealth));
			wr.println(String.valueOf(newMagic));
			wr.println(String.valueOf(newMagic));
			wr.println(String.valueOf(newLvl));
			wr.println(String.valueOf(this.getExp()));
			wr.println(String.valueOf(this.getMoney()));
			wr.println(String.valueOf(this.getEnemyLevel()));
			wr.println(String.valueOf(false));
			wr.println(this.getWeapName());
			wr.println(this.getArmName());
			wr.println(this.spaddress);
			wr.println(String.valueOf(this.getLocation().x));
			wr.println(String.valueOf(this.getLocation().y));
			wr.println(String.valueOf(this.getMap()));
			wr.close();
		} catch (IOException e){
			

		}
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
	
	
	public void move() {
		
	}
	public void interact() {
		
	}
}
