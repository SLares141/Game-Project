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
	}
	
	public boolean isBoss() { return _isBoss; }
	public void setBoss() { _isBoss = true; }
	public void setName(String s) { _name = s; }
	public String getName() { return _name; }
	
	public void enemyAttack(Character c) {
		Random r = new Random();
		int n = r.nextInt(100);
		if (n < 10) { // defend 10% of time
			this.defend();
		} else if (n < 25) { // use magic attack 15% of time
			if (this.getMagic() - 2 >= 0) {
				this.magicAttack(c);
			} else
				this.enemyAttack(c); // select different action if enemy has no MP left
		} else if (n < 40) { // use special attack 15% of time
			this.specialAttack(c);
		} else { // use melee attack 60% of time
			this.attack(c);
		}
	}

}
