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


/* 
 * This might be a good place to start, since it seems like the simplest state.
 */
public class FieldState extends JPanel implements State, KeyListener {
	
    private BufferedImage _background, _sprite;
    private int _spriteX = 1000; // X coord of img2
    private int _spriteY = 500; // Y coord of img2
    
    
	int _windowWidth = 1024;
	int _windowHeight = 576;

	WindowFrame _frame = WindowFrame.getInstance(); // should this be static??
	StateMapSingleton stateMap = StateMapSingleton.getInstance();
	StateStackSingleton stateStack = StateStackSingleton.getInstance();
	
	
	// this is the name of the state that the field state will transition to.
	//onExit function will look at this, and push the string to the state stack
	//this will allow the proper state to be put on the stack, and to transition accordingly.
	// in the case of field state, this will be either main menu, inventory, or battle states.
	String _stateDestination; 
	

		
		
	
	public FieldState()
	{
		System.out.println("in constructor");
		
		Graphics g = _frame.getGraphics();
		addKeyListener(this);
		this.setFocusable(true);

		try {
	           _background = ImageIO.read(new File("images/menuback.png"));
	           _sprite = ImageIO.read(new File("images/strawberry.png"));
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
        
		
		
	}

	public void render() {
		repaint();
		
	}
	public void paintComponent(Graphics g) {
		    g.drawImage(_background, 0, 0, (int) _windowWidth, (int) _windowHeight, null); //draws background image, sets coordinates and width and height
		    g.drawImage(_sprite, _spriteX, _spriteY, 100, 150, null);
		   
		}
    
	

	@Override
	public void onEnter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onExit() {
		_frame.removeState(this); //neccessary when exiting a state.
		
		if(_stateDestination.equals("menu"))
			stateStack.pop(); //remove this from the stack
		else
			stateStack.push(_stateDestination); //in this case, we are adding a state that belongs "after" field state
		
		_frame.addState(stateStack.peek()); // add the new state to the frame.
	}

	@Override
	public void keyPressed(KeyEvent e) {
		 if (e.getKeyCode() == KeyEvent.VK_ESCAPE ) { //press escape to go to menu. WILL BE CHANGED!
	            System.out.println("Back to main menu!");
	            _stateDestination = "menu";
	            onExit();
	        }      
		 else //indicates the player is moving, not escaping to menu
		 {
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
	            System.out.println("Right key pressed");
	            _spriteX += 10;
	        }
	        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
	            System.out.println("Left key pressed");
	            _spriteX -= 10;
	        }
	        if (e.getKeyCode() == KeyEvent.VK_UP) {
	            System.out.println("Up key pressed");
	            _spriteY -= 10;
	        }
	        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
	            System.out.println("Down key pressed");
	            _spriteY += 10;
	        }
	        
	       
	        
		repaint();
		
		 }
	}


		
	

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}

	

