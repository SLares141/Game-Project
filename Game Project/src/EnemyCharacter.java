import java.awt.image.BufferedImage;
import java.util.Random;

public class EnemyCharacter extends Character {
	private int _index; // index code that indicates which enemy this is
	private BufferedImage _sprite;
	private String _name;
	private boolean _isBoss; // player cannot run from boss
	
	public EnemyCharacter(){ this(0); }
	
	public EnemyCharacter(int i){
		super();
		_index = i;
		_isBoss = false;
		_name = "Default Name";
		this.setWeapon(new Weapon("none", 0));
		this.setArmor(new Armor("none", 0, 0));
	}
	
	public boolean isBoss() { return _isBoss; }
	public void setBoss() { _isBoss = true; }
	public void setName(String s) { _name = s; }
	public String getName() { return _name; }
	
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
	
	public void awardOnVictoru(Player p) {
		p.setExp(p.getExp() + this.getExp());
		p.setMoney(p.getMoney() + this.getMoney());
	}

}
