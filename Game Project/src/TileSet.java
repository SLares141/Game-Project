import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;


public class TileSet {

	ArrayList<BufferedImage> ts = new ArrayList<>();
	
	public TileSet(){
		try {
			/*
			 *  0=dirt0
			 *  1=grass0
			 *  2=water0
			 *  3=wood0
			 * 
			 * 
			 */
			ts.add(ImageIO.read(new File("tiles/dirt0.png")));
			ts.add(ImageIO.read(new File("tiles/grass0.png")));
			ts.add(ImageIO.read(new File("tiles/water0.png")));
			ts.add(ImageIO.read(new File("tiles/wood0.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public BufferedImage getTile(int x){
		return ts.get(x);
	}
	public void setTile(int x, BufferedImage b){
		ts.set(x, b);
	}
}