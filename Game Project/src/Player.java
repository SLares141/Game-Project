import java.awt.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Player extends Character {
	
	private Weapon equippedW;
	private Armor equippedA;
	
	public Player() throws IOException{
		FileReader inFile = new FileReader("Char Files/Char0.txt");
		BufferedReader buffReader = new BufferedReader(inFile);
		ArrayList<String> temp = new ArrayList<String>();
		
		String line;
		while((line = buffReader.readLine()) != null){
			temp.add(line);
		}
		int i = 0;
		this.setStr(Integer.valueOf(temp.get(0)));
		this.setDef(Integer.valueOf(temp.get(1)));
		this.setStrMag(Integer.valueOf(temp.get(2)));
		this.setdefMag(Integer.valueOf(temp.get(3)));
		this.setHealth(Integer.valueOf(temp.get(4)));
		this.setMagic(Integer.valueOf(temp.get(5)));
		this.setLevel(Integer.valueOf(temp.get(6)));
		this.setExp(Integer.valueOf(temp.get(7)));
		this.setIsDead(false);
		this.setWeapon(null);
	}
	public void move() {
		
	}
	public void interact() {
		
	}
	
	
}
