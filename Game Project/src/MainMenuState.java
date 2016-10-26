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
public class MainMenuState 
	extends JPanel 
		implements State, KeyListener {
	
	BufferedImage background, _arrow;
	int _windowWidth = 1024;
	int _windowHeight = 576;
	int _cursor;
	WindowFrame _frame = WindowFrame.getInstance(); // should this be static??
	
	String _currentMenu;
	
	//load the singleton classes
	StateMapSingleton stateMap = StateMapSingleton.getInstance();
	StateStackSingleton stateStack = StateStackSingleton.getInstance();
	
	

	/*
	public static void main(String[] args)
	{
		System.out.println("in main");
		MainMenuState mms = new MainMenuState();
		Graphics g = _frame.getGraphics();
		mms.paintComponent(g);
	}
	*/	
		
	
	public MainMenuState()
	{
		System.out.println("in constructor");
		_cursor = 0;
		_currentMenu = new String("Start");
		Graphics g = _frame.getGraphics();
		//this.paintComponent(g);
		/*
		_frame.setSize(new Dimension(960,540));
		_frame.setLocationRelativeTo(null);
		_frame.setResizable(false);
		_frame.setVisible(true);
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		*/
		addKeyListener(this);
		
		this.setFocusable(true);

		try {
			background = ImageIO.read(new File("images/menuback2.jpg"));
			_arrow = ImageIO.read(new File("images/arrow.png"));
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
        
		if (_currentMenu.equals("Start")){
			_cursor = 0;
		}
		if(_currentMenu.equals("Main")){
			if (_cursor < 0){
				System.out.println(" cursor < 0");
				_cursor += 50;
			}else if (_cursor > 100){				
				System.out.println(" cursor > 150");
				_cursor -=50;
			}
		}
		if(_currentMenu.equals("Settings")){
			
		}
		
	}

	public void render() {
		//Graphics g = _frame.getGraphics();
		repaint();
		
	}
	public void paintComponent(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		if (_currentMenu.equals("Start")){

			g.drawImage(background, 0,0, null);
			/*Rectangle test = new Rectangle(0,0,1024,576);
			g2d.setColor(Color.BLACK);
			g2d.fill(test);
			*/
			Font fnt0 = new Font("Comic sans MS", Font.BOLD, 50);
			g.setFont(fnt0);
			g.setColor(Color.BLACK);
			g.drawString("SAMPLE TEXT:", _windowWidth/2 - 190, 100);
			Font fnt2 = new Font("comic sans MS", Font.PLAIN, 30);
			g.setFont(fnt2);
			g.drawString(" The Adventures of (INSERT NAME HERE)", _windowWidth/2-320, _windowHeight/2 -125);
			g.drawString("Press Enter", _windowWidth/2 - 80, 300);
			//g.drawImage(_arrow, _windowWidth/2 - 200, (_windowHeight/2 - 100)+ _cursor, null);
			//frame.getContentPane().add(this);
			
			//PRINTS A GRID
			/*
			int i=0,j=0;
			for(i = 0; i < 32; i++){
				g.setColor(Color.gray);
				if(i==16)
					g.setColor(Color.red);
				g.drawLine(i*32, 0, i*32, 576);
			}
			for(j = 0; j < 18; j++){
				g.setColor(Color.gray);
				if(i==9)
					g.setColor(Color.red);
				g.drawLine(0, j*32, 1024, j*32);
			}*/
		}else if (_currentMenu.equals("Main")){
			
			
			g.drawImage(background, 0,0, null);
			//g.fillRect(0, 0, _windowWidth, _windowHeight);
			Font fnt0 = new Font("arial", Font.BOLD, 50);
			g.setFont(fnt0);
			g.setColor(Color.RED);
			g.drawString("Menu", _windowWidth/2 -64, 100);
			
			g.drawImage(_arrow, _windowWidth/2 -160, (_windowHeight/2 - 116)+ _cursor, null);
			
			Rectangle playButton		= new Rectangle(_windowWidth/2 -100, 175, 200, 40);
			Rectangle settingsButton	= new Rectangle(_windowWidth/2 -100, 225, 200, 40);
			Rectangle quitButton		= new Rectangle(_windowWidth/2 -100, 275, 200, 40);
			
			Font font1 = new Font("arial", Font.BOLD, 30);
			g2d.setFont(font1);
			g2d.setColor(Color.MAGENTA);
			g.drawString("Play", playButton.x + 70, playButton.y + 30);
			g2d.draw(playButton);
			g.drawString("Settings", settingsButton.x + 45, settingsButton.y + 30);
			g2d.draw(settingsButton);
			g.drawString("Quit", quitButton.x + 70, quitButton.y + 30);
			g2d.draw(quitButton);
			//frame.getContentPane().add(this);
			//GRID FOR FIXING PLACEMENT
			/* 
			int i=0,j=0;
			for(i = 0; i < 32; i++){
				g.setColor(Color.gray);
				if(i==16)
					g.setColor(Color.red);
				g.drawLine(i*32, 0, i*32, 576);
			}
			for(j = 0; j < 18; j++){
				g.setColor(Color.gray);
				if(i==9)
					g.setColor(Color.red);
				g.drawLine(0, j*32, 1024, j*32);
			}*/
		}else if (_currentMenu.equals("Settings")){
			g.setColor(Color.GRAY);
			g.fillRect(0, 0, _windowWidth, _windowHeight);
			
			Font fnt3 = new Font("Comic Sans MS", Font.PLAIN, 50);
			g.setFont(fnt3);
			g.setColor(Color.WHITE);
			g.drawString("Settings", _windowWidth/2 - 70, 100);
			
			/*
			 * things to add:
			 * 		settings buttons
			 * 		controls
			 * 		etc.
			 * 
			 */
			
		}
       
    }
	

	@Override
	public void onEnter() {
		// CAN SET CURSOR TO TOP POSITION OF LIST
		
	}

	@Override
	public void onExit() { //CALLED WHEN PLAY IS PRESSED
		stateStack.push("field");
		_frame.addState(stateStack.peek());
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT 
				|| e.getKeyCode() == KeyEvent.VK_D) {
            System.out.println("Right key pressed");
            
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT
				|| e.getKeyCode() == KeyEvent.VK_A) {
            System.out.println("Left key pressed");
           
        }
        if (e.getKeyCode() == KeyEvent.VK_UP
				|| e.getKeyCode() == KeyEvent.VK_W) {
            System.out.println("Up key pressed");
            _cursor-= 50;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN
				|| e.getKeyCode() == KeyEvent.VK_S) {
            System.out.println("Down key pressed");
            _cursor+= 50;
        }
        if	(e.getKeyCode() == KeyEvent.VK_ENTER
				|| e.getKeyCode() == KeyEvent.VK_SPACE){
        	System.out.println("Enter key pressed");
        	transition();
        }
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE
				|| e.getKeyCode() == KeyEvent.VK_CAPS_LOCK){
        	System.out.println("Backspace key pressed");
        	backtransition();
        }
        update();
		render();
	}
	private void backtransition() {
		if(_currentMenu.equals("Main")){
			_currentMenu = "Start";
		}else if(_currentMenu.equals("Settings")){
			_currentMenu = "Main";
		}
		
	}

	private void transition() {
		if(_currentMenu.equals("Start")){
			_currentMenu = "Main";
		}else if(_currentMenu.equals("Main")){
			if ((_cursor/50) == 0){
				System.out.println("Play Pressed");
				onExit(); //exits this state, goes to field state.
				
				
			}else if((_cursor/50)== 1){
				System.out.println("Settings Pressed");
				_currentMenu = "Settings";
			}else if ((_cursor/50)== 2){
				System.out.println("Quit Pressed");
				
				_frame.quit();
			}
		}else if(_currentMenu.equals("Settings")){
			/*
			 * settings code tbd
			 * 
			 * 
			 * 
			 */
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