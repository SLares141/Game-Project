import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Tile {
	
	final int x, y;
	public BufferedImage im;
	public TileSet ts;
	public Tile(int a, int b, int num){
		ts = new TileSet();
		x = a;
		y = b;
		im = ts.getTile(num);
	}

}