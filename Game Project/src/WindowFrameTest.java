/**Test to see if singleton worked
 * Contains many of the same methods as LoadImageApp, but now calls windowFrame and adds and removes instances
 */

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class WindowFrameTest extends JPanel implements KeyListener {

	private static Dimension _screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static double _windowWidth = _screenSize.getWidth();
    private static double _windowHeight = _screenSize.getHeight();
	private BufferedImage _background, _sprite;
    private int _spriteX = 1000; // X coord of img2
    private int _spriteY = 500; // Y coord of img2
    
    
public WindowFrameTest() {
    	
    	try {
           _background = ImageIO.read(new File("images/SSuMlnY flipped horizontally.jpg"));
           _sprite = ImageIO.read(new File("images/Mario thumbs up.png"));
       } catch (IOException e) {
       }
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
    
    
    
    
	
   public static void main(String[] args) {
      WindowFrame tmp = WindowFrame.getInstance( );
      WindowFrameTest wft = new WindowFrameTest();
      tmp.addState(wft); //adds it
     // tmp.removeState(wft); //removes it
   //   tmp.addState(wft); //then adds it again
      tmp.removeState(wft);
      WindowFrameTest2 wft2 = new WindowFrameTest2();
      
     
     
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
