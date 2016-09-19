import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.lang.reflect.Array;

import javax.imageio.*;
import javax.swing.*;

/**
 * This class demonstrates how to load an Image from an external file.
 * Will add to this class to see if pictures can coexist
 */
public class Background extends JPanel {
          
    private BufferedImage _dirt, _grass;
    private Dimension _screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private double _windowWidth = _screenSize.getWidth();
    private double _windowHeight = _screenSize.getHeight();

    
    
    

 

    public Background() {
    	
    	try {
           _dirt= ImageIO.read(new File("images/dirt.png"));
           _grass = ImageIO.read(new File("images/grass.jpg"));
           
       } catch (IOException e) {
       }
    	this.setPreferredSize(new Dimension( (int)_windowWidth, (int)_windowHeight));
       
        

    }
    
    public void paintComponent(Graphics g) {
    	int maxCols = 10;
    	int maxRows = 10;
    	
    	int[][] ImageArray = new int[][]{
    	{	1,1,1,1,1,1,2,2,2,1},
    	{   1,1,1,1,1,1,2,2,2,1},
    	{   1,1,1,1,1,1,1,2,2,2},
    	{   1,1,1,1,1,1,1,2,2,2},
    	{   1,1,1,1,1,1,1,2,2,2},
    	{   1,1,1,1,1,1,1,2,2,2},
    	{   1,1,1,1,1,1,1,2,2,2},
    	{   1,1,1,1,1,1,2,2,2,1},
    	{   1,1,1,1,1,2,2,2,1,1},
    	{   1,1,1,1,1,2,2,2,1,1}
    	};
    	
    
    	for (int row = 0; row < maxRows; row++)
    	{
    		for (int col = 0; col < maxCols; col++)
    		{
    			if (ImageArray[row][col] == 1)
    				g.drawImage(_grass, col*50, row*50, (int)_windowWidth / maxCols, (int)_windowHeight / maxRows, null);
    			else if (ImageArray[row][col] == 2)
    				g.drawImage(_dirt, col*50, row*50,(int)_windowWidth / maxCols, (int)_windowHeight / maxRows, null);
    		}
    		
    	}
      
        
       
    }
    
 

    public void addNotify() {
        super.addNotify();
        requestFocus();
    }
    
    
  
    public static void main(String[] args) {
    	
        
       
    	JFrame f = new JFrame();
        f.getContentPane().add(new Background());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        f.pack();
        f.setVisible(true);
    }
}