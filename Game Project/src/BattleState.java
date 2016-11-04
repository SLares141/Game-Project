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
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class BattleState extends JPanel implements State, KeyListener {
	
	private BufferedImage background, _player, _enemy, _cursor;
	private PlayerCharacter _charPlayer;
	private EnemyCharacter _charEnemy;
	private int _windowWidth = 960;
	private int _windowHeight = 540;
	private int _cursorX, _cursorY, _cursorIndexX, _cursorIndexY;
	WindowFrame _frame = WindowFrame.getInstance(); // should this be static??
	private Rectangle topLeftButton			= new Rectangle(_windowWidth - 500, 400, 200, 40);
	private Rectangle topRightButton		= new Rectangle(_windowWidth - 250, 400, 200, 40);
	private Rectangle bottomLeftButton		= new Rectangle(_windowWidth - 500, 450, 200, 40);
	private Rectangle bottomRightButton		= new Rectangle(_windowWidth - 250, 450, 200, 40);
	//private EnemyCharacter[] enemies; // array of enemies that can be loaded to select which enemy is being fought
	
	String _currentScreen;
	
		
	
	public BattleState()
	{
		_cursorX = _windowWidth - 550; // x coord of cursor
		_cursorY = 400; // y coord of cursor
		_cursorIndexX = 0;
		_cursorIndexY = 0;
		_currentScreen = new String("PlayerTurn");
		Graphics g = _frame.getGraphics();
		addKeyListener(this);
		
		this.setFocusable(true);

		try {
			background = ImageIO.read(new File("images/menuback.png"));
			_player = ImageIO.read(new File("images/Mario thumbs up.png"));
			_enemy = ImageIO.read(new File("images/strawberry.png"));
			_cursor = ImageIO.read(new File("images/arrow.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.addNotify();
	}
	
	public void setPlayer(PlayerCharacter p){ _charPlayer = p; }
	public void setEnemy(EnemyCharacter e){ _charEnemy = e; }


    public void addNotify() {
        super.addNotify();
        requestFocus();
    }
    
    

	@Override
	public void update() {
        
		if (_currentScreen.equals("PlayerTurn")){
			
			
		}
		if (_currentScreen.equals("PlayerTurnFight")){
			
			
		}
		if (_currentScreen.equals("PlayerTurnItem")){
			
			
		}
		if (_currentScreen.equals("EnemyTurn")){
			
			
		}
		if (_currentScreen.equals("Victory")){
			
			
		}
		if (_currentScreen.equals("GameOver")){
			
			
		}
		
	}

	public void render() { repaint(); }
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		if (_currentScreen.equals("PlayerTurn")){

			g.drawImage(background, 0,0, null);
			g.drawImage(_player, 200, 300, null);
			g.drawImage(_enemy, 700, 100, null);
			g.drawImage(_cursor, _cursorX, _cursorY, null);
			Font fnt0 = new Font("Comic sans MS", Font.BOLD, 50);
			g.setFont(fnt0);
			g.setColor(Color.BLACK);
			
			
			Font font1 = new Font("arial", Font.BOLD, 30);
			g2d.setFont(font1);
			g2d.setColor(Color.BLACK);
			g.drawString("FIGHT", topLeftButton.x + 70, topLeftButton.y + 30);
			g2d.draw(topLeftButton);
			g.drawString("DEFEND", topRightButton.x + 70, topRightButton.y + 30);
			g2d.draw(topRightButton);
			g.drawString("ITEM", bottomLeftButton.x + 70, bottomLeftButton.y + 30);
			g2d.draw(bottomLeftButton);
			g.drawString("RUN", bottomRightButton.x + 70, bottomRightButton.y + 30);
			g2d.draw(bottomRightButton);
			
		}else if (_currentScreen.equals("PlayerTurnFight")){	
			
			g.drawImage(background, 0,0, null);
			g.drawImage(_player, 200, 300, null);
			g.drawImage(_enemy, 700, 100, null);
			g.drawImage(_cursor, _cursorX, _cursorY, null);
			Font font1 = new Font("arial", Font.BOLD, 30);
			g2d.setFont(font1);
			g2d.setColor(Color.BLACK);
			g.drawString("MELEE", topLeftButton.x + 70, topLeftButton.y + 30);
			g2d.draw(topLeftButton);
			g.drawString("MAGIC", topRightButton.x + 70, topRightButton.y + 30);
			g2d.draw(topRightButton);
			g.drawString("SPECIAL", bottomLeftButton.x + 70, bottomLeftButton.y + 30);
			g2d.draw(bottomLeftButton);
			g.drawString("BACK", bottomRightButton.x + 70, bottomRightButton.y + 30);
			g2d.draw(bottomRightButton);
			
			
			
			
		}else if (_currentScreen.equals("PlayerTurnItem")){
			
			
			
			
			
		}else if (_currentScreen.equals("EnemyTurn")){
			
			g.drawImage(background, 0,0, null);
			g.drawImage(_player, 200, 300, null);
			g.drawImage(_enemy, 700, 100, null);
			Font font1 = new Font("arial", Font.BOLD, 30);
			g2d.setFont(font1);
			g2d.setColor(Color.BLACK);
			
			
			
		}else if (_currentScreen.equals("Victory")){
			
			
			
		}else if (_currentScreen.equals("GameOver")){
			
			
			
		}
       
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
           if (_cursorIndexX == 0) { // cursorIndex at 0 indicates left buttons
        	   _cursorX += 250;
        	   _cursorIndexX = 1;
           } else { // if _cursorIndexX == 1 (indicating right buttons
        	   _cursorX -= 250;
        	   _cursorIndexX = 0;
           }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
        	if (_cursorIndexX == 1) { // right buttons
         	   _cursorX -= 250;
         	   _cursorIndexX = 0;
            } else { // if _cursorIndexX == 0 (left buttons)
         	   _cursorX += 250;
         	   _cursorIndexX = 1;
            }
           
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
        	if (_cursorIndexY == 1) { // cursorIndexY at 0 indicates top buttons
         	   _cursorY -= 50;
         	   _cursorIndexY = 0;
            } else { // if _cursorIndexY == 1 (indicating bottom buttons)
         	   _cursorY += 50;
         	   _cursorIndexY = 1;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
        	if (_cursorIndexY == 0) { // cursorIndexY at 1 indicates bottom buttons
          	   _cursorY += 50;
          	   _cursorIndexY = 1;
             } else { // if _cursorIndexY == 0 (indicating top buttons)
          	   _cursorY -= 50;
          	   _cursorIndexY = 0;
             }
        }
        if	(e.getKeyCode() == KeyEvent.VK_ENTER){
        	if (_currentScreen.equals("PlayerTurn")){
        		
        		if ((_cursorIndexX == 0) && (_cursorIndexY == 0)){ // top left button selected
        			System.out.println("topLeftButton");
        			_currentScreen = "PlayerTurnFight";
        		
        		} else if ((_cursorIndexX == 0) && (_cursorIndexY == 1)){ // bottom left button selected
        			System.out.println("bottomLeftButton");
        			_currentScreen = "PlayerTurnItem";
        			
        		} else if ((_cursorIndexX == 1) && (_cursorIndexY == 0)){ // top right button selected
        			System.out.println("topRightButton");
        			
        		} else if ((_cursorIndexX == 1) && (_cursorIndexY == 1)){ // bottom right button selected
        			System.out.println("bottomRightButton");
        			if (_charEnemy.isBoss()){
        				// DISPLAY "YOU COULD NOT RUN"
        				_currentScreen = "EnemyTurn";
        			} else {
        				Random r = new Random();
        				if (r.nextInt(10) >= 4){
        					System.out.println("RunSuccess");
        					// RUN SUCCESS, TRANSITION TO EXIT
        				} else {
        					// "RUN FAILED"
        					System.out.println("RunFailed");
        					_currentScreen = "EnemyTurn";
        				}
        			
        			}
        		}	
        	} else if (_currentScreen.equals("PlayerTurnFight")){

        		if ((_cursorIndexX == 0) && (_cursorIndexY == 0)){ // top left button selected
        			System.out.println("topLeftButton");
        			// MELEE ATTACK HAPPENS HERE
        			_charPlayer.attack(_charEnemy);
        			if (_charEnemy.isDead()){
        				// if enemy dies, end fight
        				_currentScreen = "GameOver";
        			} else {
        				// if the enemy isnt dead, transition to enemy turn
        			}
        			// NEED TO VISUALLY SHOW ATTACK
        		
        		} else if ((_cursorIndexX == 0) && (_cursorIndexY == 1)){ // bottom left button selected
        			System.out.println("bottomLeftButton");
        			// SPECIAL ATTACK HAPPENS HERE
        			
        		} else if ((_cursorIndexX == 1) && (_cursorIndexY == 0)){ // top right button selected
        			System.out.println("topRightButton");
        			// MAGIC ATTACK HAPPENS HERE
        			
        		} else if ((_cursorIndexX == 1) && (_cursorIndexY == 1)){ // bottom right button selected
        			System.out.println("bottomRightButton");
        			// this button transitions back to "PlayerTurn"
        			_currentScreen = "PlayerTurn";
        			
        		}	
        	}
        	
        	
        	//transition();
        }
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
        	//backtransition();
        }
        update();
		render();
	}
	/*
	private void backtransition() {
		if(_currentScreen.equals("Main")){
			_currentMenu = "Start";
		}else if(_currentMenu.equals("Settings")){
			_currentMenu = "Main";
		}
		
	}
	*/

	private void transition() {
		if(_currentScreen.equals("PlayerTurn")){
			
			
		}else if(_currentScreen.equals("EnemyTurn")){
			
			
			
			
		}else if(_currentScreen.equals("Victory")){
			
			
			
		}else if(_currentScreen.equals("GameOver")){
			
			
			
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