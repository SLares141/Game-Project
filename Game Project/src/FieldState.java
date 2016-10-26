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
		// TODO Auto-generated method stub
		
	}

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

	
	/**
	
	
	import java.awt.*;
	import java.awt.event.*;
	import java.awt.image.*;
	import java.io.*;
	import javax.imageio.*;
	import javax.swing.*;

	
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
*/
