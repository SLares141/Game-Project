import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PlayerCharacter extends Character{
	private BufferedImage _sprite;
	
	public PlayerCharacter(){
		super();
		try {
			_sprite = ImageIO.read(new File("images/Mario thumbs up.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
