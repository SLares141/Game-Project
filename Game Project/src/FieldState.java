import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
	
	BufferedImage background = null;
	int windowWidth = 960;
	int windowHeight = 540;
	public static JFrame frame;
	
	public static void main(String[] args)
	{
		System.out.println("in main");
		FieldState fs = new FieldState();
		Graphics g = frame.getGraphics();
		fs.paintComponent(g);
	}
	
	public FieldState()
	{
		System.out.println("in constructor");
		frame = new JFrame("Field");
		frame.setSize(new Dimension(960,540));
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


    public void addNotify() {
        super.addNotify();
        requestFocus();
    }
    
    

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
		
	}

	public void render() {
		

		
		
		
	}
	public void paintComponent(Graphics g) {


		
		System.out.println("in paintcomp");
		
		/**
		 * real fullscreen below, replace setsize method if you want to use this.
		 * Note: X button doesn't exist
		*/
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		//frame.setUndecorated(true);
		
		//this.setBackground(Color.BLUE);
		//frame.getContentPane().add(this);
		
		
		//JButton button = new JButton("this is a button");
		//panel.add(button);
		
		//JTextField textfield = new JTextField();
		//textfield.setPreferredSize(new Dimension(200,15));
		//panel.add(textfield);
		
		//JButton button2 = new JButton("this is another button2");
		//panel.add(button2);
		
		
		
		
		try {
			background = ImageIO.read(new File("images/dirt.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		//JLabel back = new JLabel(new ImageIcon(background));
		//this.add(back);
		

		g.drawImage(background, 0,0, null);
		Font fnt0 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.GREEN);
		g.drawString("the field", 350, 100);
		//frame.getContentPane().add(this);
       
    }
	/**
	 public void createWindow(){
	    	JFrame f = new JFrame();
	        f.getContentPane().add(new LoadImageApp());
	        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	        f.pack(); //NOT NEEDED
	        f.setVisible(true);
	    	
	 };

*/
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
		// TODO Auto-generated method stub
		
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