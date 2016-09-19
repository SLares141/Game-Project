import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

/**
 * This class demonstrates how to load an Image from an external file.
 * Will add to this class to see if pictures can coexist
 */
public class LoadImageApp extends JPanel implements KeyListener {
          
    private BufferedImage _background, _sprite;
    private int _spriteX = 1000; // X coord of img2
    private int _spriteY = 500; // Y coord of img2
    
    
    private Dimension _screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private double _windowWidth = _screenSize.getWidth();
    private double _windowHeight = _screenSize.getHeight();
    
    

 

    public LoadImageApp() {
    	
    	try {
           _background = ImageIO.read(new File("images/SSuMlnY flipped horizontally.jpg"));
           _sprite = ImageIO.read(new File("images/Mario thumbs up.png"));
       } catch (IOException e) {
       }
    	this.setPreferredSize(new Dimension((int)_windowWidth, (int)_windowHeight));
        addKeyListener(this);
        

    }
    
    public void paintComponent(Graphics g) {
        g.drawImage(_background, 0, 0, (int) _windowWidth, (int) _windowHeight, null); //draws background image, sets coordinates and width and height
        g.drawImage(_sprite, _spriteX, _spriteY, 100, 150, null);
       
    }
    
 

    public void addNotify() {
        super.addNotify();
        requestFocus();
    }
    
    
    
    public void createWindow(){
    	JFrame f = new JFrame();
        f.getContentPane().add(new LoadImageApp());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        f.pack();
        f.setVisible(true);
    	
    };

	@Override
	public void keyPressed(KeyEvent e) {
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

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
    
	


	
}