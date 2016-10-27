import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
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

public class InventoryMenuState extends JPanel implements State, KeyListener {

	Character c;
	Inventory inv = new Inventory();
	
	BufferedImage arrow;
	int windowWidth = 1024;
	int windowHeight = 576;
	int cursorX;
	int cursorY;
	WindowFrame frame = WindowFrame.getInstance();
	StateMapSingleton stateMap = StateMapSingleton.getInstance();
	StateStackSingleton stateStack = StateStackSingleton.getInstance();
	
	String currentMenu;

	public InventoryMenuState() {
		
		System.out.println("in constructor");
		onEnter();
		
		addKeyListener(this);
		this.setFocusable(true);
		
		try {
			arrow = ImageIO.read(new File("images/small-arrow.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
		if(currentMenu.equals("Main")) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 1024, 576);	//background
			g.setColor(Color.WHITE);
			g.drawRect(5, 75, 250, 305);	//menu selection
			g.drawRect(5, 5, 997, 65); 		//location
			g.drawRect(5, 385, 250, 147);	//money and game time
			g.drawRect(260, 75, 742, 457);	//party members
			
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
			
			g.drawImage(arrow, 25, (88 + cursorY), null);
			
			/*
			 *	also add stuff to the party member section
			 */
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

	@Override
	public void onEnter() { 
		cursorX = 0;
		cursorY = 0;
		currentMenu = new String("Main");
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
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == (KeyEvent.VK_RIGHT)) {
            System.out.println("Right key pressed");
            //cursorX -= someAmount;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("Left key pressed");
            //cursorX -= someAmount;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            System.out.println("Up key pressed");
            cursorY -= 50;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            System.out.println("Down key pressed");
            cursorY += 50;
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


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
