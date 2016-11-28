import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PlayerCharacter extends Character{
	private BufferedImage _sprite;
	private String _name;
	
	public PlayerCharacter(){
		super();
		_name = "Player"; // name is Player by default
		try {
			_sprite = ImageIO.read(new File("images/Mario thumbs up.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setName(String s) { _name = s; }
	public String getName() { return _name; }

}
