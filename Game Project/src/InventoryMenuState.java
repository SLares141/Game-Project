import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class InventoryMenuState extends JPanel implements State {

	Player player;
	PartyMember pm1, pm2, pm3;
	Inventory inv;
	int invIndex = 0;

	BufferedImage arrow, biggerArrow;
	int cursorX;
	int cursorY;
	boolean savePrompt;
	boolean itemSelected;
	WindowFrame frame = WindowFrame.getInstance();
	int windowWidth = frame.getWidth();
	int windowHeight = frame.getHeight();
	StateMapSingleton stateMap = StateMapSingleton.getInstance();
	StateStackSingleton stateStack = StateStackSingleton.getInstance();
	String _stateDestination = "field";
	String currentMenu;
	int previousMenu = 0;
	String[] menus = {"Items", "Magic", "Equip", "Status", "Save", "Settings"};


	public InventoryMenuState(Player p, PartyMember pm1, PartyMember pm2, PartyMember pm3, Inventory i) {
		System.out.println("in constructor");

		player = p;
		this.pm1 = pm1;
		this.pm2 = pm2;
		this.pm3 = pm3;
		inv = i;

		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT 
						|| e.getKeyCode() == KeyEvent.VK_D) {
					System.out.println("Right key pressed");
					rightPressed();
				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT
						|| e.getKeyCode() == KeyEvent.VK_A) {
					System.out.println("Left key pressed");
					leftPressed();
				}
				if (e.getKeyCode() == KeyEvent.VK_UP
						|| e.getKeyCode() == KeyEvent.VK_W) {
					System.out.println("Up key pressed");
					upPressed();
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN
						|| e.getKeyCode() == KeyEvent.VK_S) {
					System.out.println("Down key pressed");
					downPressed();
				}
				if	(e.getKeyCode() == KeyEvent.VK_ENTER
						|| e.getKeyCode() == KeyEvent.VK_SPACE){
					System.out.println("Enter key pressed");
					select();
				}
				if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE
						|| e.getKeyCode() == KeyEvent.VK_CAPS_LOCK){
					System.out.println("Backspace key pressed");
					back();
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
		if(currentMenu.equals("Main")) {
			cursorX = 0;
			if(previousMenu == 0) 
				cursorY = 0;
			else 
				cursorY = (50*previousMenu) ;

		}
		else if(currentMenu.equals("Items")) {
			if(!itemSelected) {
				cursorX = 0;
				cursorY = 0;
			}
			itemSelected = false;
		}
		else if(currentMenu.equals("Magic")) {
			cursorX = 0;
			cursorY = 0;
		}
		else if(currentMenu.equals("Equip")) {
			cursorX = 0;
			cursorY = 0;
		}
		else if(currentMenu.equals("Status")) {
			cursorX = 0;
			cursorY = 0;
		}
		else if(currentMenu.equals("Save")) {
			cursorX = 0;
			cursorY = 0;
			savePrompt = true;
		}
		else if(currentMenu.equals("Settings")) {
			cursorX = 0;
			cursorY = 0;
		}
	}

	@Override
	public void render() {
		repaint();
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		Font large = new Font("Comic sans MS", Font.PLAIN, 32);
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
		g.drawString("Time: ", 30, 525);

		//draws party
		g.drawImage(player.getMenuSprite(), 360, 95, null);
		g.drawRect(360, 95, 100, 100);
		g.drawImage(pm1.getMenuSprite(), 360, 215, null);
		g.drawRect(360, 215, 100, 100);
		g.drawImage(pm2.getMenuSprite(), 360, 335, null);
		g.drawRect(360, 335, 100, 100);
		g.drawImage(pm3.getMenuSprite(), 360, 455, null);
		g.drawRect(360, 455, 100, 100);

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
				g.drawImage(player.getMenuSprite(), 360, 95, null);
				g.drawRect(360, 95, 100, 100);
				g.drawImage(pm1.getMenuSprite(), 360, 215, null);
				g.drawRect(360, 215, 100, 100);
				g.drawImage(pm2.getMenuSprite(), 360, 335, null);
				g.drawRect(360, 335, 100, 100);
				g.drawImage(pm3.getMenuSprite(), 360, 455, null);
				g.drawRect(360, 455, 100, 100);
				g.drawImage(biggerArrow, (280 + cursorX), (120 + cursorY), null);
			}
			else 
				g.drawImage(arrow, (280 + cursorX), (103 + cursorY), null);

		}
		else if(currentMenu.equals("Magic")) {
			g.drawImage(arrow, 25, 153, null);
			g.drawImage(biggerArrow, (280 + cursorX), (120 + cursorY), null);
		}
		else if(currentMenu.equals("Equip")) {
			g.drawImage(arrow, 25, 203, null);
			g.drawImage(biggerArrow, (280 + cursorX), (120 + cursorY), null);
			/*
			 *	also add stuff to the party member section
			 */

			//and then the equip screen
		}
		else if(currentMenu.equals("Status")) {
			g.drawImage(arrow, 25, 253, null);
			g.drawImage(biggerArrow, (280 + cursorX), (120 + cursorY), null);
			/*
			 *	also add stuff to the party member section
			 */

			//and then the status screen
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
		stateStack.pop();
		
	}

	private void select() {
		if(currentMenu.equals("Main")) {
			currentMenu = menus[cursorY/50];
			update();
		}
		else if(currentMenu.equals("Items")) {
			itemSelected = true;
		}
		else if(currentMenu.equals("Magic")) {
			//some code
		}
		else if(currentMenu.equals("Equip")) {
			//some code
		}
		else if(currentMenu.equals("Status")) {
			//some code
		}
		else if(currentMenu.equals("Save")) {
			if(!savePrompt) {
				currentMenu = "Main";
				previousMenu = 4;
				update();
			}
		}
		else if(currentMenu.equals("Settings")) {
			//some code
		}
	}

	private void back() {
		if(currentMenu.equals("Main")) {
			onExit();
		}
		else if(currentMenu.equals("Items")) {
			if(itemSelected) {
				update();
			}
			else {
				currentMenu = "Main";
				previousMenu = 0;
				update();
			}
		}
		else if(currentMenu.equals("Magic")) {
			currentMenu = "Main";
			previousMenu = 1;
			update();
		}
		else if(currentMenu.equals("Equip")) {
			currentMenu = "Main";
			previousMenu = 2;
			update();
		}
		else if(currentMenu.equals("Status")) {
			currentMenu = "Main";
			previousMenu = 3;
			update();
		}
		else if(currentMenu.equals("Save")) {
			currentMenu = "Main";
			previousMenu = 4;
			update();
		}
		else if(currentMenu.equals("Settings")) {
			currentMenu = "Main";
			previousMenu = 5;
			update();
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
				
				if(cursorY == 0)
					cursorY = 360;
				else 
					cursorY -= 120;
			}
			else {
				if(cursorY == 0) {
					cursorY = 400;
					invIndex = invIndex + (16 -1);
				}
				else {
					cursorY -= 50;
					invIndex = invIndex - 2;
				}
			}
		}
		else if(currentMenu.equals("Magic")) {
			if(cursorY == 0)
				cursorY = 360;
			else 
				cursorY -= 120;
		}
		else if(currentMenu.equals("Equip")) {
			if(cursorY == 0)
				cursorY = 360;
			else 
				cursorY -= 120;
		}
		else if(currentMenu.equals("Status")) {
			if(cursorY == 0)
				cursorY = 360;
			else 
				cursorY -= 120;
		}
		else if(currentMenu.equals("Save")) {
			if(cursorY == 0) {
				cursorY = 65;
				savePrompt = false;
			}	
			else {
				cursorY -= 65;
				savePrompt = true;
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
				if(cursorY == 360)
					cursorY = 0;
				else
					cursorY += 120;
			}
			else {
				if(cursorY == 400) {
					cursorY = 0;
					invIndex = invIndex - (16 - 1);
				}
				else {
					cursorY += 50;
					invIndex = invIndex + 2;
				}
			}
		}
		else if(currentMenu.equals("Magic")) {
			if(cursorY == 360)
				cursorY = 0;
			else
				cursorY += 120;
		}
		else if(currentMenu.equals("Equip")) {
			if(cursorY == 360)
				cursorY = 0;
			else
				cursorY += 120;
		}
		else if(currentMenu.equals("Status")) {
			if(cursorY == 360)
				cursorY = 0;
			else
				cursorY += 120;
		}
		else if(currentMenu.equals("Save")) {
			if(cursorY == 65) {
				cursorY = 0;
				savePrompt = true;
			}
			else {
				cursorY += 65;
				savePrompt = false;
			}
		}
		else if(currentMenu.equals("Settings")) {
			//some code
		}
	}

	private void rightPressed() {
		if(currentMenu.equals("Main")) {}
		else if(currentMenu.equals("Items")) {
			if(cursorX == 0) {
				cursorX += 380;
				invIndex++;
			}
			else {
				cursorX -= 380;
				invIndex--;
			}
		}
		else if(currentMenu.equals("Magic")) {
			//some code
		}
		else if(currentMenu.equals("Equip")) {
			//some code
		}
		else if(currentMenu.equals("Status")) {
			//some code
		}
		else if(currentMenu.equals("Save")) {
			//some code
		}
		else if(currentMenu.equals("Settings")) {
			//some code
		}
	}

	private void leftPressed() {
		if(currentMenu.equals("Main")) {}
		else if(currentMenu.equals("Items")) {
			if(cursorX == 380) {
				cursorX -= 380;
				invIndex--;
			}
			else {
				cursorX += 380;
				invIndex++;
			}
		}
		else if(currentMenu.equals("Magic")) {
			//some code
		}
		else if(currentMenu.equals("Equip")) {
			//some code
		}
		else if(currentMenu.equals("Status")) {
			//some code
		}
		else if(currentMenu.equals("Save")) {
			//some code
		}
		else if(currentMenu.equals("Settings")) {
			//some code
		}
	}
}
