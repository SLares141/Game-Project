import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
/*
 * things to add: another button to see the effect of an item
 * 				  saving and settings
 * 
 */
public class InventoryMenuState extends JPanel implements State {

	Player player;
	PartyMember pm1, pm2, pm3;
	Character[] party;
	int characterIndex = 0;
	int partySize;
	Inventory inv;

	int invIndex = 0;

	BufferedImage arrow, biggerArrow;
	int cursorX;
	int cursorY;
	boolean yesSelected = true;
	boolean itemSelected;
	boolean itemUsed;
	boolean characterSelected;
	WindowFrame frame = WindowFrame.getInstance();
	int windowWidth = frame.getWidth();
	int windowHeight = frame.getHeight();
	StateMapSingleton stateMap = StateMapSingleton.getInstance();
	StateStackSingleton stateStack = StateStackSingleton.getInstance();
	String currentMenu;
	String[] menus = {"Items", "Magic", "Equip", "Status", "Save", "Settings"};


	public InventoryMenuState(Player p, PartyMember pm1, PartyMember pm2, PartyMember pm3, Inventory i) {
		player = p;
		this.pm1 = pm1;
		this.pm2 = pm2;
		this.pm3 = pm3;
		party = new Character[] {player, pm1, pm2, null};
		partySize = 3;
		inv = i;

		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT 
						|| e.getKeyCode() == KeyEvent.VK_D) {
					System.out.println("Right key pressed");
					rightPressed();
					System.out.println("char: " + characterIndex);
					System.out.println("inv: " + invIndex);
				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT
						|| e.getKeyCode() == KeyEvent.VK_A) {
					System.out.println("Left key pressed");
					leftPressed();
					System.out.println("char: " + characterIndex);
					System.out.println("inv: " + invIndex);
				}
				if (e.getKeyCode() == KeyEvent.VK_UP
						|| e.getKeyCode() == KeyEvent.VK_W) {
					System.out.println("Up key pressed");
					upPressed();
					System.out.println("char: " + characterIndex);
					System.out.println("inv: " + invIndex);
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN
						|| e.getKeyCode() == KeyEvent.VK_S) {
					System.out.println("Down key pressed");
					downPressed();
					System.out.println("char: " + characterIndex);
					System.out.println("inv: " + invIndex);
				}
				if	(e.getKeyCode() == KeyEvent.VK_ENTER
						|| e.getKeyCode() == KeyEvent.VK_SPACE){
					System.out.println("Enter key pressed");
					select();
					System.out.println("char: " + characterIndex);
					System.out.println("inv: " + invIndex);
				}
				if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE
						|| e.getKeyCode() == KeyEvent.VK_CAPS_LOCK){
					System.out.println("Backspace key pressed");
					back();
					System.out.println("char: " + characterIndex);
					System.out.println("inv: " + invIndex);
				}
				render();
			}
		});

		this.setFocusable(true);

		try {
			arrow = ImageIO.read(new File("images/small-arrow.png"));
			biggerArrow = ImageIO.read(new File("images/arrow.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.addNotify();
	}

	public void addNotify() {
		super.addNotify();
		requestFocus();
	}

	@Override
	public void update() {

	}

	@Override
	public void render() {
		repaint();
	}

	public void paintComponent(Graphics g) {
		Font large = new Font("Comic sans MS", Font.PLAIN, 32);
		Font medium = new Font("Comic sans MS", Font.PLAIN, 24);
		Font small = new Font("Comic sans MS", Font.PLAIN, 20);

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, windowWidth, windowHeight);	//background
		g.setColor(Color.WHITE);
		g.drawRect(5, 75, 250, 344);					//menu selection
		g.drawRect(5, 5, 1013, 65); 					//location
		g.drawRect(5, 423, 250, 147);					//money and game time
		g.drawRect(260, 75, 758, 495);					//party members or items

		//draws current location
		g.setFont(large);
		g.drawString("Current Location", 385, 50);

		//draws submenu selections
		g.setFont(small);
		g.drawString("Items", 74, 125);
		g.drawString("Magic", 74, 175);
		g.drawString("Equip", 74, 225);
		g.drawString("Status", 74, 275);
		g.drawString("Save", 74, 325);
		g.drawString("Settings", 74, 375);

		//draws money and in-game time
		g.drawString("Money: ", 30, 475);
		g.drawString("$" + inv.getMoney(), 100, 475);
		//g.drawString("Time: ", 30, 525);

		//draws party
		int count = 0;
		while(count < partySize) {
			Character c = party[count];
			g.drawImage(c.getMenuSprite(), 360, 95 + 120*count, null);
			g.drawRect(360, 95 + 120*count, 100, 100);
			g.setFont(large);
			g.drawString(c.getName(), 475, 125 + 120*count);
			g.setFont(medium);
			g.drawString("HP: " + c.getHealth() + "/" + c.getTotalHealth() , 475, 160 + 120*count);
			g.drawString("MP: " + c.getMagic() + "/" + c.getTotalMagic() , 475, 190 + 120*count);
			g.drawString("Level: " + c.getLevel(), 745, 125 + 120*count);
			g.drawString("EXP: " + c.getExp(), 745, 160 + 120*count);
			g.drawString("Next Level: ", 745, 190 + 120*count);
			count++;
		}

		if(currentMenu.equals("Main")) {
			g.drawImage(arrow, 25, (103 + cursorY), null);
		}
		else if(currentMenu.equals("Items")) {
			g.setColor(Color.BLACK);
			g.fillRect(260, 75, 758, 495);
			g.setColor(Color.WHITE);
			g.drawRect(260, 75, 758, 495);

			int x = 330;
			int y = 125;
			for(int j = 0; j < inv.getNumItems(); j++) {
				if(j%2 == 0) {
					g.drawString(inv.getItem(j).getName(), x, y);
					g.drawString("x" + inv.getItemAmount(j), x + 280, y);
					x = 700;
					continue;
				}
				g.drawString(inv.getItem(j).getName(), x, y);
				g.drawString("x" + inv.getItemAmount(j), x + 270, y);
				x = 330;
				y += 50;
			}
			g.drawImage(arrow, 25, 103, null);	

			if(itemSelected) {
				g.setColor(Color.BLACK);
				g.fillRect(260, 75, 758, 495);
				g.setColor(Color.WHITE);
				g.drawRect(260, 75, 758, 495);

				g.setFont(large);
				g.drawString("Give to...", 300, 135);

				//draws party
				count = 0;
				while(count < partySize) {
					Character c = party[count];
					g.drawImage(c.getSmallMenuSprite(), 360, 170 + 100*count, null);
					g.drawRect(360, 170 + 100*count, 80, 80);
					g.setFont(medium);
					g.drawString(c.getName(), 475, 190 + 100*count);
					g.setFont(small);
					g.drawString("HP: " + c.getHealth() + "/" + c.getTotalHealth() , 475, 220 + 100*count);
					g.drawString("MP: " + c.getMagic() + "/" + c.getTotalMagic() , 475, 245 + 100*count);
					count++;
				}
				g.setFont(medium);
				g.drawImage(biggerArrow, (280 + cursorX), (187 + cursorY), null);
			}
			else if(itemUsed) {
				g.setColor(Color.BLACK);
				g.fillRect(260, 75, 758, 495);
				g.setColor(Color.WHITE);
				g.drawRect(260, 75, 758, 495);
				g.setColor(Color.BLACK);
				g.fillRect(415, 200, 450, 250);
				g.setColor(Color.WHITE);
				g.drawRect(415, 200, 450, 250);

				Consumable cons = inv.use(invIndex, party[characterIndex]);
				g.setFont(medium);
				g.drawString(cons.getName(), 500, 300);
				g.drawString("Restored " + cons.getRestoreAmt() + " HP", 500, 360);
				g.setFont(small);
			}
			else 
				g.drawImage(arrow, (280 + cursorX), (103 + cursorY), null);

		}
		else if(currentMenu.equals("Magic")) {
			g.drawImage(arrow, 25, 153, null);
			g.drawImage(biggerArrow, (280 + cursorX), (120 + cursorY), null);
		}
		else if(currentMenu.equals("Equip")) {
			g.setColor(Color.BLACK);
			g.fillRect(260, 75, 758, 495);
			g.setColor(Color.WHITE);
			g.drawRect(260, 75, 758, 495);

			g.drawImage(arrow, 25, 203, null);
			g.drawImage(biggerArrow, (280 + cursorX), (120 + cursorY), null);
			
			//draws party
			count = 0;
			while(count < partySize) {
				Character c = party[count];
				g.drawImage(c.getMenuSprite(), 360, 95 + 120*count, null);
				g.drawRect(360, 95 + 120*count, 100, 100);
				g.setFont(large);
				g.drawString(c.getName(), 475, 125 + 120*count);
				g.setFont(medium);
				g.drawString("Weapon: " + c.getWeapon(), 475, 160 + 120*count);
				g.drawString("Armor " + c.getArmor(), 475, 190 + 120*count);
				count++;
			}
			
			if(characterSelected) {
				g.setColor(Color.BLACK);
				g.fillRect(260, 75, 758, 495);
				g.setColor(Color.WHITE);
				g.drawRect(260, 75, 758, 495);

				int x = 330;
				int y = 125;

				for(int j = 0; j < inv.getNumEquip(); j++) {
					if(j%2 == 0) {
						g.drawString(inv.getEquip(j).getName(), x, y);
						g.drawString("x" + inv.getEquipAmount(j), x + 280, y);
						x = 700;
						continue;
					}
					g.drawString(inv.getEquip(j).getName(), x, y);
					g.drawString("x" + inv.getEquipAmount(j), x + 270, y);
					x = 330;
					y += 50;
				}
				g.drawImage(arrow, (280 + cursorX), (103 + cursorY), null);
			}
			else if(itemSelected) {
				g.setColor(Color.BLACK);
				g.fillRect(260, 75, 758, 495);
				g.setColor(Color.WHITE);
				g.drawRect(260, 75, 758, 495);
				g.setColor(Color.BLACK);
				g.fillRect(415, 200, 450, 250);
				g.setColor(Color.WHITE);
				g.drawRect(415, 200, 450, 250);

				g.setFont(medium);
				g.drawString("Equip " + inv.getEquip(invIndex).getName() + "?", 550, 300);
				g.drawString("Yes", 520, 400);
				g.drawString("No", 700, 400);
				g.setFont(small);
				g.drawImage(arrow, (475 + cursorX), 377, null);
			}
			else if(itemUsed) {
				g.setColor(Color.BLACK);
				g.fillRect(260, 75, 758, 495);
				g.setColor(Color.WHITE);
				g.drawRect(260, 75, 758, 495);
				g.setColor(Color.BLACK);
				g.fillRect(415, 200, 450, 250);
				g.setColor(Color.WHITE);
				g.drawRect(415, 200, 450, 250);

				g.setFont(medium);
				g.drawString("Equipped " + inv.getEquip(invIndex).getName(), 550, 300);
				inv.equip(invIndex, party[characterIndex]);
				g.setFont(small);
			}
		}
		else if(currentMenu.equals("Status")) {
			g.drawImage(arrow, 25, 253, null);
			g.drawImage(biggerArrow, (280 + cursorX), (120 + cursorY), null);
			/*
			 *	also add stuff to the party member section
			 */

			if(characterSelected) {
				g.setColor(Color.BLACK);
				g.fillRect(260, 75, 758, 495);
				g.setColor(Color.WHITE);
				g.drawRect(260, 75, 758, 495);

				Character c = party[characterIndex];
				g.drawImage(c.getMenuSprite(), 310, 120, null);
				g.drawRect(310, 120, 100, 100);
				g.setFont(large);
				g.drawString(c.getName(), 430, 150);
				g.setFont(medium);
				g.drawString("HP: " + c.getHealth() + "/" + c.getTotalHealth() , 430, 185);
				g.drawString("MP: " + c.getMagic() + "/" + c.getTotalMagic() , 430, 215);
				g.drawString("Level: " + c.getLevel(), 700, 150);
				g.drawString("EXP: " + c.getExp(), 700, 185);
				g.drawString("Next Level: ", 700, 215);
				
				
				//g.drawString();
				//g.drawString();
				//g.drawString();
				//g.drawString();
			}
		}
		else if(currentMenu.equals("Save")) {
			g.setColor(Color.BLACK);
			g.fillRect(415, 200, 450, 250);
			g.setColor(Color.WHITE);
			g.drawRect(415, 200, 450, 250);

			g.setFont(large);
			g.drawString("Would You Like To Save?", 450, 255);
			g.drawString("Yes", 550, 330);
			g.drawString("No", 550, 395);

			g.drawImage(arrow, 25, 303, null);
			g.drawImage(biggerArrow, 450, (295 + cursorY), null);
		}
		else if(currentMenu.equals("Settings")) {
			g.setColor(Color.BLACK);
			g.fillRect(260, 75, 758, 495);
			g.setColor(Color.WHITE);
			g.drawRect(260, 75, 758, 495);

			g.drawImage(arrow, 25, 353, null);
			g.drawImage(arrow, (280 + cursorX), (110 + cursorY), null);
		}
	}

	@Override
	public void onEnter() { 
		currentMenu = new String("Main");
		update();
		render();
	}

	@Override
	public void onExit() {

	}

	private void select() {
		if(currentMenu.equals("Main")) {
			currentMenu = menus[cursorY/50];
			cursorX = 0;
			cursorY = 0;
			invIndex = 0;
			characterIndex = 0;
		}
		else if(currentMenu.equals("Items")) {
			if(itemSelected && party[characterIndex] != null) {
				itemSelected = false;
				itemUsed = true;
			}
			else if(itemUsed) {
				cursorX = 0;
				cursorY = 0;
				invIndex = 0;
				characterIndex = 0;
				itemUsed = false;
			}
			else if(inv.getItem(invIndex) != null && party[characterIndex] != null) {
				itemSelected = true;
				cursorX = 0;
				cursorY = 0;
			}
		}
		else if(currentMenu.equals("Magic")) {
			characterSelected = true;
			cursorX = 0;
			cursorY = 0;
		}
		else if(currentMenu.equals("Equip")) {
			if(characterSelected) {
				if(inv.getEquip(invIndex) == null) {}
				else {
					characterSelected = false;
					itemSelected = true;
					cursorX = 0;
					cursorY = 0;
				}
			}
			else if(itemSelected) {
				if(yesSelected) {
					itemSelected = false;
					itemUsed = true;
					cursorX = 0;
				}
				else {
					cursorX = 0;
					invIndex = 0;
					itemSelected = false;
					characterSelected = true;
					yesSelected = true;
				}
			}
			else if(itemUsed) {
				cursorX = 0;
				cursorY = 0;
				invIndex = 0;
				characterIndex = 0;
				itemUsed = false;
			}
			else if(party[characterIndex] != null) {
				characterSelected = true;
				cursorY = 0;
			}
		}
		else if(currentMenu.equals("Status")) {
			if(!characterSelected && party[characterIndex] != null) {
				characterSelected = true;
				cursorX = 0;
				cursorY = 0;
			}
		}
		else if(currentMenu.equals("Save")) {
			if(!yesSelected) {
				currentMenu = "Main";
				cursorX = 0;
				cursorY = (50*4);
				yesSelected = true;
			}
		}
		else if(currentMenu.equals("Settings")) {
			//some code
		}
	}

	private void back() {
		if(currentMenu.equals("Main")) {
			stateStack.pop();
		}
		else if(currentMenu.equals("Items")) {
			if(itemSelected) {
				characterIndex = 0;
				invIndex = 0;
				if(itemSelected) {
					cursorX = 0;
					cursorY = 0;
				}
				itemSelected = false;
			}
			else if(itemUsed) {
				cursorX = 0;
				cursorY = 0;
				invIndex = 0;
				characterIndex = 0;
				itemUsed = false;
			}
			else {
				currentMenu = "Main";
				cursorY = 0;
				characterIndex = 0;
				invIndex = 0;
			}
		}
		else if(currentMenu.equals("Magic")) {
			currentMenu = "Main";
			cursorY = 50;
		}
		else if(currentMenu.equals("Equip")) {
			if(characterSelected) {
				invIndex = 0;
				characterIndex = 0;
				cursorX = 0;
				cursorY = 0;
				characterSelected = false;
			}
			else if(itemSelected) {
				invIndex = 0;
				cursorX = 0;
				cursorY = 0;
				itemSelected = false;
				characterSelected = true;
			}
			else if(itemUsed) {
				cursorX = 0;
				cursorY = 0;
				invIndex = 0;
				characterIndex = 0;
				itemUsed = false;
			}
			else {
				currentMenu = "Main";
				cursorY = (50*2);
				characterIndex = 0;
				invIndex = 0;
			}
		}
		else if(currentMenu.equals("Status")) {
			if(characterSelected) {
				characterSelected = false;
				cursorX = 0;
				cursorY = 0;
				characterIndex = 0;
			}
			else {
				characterIndex = 0;
				currentMenu = "Main";
				cursorY = (50*3);
			}
		}
		else if(currentMenu.equals("Save")) {
			currentMenu = "Main";
			cursorX = 0;
			cursorY = (50*4);
			yesSelected = true;
		}
		else if(currentMenu.equals("Settings")) {
			currentMenu = "Main";
			cursorY = (50*5);
		}
	}

	private void upPressed() {
		if(currentMenu.equals("Main")) {
			if(cursorY == 0) 
				cursorY = 250;
			else 
				cursorY -= 50;
		}
		else if(currentMenu.equals("Items")) {
			if(itemSelected) {
				if(cursorY == 0) {
					cursorY = 300;
					characterIndex = 3;
				}
				else {
					cursorY -= 100;
					characterIndex--;
				}
			}
			else if (!itemUsed){
				if(cursorY == 0) {
					cursorY = 400;
					invIndex = invIndex + 16;
				}
				else {
					cursorY -= 50;
					invIndex = invIndex - 2;
				}
			}
		}
		else if(currentMenu.equals("Magic")) {
			if(cursorY == 0) {
				cursorY = 360;
				characterIndex = 3;
			}
			else {
				cursorY -= 120;
				characterIndex--;
			}
		}
		else if(currentMenu.equals("Equip")) {
			if(characterSelected) {
				if(cursorY == 0) {
					cursorY = 400;
					invIndex = invIndex + 16;
				}
				else {
					cursorY -= 50;
					invIndex = invIndex - 2;
				}
			}
			else if(itemSelected) {}
			else if(itemUsed) {

			}
			else {
				if(cursorY == 0) {
					cursorY = 360;
					characterIndex = 3;
				}
				else {
					cursorY -= 120;
					characterIndex--;
				}
			}
		}
		else if(currentMenu.equals("Status")) {
			if(!characterSelected) {
				if(cursorY == 0) {
					cursorY = 360;
					characterIndex = 3;
				}
				else {
					cursorY -= 120;
					characterIndex--;
				}
			}
		}
		else if(currentMenu.equals("Save")) {
			if(cursorY == 0) {
				cursorY = 65;
				yesSelected = false;
			}	
			else {
				cursorY -= 65;
				yesSelected = true;
			}
		}
		else if(currentMenu.equals("Settings")) {
			//some code
		}
	}

	private void downPressed() {
		if(currentMenu.equals("Main")) {
			if(cursorY == 250) 
				cursorY = 0;
			else 
				cursorY += 50;
		}
		else if(currentMenu.equals("Items")) {
			if(itemSelected) {
				if(cursorY == 300) {
					cursorY = 0;
					characterIndex = 0;
				}
				else {
					cursorY += 100;
					characterIndex++;
				}
			}
			else if(!itemUsed){
				if(cursorY == 400) {
					cursorY = 0;
					invIndex = invIndex - 16;
				}
				else {
					cursorY += 50;
					invIndex = invIndex + 2;
				}
			}
		}
		else if(currentMenu.equals("Magic")) {
			if(cursorY == 360) {
				cursorY = 0;
				characterIndex = 0;
			}
			else {
				cursorY += 120;
				characterIndex++;
			}
		}
		else if(currentMenu.equals("Equip")) {
			if(characterSelected) {
				if(cursorY == 400) {
					cursorY = 0;
					invIndex = invIndex - 16;
				}
				else {
					cursorY += 50;
					invIndex = invIndex + 2;
				}
			}
			else if(itemSelected) {}
			else if(itemUsed) {

			}
			else {
				if(cursorY == 360) {
					cursorY = 0;
					characterIndex = 0;
				}
				else {
					cursorY += 120;
					characterIndex++;
				}
			}
		}
		else if(currentMenu.equals("Status")) {
			if(!characterSelected) {
				if(cursorY == 360) {
					cursorY = 0;
					characterIndex = 0;
				}
				else {
					cursorY += 120;
					characterIndex++;
				}
			}
		}
		else if(currentMenu.equals("Save")) {
			if(cursorY == 65) {
				cursorY = 0;
				yesSelected = true;
			}
			else {
				cursorY += 65;
				yesSelected = false;
			}
		}
		else if(currentMenu.equals("Settings")) {
			//some code
		}
	}

	private void rightPressed() {
		if(currentMenu.equals("Main")) {}
		else if(currentMenu.equals("Items")) {
			if(!itemSelected && !itemUsed) {
				if(cursorX == 0) {
					cursorX += 380;
					invIndex++;
				}
				else if(!itemUsed){
					cursorX -= 380;
					invIndex--;
				}
			}
		}
		else if(currentMenu.equals("Magic")) {}
		else if(currentMenu.equals("Equip")) {
			if(characterSelected) {
				if(cursorX == 380) {
					cursorX -= 380;
					invIndex--;
				}
				else {
					cursorX += 380;
					invIndex++;
				}
			}
			else if(itemSelected) {
				if(cursorX == 0) {
					cursorX += 180;
					yesSelected = false;
				}
				else {
					cursorX -= 180;
					yesSelected = true;
				}
			}
			else if(itemUsed) {

			}
		}
		else if(currentMenu.equals("Status")) {}
		else if(currentMenu.equals("Save")) {}
		else if(currentMenu.equals("Settings")) {
			//some code
		}
	}

	private void leftPressed() {
		if(currentMenu.equals("Main")) {}
		else if(currentMenu.equals("Items")) {
			if(!itemSelected && !itemUsed) {
				if(cursorX == 0) {
					cursorX += 380;
					invIndex++;
				}
				else if (!itemUsed){
					cursorX -= 380;
					invIndex--;
				}
			}
		}
		else if(currentMenu.equals("Magic")) {}
		else if(currentMenu.equals("Equip")) {
			if(characterSelected) {
				if(cursorX == 0) {
					cursorX += 380;
					invIndex++;
				}
				else {
					cursorX -= 380;
					invIndex--;
				}
			}
			else if(itemSelected) {
				if(cursorX == 0) {
					cursorX += 180;
					yesSelected = false;
				}
				else {
					cursorX -= 180;
					yesSelected = true;
				}
			}
			else if(itemUsed) {

			}
		}
		else if(currentMenu.equals("Status")) {}
		else if(currentMenu.equals("Save")) {}
		else if(currentMenu.equals("Settings")) {
			//some code
		}
	}
}
