import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class EnemyCharacter extends Character {
	private int _index; // index code that indicates which enemy this is
	private BufferedImage _battleSprite;
	private String _name;
	private boolean _isBoss; // player cannot run from boss
	private int _enemyCode;
	
	
	//public EnemyCharacter(){ this(0); }
	
	public EnemyCharacter(int enemyCode, int enemyLevel){
		
		_enemyCode = enemyCode;
		
		this.setLevel(enemyLevel);
		this.setIsDead(false);
		this.restoreDef();
		this.setWeapon(new Weapon("none", 0));
		this.setArmor(new Armor("none", 0, 0));
		
		switch (enemyCode) {
		case 0:
			_isBoss = true;
			_name = "Broccoli Boss";
			
			setRewards(this.getLevel()); // set exp and money payout
			setStats();
			
			try {
				_sprite = ImageIO.read(new File("images/broccoli.png"));
				_battleSprite = ImageIO.read(new File("images/battleBroccoli.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			break;
			
		case 1: default:
			_isBoss = false;
			_name = "Common Carrot";
			
			setRewards(enemyLevel); // set exp and money payout
			setStats();
			
			try {
				_sprite = ImageIO.read(new File("images/carrot.png"));
				_battleSprite = ImageIO.read(new File("images/battleCarrot.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
			
		case 2:
			_isBoss = false;
			_name = "Casual Corn";
			
			setRewards(enemyLevel);
			setStats();
			
			try {
				_sprite = ImageIO.read(new File("images/corn.png"));
				_battleSprite = ImageIO.read(new File("images/battleCorn.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
		}
		
	}
	
	public boolean isBoss() { return _isBoss; }
	public void setBoss() { _isBoss = true; }
	public void setName(String s) { _name = s; }
	public String getName() { return _name; }
	public BufferedImage getBattleSprite() { return _battleSprite; }
	public int getCode() { return _enemyCode; }
	
	public String enemyAttack(Character c) {
		Random r = new Random();
		int n = r.nextInt(100);
		if (n < 10) { // defend 10% of time
			this.defend();
			System.out.println("Enemy used DEFEND");
			c.restoreDef(); // restore player's defend status to false
			return "def";
		} else if (n < 25) { // use magic attack 15% of time
			if (this.getMagic() - 2 >= 0) {
				this.magicAttack(c);
				System.out.println("Enemy used MAGIC");
				return "mag";
			} else
				return this.enemyAttack(c); // select different action if enemy has no MP left
		} else if (n < 40) { // use special attack 15% of time
			int spHit = this.specialAttack(c);
			if (spHit >= 0) {
				System.out.println("Enemy used SPECIAL");
				return "spe";
			} else {
				System.out.println("Enemy Special Missed");
				return "mis";
			}
		} else { // use melee attack 60% of time
			this.attack(c);
			System.out.println("Enemy used MELEE");
			return "mel";
		}
	}
	
	public void awardOnVictory(Player p) {
		p.setExp(p.getExp() + this.getExp());
		p.setMoney(p.getMoney() + this.getMoney());
	}
	
	@Override
	public void setLevel(int i ) {
		super.setLevel(i);
		setRewards(i);
		setStats();
	}
	
	
	// returns how much EXP (and money) should be set, based on the current enemyLevel
	public void setRewards(int enemyLevel) {
		int answer;
		if (enemyLevel == 0)
			switch (_enemyCode) {
				case 0:
					answer = 60;
					break;
				case 1: default:
					answer = 30;
					break;
				case 2:
					answer = 10;
					break;
			}
		else { // if enemyLevel != 0
			int initial;
			switch (_enemyCode) {
			case 0:
				initial = 60;
				break;
			case 1: default:
				initial = 30;
				break;
			case 2:
				initial = 10;
				break;
		}
			
			answer = initial;
			for (int i = 0; i < enemyLevel; i++) {
				answer = (int) Math.floor(answer * 1.25);
			}	
		}
		this.setExp(answer);
		this.setMoney(answer);
		return;
	}
	
	
	public void setStats() {
		if (this.getLevel() == 0) {
			switch (_enemyCode) { 
				case 0: // initial boss stats
					this.setStr(1);
					this.setDef(0);;
					this.setStrMag(3);
					this.setDefMag(1);
					this.setTotalHealth(10);
					this.setHealth(this.getTotalHealth());
					this.setTotalMagic(5);
					this.setMagic(this.getTotalMagic());
					this.setIsDead(false);
					this.restoreDef();
					break;
				case 1: default:  // initial common enemy stats
					this.setStr(1);
					this.setDef(0);;
					this.setStrMag(2);
					this.setDefMag(0);
					this.setTotalHealth(5);
					this.setHealth(this.getTotalHealth());
					this.setTotalMagic(3);
					this.setMagic(this.getTotalMagic());
					this.setIsDead(false);
					this.restoreDef();
					break;
				case 2:
					this.setStr(1);
					this.setDef(0);;
					this.setStrMag(2);
					this.setDefMag(0);
					this.setTotalHealth(3);
					this.setHealth(this.getTotalHealth());
					this.setTotalMagic(1);
					this.setMagic(this.getTotalMagic());
					this.setIsDead(false);
					this.restoreDef();
					break;
			}
			return;
		} else {
			int newStr, newDef, newStrMag, newDefMag, newTotalHealth, newTotalMagic;
			switch (_enemyCode) { 
				case 0:
					newStr = 1;
					newDef = 0;
					newStrMag = 3;
					newDefMag = 1;
					newTotalHealth = 10;
					newTotalMagic = 5;
				
					for (int i = 0; i < this.getLevel(); i++)
					{
						newStr += 2;
						if (i != 0 && i % 2 == 0) {
							newDef += 1;
							newStrMag += 1;
							newDefMag += 1;
						}
						newTotalHealth += 3;
						newTotalMagic += 2;
					}
					break;
			
				case 1: default:
					newStr = 1;
					newDef = 0;
					newStrMag = 2;
					newDefMag = 0;
					newTotalHealth = 5;
					newTotalMagic = 3;
				
					for (int i = 0; i < this.getLevel(); i++)
					{
						newStr += 1;
						if (i != 0 && i % 2 == 0) {
							newDef += 1;
							newStrMag += 1;
							newDefMag += 1;
						}
						newTotalHealth += 2;
						newTotalMagic += 1;
					}
					break;
					
				case 2: // corn has low stats to allow play to grind addition EXP if behind in level
					newStr = 1;
					newDef = 0;
					newStrMag = 2;
					newDefMag = 0;
					newTotalHealth = 3;
					newTotalMagic = 1;
				
					for (int i = 0; i < this.getLevel(); i++)
					{
						if (i != 0 && i % 2 == 0) {
							newStr += 1;
							newDef += 1;
							newStrMag += 1;
							newDefMag += 1;
							newTotalHealth += 2;
							newTotalMagic += 1;
						}
						
					}
					break;
					
			}

			this.setStr(newStr);
			this.setDef(newDef);
			this.setStrMag(newStrMag);
			this.setDefMag(newDefMag);
			this.setTotalHealth(newTotalHealth);
			this.setHealth(this.getTotalHealth());
			this.setTotalMagic(newTotalMagic);
			this.setMagic(this.getTotalMagic());
			this.setIsDead(false);
			this.restoreDef();
		} 
	}

}
