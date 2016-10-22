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
	int windowWidth = 960;
	int windowHeight = 540;
	int cursor;
	WindowFrame _frame = WindowFrame.getInstance();
	
	String currentMenu;

	public InventoryMenuState() {
		
		System.out.println("in constructor");
		cursor = 0;
		currentMenu = new String("Main");
		Graphics g = _frame.getGraphics();
		
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == (KeyEvent.VK_RIGHT)) {
		            System.out.println("Right key pressed");
		            
		        }
		        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
		            System.out.println("Left key pressed");
		           
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
		        	//select method called
		        }
		        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
		        	System.out.println("Backspace key pressed");
		        	//back method called
		        }
				update();
				render();
			}
		});
		this.setFocusable(true);
	}


	@Override
	public void update() {



	}

	@Override
	public void render() {
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
	}

	@Override
	public void onEnter() { 
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onExit() {
		// TODO Auto-generated method stub

	}
}
