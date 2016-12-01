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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
	
	Player player;
	Inventory inv;
	
	//load the singleton classes
	StateMapSingleton stateMap = StateMapSingleton.getInstance();
	StateStackSingleton stateStack = StateStackSingleton.getInstance();	
		
	
	public MainMenuState(Player player, Inventory inv)
	{
		System.out.println("in constructor");
		_cursor = 0;
		_currentMenu = new String("Start");
		this.player = player;
		this.inv = inv;
		Graphics g = _frame.getGraphics();
		addKeyListener(this);
		
		this.setFocusable(true);

		try {
			background = ImageIO.read(new File("images/menuback2.jpg"));
			_arrow = ImageIO.read(new File("images/arrow.png"));
		} catch (IOException e) {
			
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
			}else if (_cursor > 150){				
				System.out.println(" cursor > 200");
				_cursor -=50;
			}
		}
		if(_currentMenu.equals("Settings")){
			
		}
		
	}

	public void render() {
		repaint();
		
	}
	public void paintComponent(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		if (_currentMenu.equals("Start")){

			g.drawImage(background, 0,0, null);
			Font fnt0 = new Font("Comic sans MS", Font.BOLD, 50);
			g.setFont(fnt0);
			g.setColor(Color.BLACK);
			g.drawString("FRUIT VS VEGETABLES:", _windowWidth/2 - 300, 100);
			Font fnt2 = new Font("comic sans MS", Font.PLAIN, 30);
			g.setFont(fnt2);
			g.drawString(" The Adventures of Straw the Berry", _windowWidth/2 - 275, _windowHeight/2 -125);
			g.drawString("Press Enter", _windowWidth/2 - 80, 300);
			
			
		}else if (_currentMenu.equals("Main")){
			
			g.drawImage(background, 0,0, null);
			Font fnt0 = new Font("Comic sans MS", Font.BOLD, 50);
			g.setFont(fnt0);
			g.setColor(Color.RED);
			g.drawString("Main Menu", (_windowWidth-g.getFontMetrics().stringWidth("Main Menu"))/2, 100);
			
			g.drawImage(_arrow, _windowWidth/2 -160, (_windowHeight/2 - 116)+ _cursor, null);
			
			Rectangle newGameButton		= new Rectangle(_windowWidth/2 -100, 175, 200, 40);
			Rectangle continueButton	= new Rectangle(_windowWidth/2 -100, 225, 200, 40);
			Rectangle settingsButton	= new Rectangle(_windowWidth/2 -100, 275, 200, 40);
			Rectangle quitButton		= new Rectangle(_windowWidth/2 -100, 325, 200, 40);
			
			Font font1 = new Font("Comic sans MS", Font.BOLD, 30);
			g2d.setFont(font1);
			g2d.setColor(Color.MAGENTA);
			g.drawString("New Game", newGameButton.x + 30, newGameButton.y + 30);
			g2d.draw(newGameButton);
			g.drawString("Continue", continueButton.x + 45, continueButton.y + 30);
			g2d.draw(continueButton);
			g.drawString("Settings", settingsButton.x + 45, settingsButton.y + 30);
			g2d.draw(settingsButton);
			g.drawString("Quit", quitButton.x + 70, quitButton.y + 30);
			g2d.draw(quitButton);
		
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
		//this.requestFocusInWindow();
		repaint();
		this.addNotify();
	}

	@Override
	public void onExit() { 
		/**
		 * left blank, may not need to fill since this is the first state in the program.
		 * 
		 * 
		 */
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
				System.out.println("NewGame Pressed");
				FileReader inFile;				
				try {
					try {
						player._sprite = ImageIO.read(new File("images/strawberry.png"));
						player._menuSprite = ImageIO.read(new File("images/menuStrawberry.png"));
						player._smallMenuSprite = ImageIO.read(new File("images/smallMenuStrawberry.png"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					inFile = new FileReader("PlayerFiles/DefaultStats");
					BufferedReader buffReader = new BufferedReader(inFile);
					ArrayList<String> temp = new ArrayList<String>();
					String line;
					try {
						while((line = buffReader.readLine()) != null)
							temp.add(line);
					} catch (IOException e) {
						e.printStackTrace();
					}
					player.setStr(Integer.valueOf(temp.get(0)));
					player.setDef(Integer.valueOf(temp.get(1)));
					player.setStrMag(Integer.valueOf(temp.get(2)));
					player.setDefMag(Integer.valueOf(temp.get(3)));
					player.setHealth(Integer.valueOf(temp.get(4)));
					player.setTotalHealth(Integer.valueOf(temp.get(5)));
					player.setMagic(Integer.valueOf(temp.get(6)));
					player.setTotalMagic(Integer.valueOf(temp.get(7)));
					player.setLevel(Integer.valueOf(temp.get(8)));
					player.setExp(Integer.valueOf(temp.get(9)));
					player.setMoney(Integer.valueOf(temp.get(10)));
					player.setEnemyLevel(Integer.valueOf(temp.get(11)));
					player.setIsDead(Boolean.valueOf(temp.get(12)));
					Weapon w = (Weapon)Inventory.itemMap.get(temp.get(13));
					player.setWeapon(w);
					Armor a = (Armor)Inventory.itemMap.get(temp.get(14));
					player.setArmor(a);
					player.setspaddress(temp.get(15));
					int x = Integer.valueOf(temp.get(16));
					int y = Integer.valueOf(temp.get(17));
					player.setLocation(new Coordinate(x,y));
					player.setMap(Integer.valueOf(temp.get(18)));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// LOADS SPRITE FROM FILES
				try {
					player.sprite = ImageIO.read(new File(player.getspaddress()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				player.updateExp4NextLvl();
				
				
				try {
					inFile = new FileReader("InventoryFiles/DefaultValues");
					BufferedReader buffReader = new BufferedReader(inFile);
					ArrayList<String> temp = new ArrayList<String>();
					String line;
					try {
						while((line = buffReader.readLine()) != null)
							temp.add(line);
					} catch (IOException e) {
						e.printStackTrace();
					}
					inv.clear();
					//loads default items
					for(int j = 0; j < 72; j++) {
						inv.add(Inventory.itemMap.get(temp.get(j)), Integer.valueOf(temp.get(j+1)));
						j++;
					}
					
					//loads default menu settings
					InventoryMenuState.textColor = new Color(Integer.parseInt(temp.get(72)));
					InventoryMenuState.backgroundColor = new Color(Integer.parseInt(temp.get(73)));

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				stateStack.incrementCount();
				stateMap.put("field" + stateStack.getCount(), new FieldState(player));
				((FieldState) stateMap.get("field" + stateStack.getCount())).getPlayer().resetPlayer();
				stateStack.push("field" + stateStack.getCount());
				stateStack.incrementCount();
				stateMap.put("field" + stateStack.getCount(), new FieldState(player));
				stateStack.popAndPush();

			}else if((_cursor/50)== 1){
				System.out.println("Continue Pressed");
				FileReader inFile;
				try {
					try {
						player._sprite = ImageIO.read(new File("images/strawberry.png"));
						player._menuSprite = ImageIO.read(new File("images/menuStrawberry.png"));
						player._smallMenuSprite = ImageIO.read(new File("images/smallMenuStrawberry.png"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					inFile = new FileReader("PlayerFiles/Player0");

					BufferedReader buffReader = new BufferedReader(inFile);
					ArrayList<String> temp = new ArrayList<String>();
					String line;
					try {
						while((line = buffReader.readLine()) != null)
							temp.add(line);
					} catch (IOException e) {
						e.printStackTrace();
					}
					player.setStr(Integer.valueOf(temp.get(0)));
					player.setDef(Integer.valueOf(temp.get(1)));
					player.setStrMag(Integer.valueOf(temp.get(2)));
					player.setDefMag(Integer.valueOf(temp.get(3)));
					player.setHealth(Integer.valueOf(temp.get(4)));
					player.setTotalHealth(Integer.valueOf(temp.get(5)));
					player.setMagic(Integer.valueOf(temp.get(6)));
					player.setTotalMagic(Integer.valueOf(temp.get(7)));
					player.setLevel(Integer.valueOf(temp.get(8)));
					player.setExp(Integer.valueOf(temp.get(9)));
					player.setMoney(Integer.valueOf(temp.get(10)));
					player.setEnemyLevel(Integer.valueOf(temp.get(11)));
					player.setIsDead(Boolean.valueOf(temp.get(12)));
					Weapon w = (Weapon)Inventory.itemMap.get(temp.get(13));
					player.setWeapon(w);
					Armor a = (Armor)Inventory.itemMap.get(temp.get(14));
					player.setArmor(a);
					player.setspaddress(temp.get(15));
					int x = Integer.valueOf(temp.get(16));
					int y = Integer.valueOf(temp.get(17));
					player.setLocation(new Coordinate(x,y));
					player.setMap(Integer.valueOf(temp.get(18)));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// LOADS SPRITE FROM FILES
				try {
					player.sprite = ImageIO.read(new File(player.getspaddress()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				player.updateExp4NextLvl();

				//loading inventory stuff
				inv.clear();
				try {
					inFile = new FileReader("InventoryFiles/Items");
					BufferedReader buffReader = new BufferedReader(inFile);
					ArrayList<String> temp = new ArrayList<String>();
					String line;
					try {
						while((line = buffReader.readLine()) != null)
							temp.add(line);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					for(int j = 0; j < 36; j++) {
						inv.add(Inventory.itemMap.get(temp.get(j)), Integer.valueOf(temp.get(j+1)));
						j++;
					}

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					inFile = new FileReader("InventoryFiles/Equipment");
					BufferedReader buffReader = new BufferedReader(inFile);
					ArrayList<String> temp = new ArrayList<String>();
					String line;
					try {
						while((line = buffReader.readLine()) != null){
							temp.add(line);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					for(int j = 0; j < 36; j++) {
						inv.add(Inventory.itemMap.get(temp.get(j)), Integer.valueOf(temp.get(j+1)));
						j++;
					}

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					inFile = new FileReader("InventoryFiles/Settings");
					BufferedReader buffReader = new BufferedReader(inFile);
					ArrayList<String> temp = new ArrayList<String>();
					String line;
					try {
						while((line = buffReader.readLine()) != null){
							temp.add(line);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					InventoryMenuState.textColor = new Color(Integer.parseInt(temp.get(0)));
					InventoryMenuState.backgroundColor = new Color(Integer.parseInt(temp.get(1)));
				}
				catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				stateMap.put("field", new FieldState(player));
				if (stateStack.getCount() <= 0)
					stateStack.push("field"); //exits this state, goes to field state.
				else
					stateStack.push("field" + stateStack.getCount()); //exits this state, goes to field state.


			}else if((_cursor/50)== 2){
				System.out.println("Settings Pressed");
				_currentMenu = "Settings";
			}else if ((_cursor/50)== 3){
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
