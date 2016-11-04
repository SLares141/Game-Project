import java.awt.image.BufferedImage;

public class EnemyCharacter extends Character {
	private int _index; // index code that indicates which enemy this is
	private BufferedImage _sprite;
	private boolean _isBoss; // player cannot run from boss
	
	public EnemyCharacter(int i){
		super();
		_index = i;
		_isBoss = false;
	}
	
	public boolean isBoss() { return _isBoss; }
	public void setBoss() { _isBoss = true; }

}
