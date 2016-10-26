import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Tile {
	
	final int x, y;
	public BufferedImage im;
	public Tile(int a, int b, int num){
		x = a;
		y = b;
		try{
			if(num == 0){im = ImageIO.read(new File("tiles/dirt0.png"));}
			else if(num == 1){im = ImageIO.read(new File("tiles/grass0.png"));}
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}