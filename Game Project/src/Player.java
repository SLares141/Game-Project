import java.awt.List;
import java.io.BufferedReader;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
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
	
	public boolean loadplayer0 = true;
	BufferedImage sprite = null;
	private String spaddress;
	private String weaponeq;
	
	public Player() {
		if(loadplayer0){
			// LOAD PLAYER STATS FROM SAVE FILE
			FileReader inFile;
			try {
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
				this.setWeapon(temp.get(9));
				spaddress = temp.get(10);
				int x = Integer.valueOf(temp.get(11));
				int y = Integer.valueOf(temp.get(12));
				this.setLocation(new Coordinate(x,y));
				
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
		}else{
			
		}
	}
	public BufferedImage getSprite(){
		return sprite;
	}
	
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
			wr.println(String.valueOf(this.getDead()));
			wr.println(this.getWeapon());
			wr.println(this.spaddress);
			wr.println(String.valueOf(this.getLocation().x));
			wr.println(String.valueOf(this.getLocation().y));
			wr.close();
		} catch (IOException e){
			
		}
	}
	public void move() {
		
	}
	public void interact() {
		
	}
	
	
}
