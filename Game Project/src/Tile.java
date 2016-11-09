import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Tile {
	
	public BufferedImage im;
	public TileSet ts;
	private boolean isPortal;
	private String destination;
	public Tile(int num, boolean b){
		isPortal = b;
		ts = new TileSet();
		im = ts.getTile(num);
	}
	public boolean isPortal(){
		return isPortal;
	}
	public void setDestination(String s){
		destination = s;
	}
	public String getDestination(){
		return destination;
	}
	

}