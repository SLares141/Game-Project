import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Tile {
	
	public BufferedImage im;
	public TileSet ts;
	private boolean isPortal;
	private boolean canMoveTo;
	private boolean isBuilding;
	private boolean isBorder;
	private int destination;
	private Coordinate returnCord;
	
	public Tile(int num, boolean b){
		isPortal = b;
		ts = new TileSet();
		im = ts.getTile(num);
		if((num == 2) || (num == 4)){
			canMoveTo = false;
		}else{
			canMoveTo = true;
		}
		if (num == 5){
			isPortal = true;
			isBuilding = true;
		}else{isBuilding = false;}
	}
	//get functions
	public boolean isPortal(){
		return isPortal;
	}
	public boolean canMoveTo(){
		return canMoveTo;
	}
	public boolean isBuilding(){
		return isBuilding;
	}
	public boolean isBorder(){
		return isBorder;
	}
	public int getDestination(){
		return destination;
	}
	public Coordinate getReturnCord(){
		return returnCord;
	}
	//set functions
	public void setPortalBool(){
		isPortal = true;
	}
	public void setisBorder(boolean b){
		isBorder = b;
	}
	public void setDestination(int s){
		destination = s;
	}
	public void setReturnCord(Coordinate c){
		returnCord = c;
	}
	

}