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
public class MainMenuState extends JPanel implements State, KeyListener {
	
	BufferedImage background, _arrow;
	int _windowWidth = 960;
	int _windowHeight = 540;
	int _cursor;
	public static JFrame frame;
	
	public static void main(String[] args)
	{
		System.out.println("in main");
		MainMenuState mms = new MainMenuState();
		Graphics g = frame.getGraphics();
		mms.paintComponent(g);
		
		
		
		
	}
	
	public MainMenuState()
	{
		System.out.println("in constructor");
		_cursor = 0;
		frame = new JFrame("Menu");
		frame.setSize(new Dimension(960,540));
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(this);
		this.setFocusable(true);
	}


    public void addNotify() {
        super.addNotify();
        requestFocus();
    }
    
    

	@Override
	public void update() {
		// TODO Auto-generated method stub
		Graphics g = frame.getGraphics();
		paintComponent(g);
		
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
			background = ImageIO.read(new File("images/menuback.png"));
			_arrow = ImageIO.read(new File("images/arrow.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		//JLabel back = new JLabel(new ImageIcon(background));
		//this.add(back);
		

		g.drawImage(background, 0,0, null);
		Font fnt0 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.RED);
		g.drawString("RPG GAME", 350, 100);
		g.drawImage(_arrow, _windowWidth/2 - 100, _windowHeight/2 + _cursor, null);
		//frame.getContentPane().add(this);
       
    }
	 public void createWindow(){
	    	JFrame f = new JFrame();
	        f.getContentPane().add(new LoadImageApp());
	        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	        f.pack(); //NOT NEEDED
	        f.setVisible(true);
	    	
	 };


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
            
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("Left key pressed");
           
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            System.out.println("Up key pressed");
            _cursor-= 50;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            System.out.println("Down key pressed");
            _cursor+= 50;
        }
        if	(e.getKeyCode() == KeyEvent.VK_ENTER){
        	System.out.println("Enter key pressed");
        }
        
		update();
	}
/**
	private void advanceMenu() {
		m.paintComponent1(g);
		
	}

	private void paintComponent1(Graphics g) {
		g.drawImage(background, 0,0, null);
		
	}
	**/

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
