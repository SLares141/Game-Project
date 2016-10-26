import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class InventoryMenuState extends JPanel implements State {

	Character c;
	Inventory inv;
	
	//BufferedImage cursor;
	int windowWidth = 1024;
	int windowHeight = 576;
	int cursor;
	WindowFrame frame = WindowFrame.getInstance();
	StateMapSingleton stateMap = StateMapSingleton.getInstance();
	StateStackSingleton stateStack = StateStackSingleton.getInstance();
	
	String currentMenu;

	public InventoryMenuState() {
		
		System.out.println("in constructor");
		cursor = 0;
		currentMenu = new String("Main");
		Graphics g = frame.getGraphics();
		
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == (KeyEvent.VK_RIGHT)) {
		            System.out.println("Right key pressed");
		            //cursor -= someAmount;
		        }
		        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
		            System.out.println("Left key pressed");
		            //cursor -= someAmount;
		        }
		        if (e.getKeyCode() == KeyEvent.VK_UP) {
		            System.out.println("Up key pressed");
		            //cursor -= someAmount;
		        }
		        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
		            System.out.println("Down key pressed");
		            //cursor += someAmount;
		        }
		        if	(e.getKeyCode() == KeyEvent.VK_SPACE){
		        	System.out.println("Space key pressed");
		        	select();
		        }
		        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
		        	System.out.println("Backspace key pressed");
		        	back();
		        }
				update();
				render();
			}
		});
		this.setFocusable(true);
	}


	@Override
	public void update() {
		if(currentMenu.equals("Main")) {
			//some code
		}
		else if(currentMenu.equals("Items")) {
			//some code
		}
		else if(currentMenu.equals("Magic")) {
			//some code
		}
		else if(currentMenu.equals("Equip")) {
			//some code
		}
		else if(currentMenu.equals("Save")) {
			//some code
		}
		else if(currentMenu.equals("Settings")) {
			//some code
		}
	}

	@Override
	public void render() {
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		if(currentMenu.equals("Main")) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 1024, 576);	//background
			g.setColor(Color.WHITE);
			g.drawRect(5, 75, 290, 300);	//menu selection
			g.drawRect(5, 5, 997, 65); 		//location
			g.drawRect(5, 380, 290, 152);	//money and game time
			g.drawRect(300, 75, 702, 457);	//party members
			Font fnt0 = new Font("Comic sans MS", Font.PLAIN, 30);
			g.setFont(fnt0);
			g.drawString("Current Location", 400, 50);
			Font fnt1 = new Font("Comic sans MS", Font.PLAIN, 20);
			g.setFont(fnt1);
			g.drawString("Items", 74 , 110);
			g.drawString("Magic", 74 , 170);
			g.drawString("Equip", 74 , 230);
			g.drawString("Save", 74 , 290);
			g.drawString("Settings", 74 , 350);
			
		}
		else if(currentMenu.equals("Items")) {
			//some code
		}
		else if(currentMenu.equals("Magic")) {
			//some code
		}
		else if(currentMenu.equals("Equip")) {
			//some code
		}
		else if(currentMenu.equals("Save")) {
			//some code
		}
		else if(currentMenu.equals("Settings")) {
			//some code
		}
	}

	@Override
	public void onEnter() { 
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onExit() {
		// TODO Auto-generated method stub

	}
	
	private void select() {
		if(currentMenu.equals("Main")) {
			//some code
		}
		else if(currentMenu.equals("Items")) {
			//some code
		}
		else if(currentMenu.equals("Magic")) {
			//some code
		}
		else if(currentMenu.equals("Equip")) {
			//some code
		}
		else if(currentMenu.equals("Save")) {
			//some code
		}
		else if(currentMenu.equals("Settings")) {
			//some code
		}
	}
	
	private void back() {
		if(currentMenu.equals("Main")) {
			stateStack.push("field");
		}
		else if(currentMenu.equals("Items")) {
			//some code
		}
		else if(currentMenu.equals("Magic")) {
			//some code
		}
		else if(currentMenu.equals("Equip")) {
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
