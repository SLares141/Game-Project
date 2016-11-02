import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InventoryMenuState extends JPanel implements State {

	Character player;
	Inventory inv = new Inventory();
	
	BufferedImage arrow;
	int cursorX;
	int cursorY;
	WindowFrame frame = WindowFrame.getInstance();
	int windowWidth = frame.getWidth();
	int windowHeight = frame.getHeight();
	StateMapSingleton stateMap = StateMapSingleton.getInstance();
	StateStackSingleton stateStack = StateStackSingleton.getInstance();
	String currentMenu;
	String[] menus = {"Main", "Items", "Magic", "Equip", "Save", "Settings"};


	public InventoryMenuState(Character c, Inventory i) {
		System.out.println("in constructor");
		
		player = c;
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
			cursorY = 0;
		}
		else if(currentMenu.equals("Items")) {
			
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

	@Override
	public void render() {
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, windowWidth, windowHeight);	//background
		g.setColor(Color.WHITE);
		g.drawRect(5, 75, 250, 305);	//menu selection
		g.drawRect(5, 5, 997, 65); 		//location
		g.drawRect(5, 385, 250, 147);	//money and game time
		g.drawRect(260, 75, 742, 457);	//party members or items
		
		Font fnt0 = new Font("Comic sans MS", Font.PLAIN, 30);
		g.setFont(fnt0);
		g.drawString("Current Location", 385, 50);
		
		Font fnt1 = new Font("Comic sans MS", Font.PLAIN, 20);
		g.setFont(fnt1);
		g.drawString("Items", 74, 110);
		g.drawString("Magic", 74, 160);
		g.drawString("Equip", 74, 210);
		g.drawString("Status", 74, 260);
		g.drawString("Save", 74, 310);
		g.drawString("Settings", 74, 360);
		
		g.drawString("Money: ", 30, 440);
		g.drawString("$" + inv.getMoney(), 100, 440);
		g.drawString("Time: ", 30, 495);
		
		if(currentMenu.equals("Main")) {
			g.drawImage(arrow, 25, (88 + cursorY), null);
			
			/*
			 *	also add stuff to the party member section
			 */
		}
		else if(currentMenu.equals("Items")) {
			g.drawImage(arrow, (344 + cursorX), (234 + cursorY), null);
		}
		else if(currentMenu.equals("Magic")) {
			g.drawImage(arrow, (344 + cursorX), (234 + cursorY), null);
		}
		else if(currentMenu.equals("Equip")) {
			g.drawImage(arrow, (344 + cursorX), (234 + cursorY), null);
			/*
			 *	also add stuff to the party member section
			 */
			
			//and then the equip screen
		}
		else if(currentMenu.equals("Status")) {
			g.drawImage(arrow, (344 + cursorX), (234 + cursorY), null);
			/*
			 *	also add stuff to the party member section
			 */
			
			//and then the status screen
		}
		else if(currentMenu.equals("Save")) {
			//draw a new, smaller rectangle
			g.drawImage(arrow, (344 + cursorX), (234 + cursorY), null);
		}
		else if(currentMenu.equals("Settings")) {
			g.drawImage(arrow, (344 + cursorX), (234 + cursorY), null);
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
		frame.removeState(this); //neccessary when exiting a state.
		stateStack.push("menu"); //push field state to top of stack
		frame.addState(stateStack.peek()); // add the new state to the frame.
		stateStack.peek().onEnter();
	}
	
	private void select() {
		if(currentMenu.equals("Main")) {
			currentMenu = menus[cursorY/50 + 1];
			update();
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
	
	private void back() {
		if(currentMenu.equals("Main")) {
			onExit();
		}
		else if(currentMenu.equals("Items")) {
			currentMenu = "Main";
		}
		else if(currentMenu.equals("Magic")) {
			currentMenu = "Main";
		}
		else if(currentMenu.equals("Equip")) {
			currentMenu = "Main";
		}
		else if(currentMenu.equals("Status")) {
			currentMenu = "Main";
		}
		else if(currentMenu.equals("Save")) {
			currentMenu = "Main";
		}
		else if(currentMenu.equals("Settings")) {
			currentMenu = "Main";
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
			//some code
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
	
	private void downPressed() {
		if(currentMenu.equals("Main")) {
        	if(cursorY == 250)
        		cursorY = 0;
        	else
        		cursorY += 50;
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
	
	private void rightPressed() {
		if(currentMenu.equals("Main")) {}
		else if(currentMenu.equals("Items")) {
			//some code
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
			//some code
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
