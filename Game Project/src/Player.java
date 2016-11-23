import java.awt.List;
import java.io.BufferedReader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;

import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Player extends Character {
	
	private Weapon equippedW;
	private Armor equippedA;
	private String _name;

	public boolean loadplayer0 = true;
	BufferedImage sprite = null;
	private String spaddress;
	private String weaponeq;
	//private boolean lostlastbattle;
	private boolean _usedDefend = false;
	private boolean _beatBoss = false;
	
	public Player() {
		_name = "Player";
		if(loadplayer0){
			// LOAD PLAYER STATS FROM SAVE FILE
			FileReader inFile;
			try {
				try {
					_sprite = ImageIO.read(new File("images/strawberry.png"));
					_menuSprite = ImageIO.read(new File("images/menuStrawberry.png"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				inFile = new FileReader("PlayerFiles/Player0");
				
				BufferedReader buffReader = new BufferedReader(inFile);
				ArrayList<String> temp = new ArrayList<String>();
				
				String line;
				try {
					while((line = buffReader.readLine()) != null){
						temp.add(line);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				this.setStr(Integer.valueOf(temp.get(0)));
				this.setDef(Integer.valueOf(temp.get(1)));
				this.setStrMag(Integer.valueOf(temp.get(2)));
				this.setdefMag(Integer.valueOf(temp.get(3)));
				this.setHealth(Integer.valueOf(temp.get(4)));
				this.setMagic(Integer.valueOf(temp.get(5)));
				this.setLevel(Integer.valueOf(temp.get(6)));
				this.setExp(Integer.valueOf(temp.get(7)));
				this.setIsDead(Boolean.valueOf(temp.get(8)));
				
				switch(temp.get(9)){
				case "none":
					this.setWeapon(new Weapon("none", 0));
					break;
				default:
					this.setWeapon(new Weapon("none", 0));
					break;
				}
				
				
				switch(temp.get(10)){
				case "none":
					this.setArmor(new Armor("none", 0, 0));
					break;
				default:
					this.setArmor(new Armor("none", 0, 0));
					break;
				}
				
				
				spaddress = temp.get(11);
				int x = Integer.valueOf(temp.get(12));
				int y = Integer.valueOf(temp.get(13));
				this.setLocation(new Coordinate(x,y));
				this.setMap(Integer.valueOf(temp.get(14)));
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// LOADS SPRITE FROM FILES
			try {
				sprite = ImageIO.read(new File(spaddress));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			
		}
	}
	
	public BufferedImage getSprite(){ return sprite; }
	public void setName(String s) { _name = s; }
	public String getName() { return _name; }
	
	public boolean beatBoss() { return _beatBoss; }
	public void setBeatBoss(boolean b) { _beatBoss = b; } 
	
	public void savePlayer(){
		try{
			PrintWriter wr = new PrintWriter("PlayerFiles/Player0", "UTF-8");
			wr.println(String.valueOf(this.getStr()));
			wr.println(String.valueOf(this.getDef()));
			wr.println(String.valueOf(this.getMagicStr()));
			wr.println(String.valueOf(this.getMagicDef()));
			wr.println(String.valueOf(this.getHealth()));
			wr.println(String.valueOf(this.getMagic()));
			wr.println(String.valueOf(this.getLevel()));
			wr.println(String.valueOf(this.getExp()));
			wr.println(String.valueOf(this.getIsDead()));
			wr.println(this.getWeapName());
			wr.println(this.getArmName());
			wr.println(this.spaddress);
			wr.println(String.valueOf(this.getLocation().x));
			wr.println(String.valueOf(this.getLocation().y));
			wr.println(String.valueOf(this.getMap()));
			wr.close();
		} catch (IOException e){
			

		}
	}
	
	/**
	 * This method is a modified version of the savePlayer() method. This method
	 * restores the original player stats for a new game after game over
	 */
	public void resetPlayer(){ // save  and set original stats
		try{
			PrintWriter wr = new PrintWriter("PlayerFiles/Player0", "UTF-8");
			wr.println(String.valueOf(1));
			wr.println(String.valueOf(0));
			wr.println(String.valueOf(3));
			wr.println(String.valueOf(1));
			wr.println(String.valueOf(10));
			wr.println(String.valueOf(5));
			wr.println(String.valueOf(1));
			wr.println(String.valueOf(0));
			wr.println(String.valueOf(false));
			wr.println("none");
			wr.println("none"); 
			wr.println("images/strawberry.png");
			wr.println(String.valueOf(512));
			wr.println(String.valueOf(288));
			wr.println(String.valueOf(2));
			wr.close();
		} catch (IOException e){
		
		}
		this.setStr(1);
		this.setDef(0);
		this.setStrMag(3);
		this.setdefMag(1);
		this.setHealth(10);
		this.setMagic(5);
		this.setLevel(1);
		this.setExp(0);
		this.setIsDead(false);
		this.setWeapName("none");
		this.setArmName("none");
		spaddress = "images/strawberry.png";
		this.setLocation(new Coordinate(512, 288));
		this.setMap(2);
	}
	
	public void move() {
		
	}
	public void interact() {
		
	}
	
	
}
