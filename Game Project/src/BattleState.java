import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class BattleState extends JPanel implements State, KeyListener {
	
	private BufferedImage background, _player, _enemy, _cursor;
	private BufferedImage melee, special, magic, defend;
	private BufferedImage notEnoughMP, specialMissed, runFailed, cannotRun;
	private Player _charPlayer;
	private EnemyCharacter _charEnemy;
	private int _cursorX, _cursorY, _cursorIndexX, _cursorIndexY;
	WindowFrame _frame = WindowFrame.getInstance(); // should this be static??
	StateMapSingleton stateMap = StateMapSingleton.getInstance();
	StateStackSingleton stateStack = StateStackSingleton.getInstance();
	private Rectangle topLeftButton			= new Rectangle(460, 400, 200,  40);
	private Rectangle topRightButton		= new Rectangle(710, 400, 200,  40);
	private Rectangle bottomLeftButton		= new Rectangle(460, 450, 200,  40);
	private Rectangle bottomRightButton		= new Rectangle(710, 450, 200,  40);
	private Rectangle playerInfoBox			= new Rectangle(460, 300, 450,  75);
	private Rectangle enemyInfoBox			= new Rectangle( 40,  75, 450,  75);
	//private Rectangle victoryInfoBox		= new Rectangle(150, 550, 100, 250);
	private String _currentScreen;
	private int tempExpVal;
	
	
	
	
	public BattleState()
	{
		resetCursor();
		_currentScreen = new String("PlayerTurn");
		Graphics g = _frame.getGraphics();
		addKeyListener(this);
		
		this.setFocusable(true);
		
		try {
			background = ImageIO.read(new File("images/fightBackground.png"));
			_player = ImageIO.read(new File("images/battleStrawberry.png"));
			_cursor = ImageIO.read(new File("images/arrow.png"));
			melee = ImageIO.read(new File("images/melee.png"));
			special = ImageIO.read(new File("images/special.png"));
			magic = ImageIO.read(new File("images/magic.png"));
			defend = ImageIO.read(new File("images/shield.png"));
			notEnoughMP = ImageIO.read(new File("images/notEnoughMP.png"));
			specialMissed = ImageIO.read(new File("images/specialMissed.png"));
			runFailed = ImageIO.read(new File("images/runFailed.png"));
			cannotRun = ImageIO.read(new File("images/cannotRun.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.addNotify();
	}
	
	// setter methods to give the BattleState a player and an enemy
	public void setPlayer(Player p){ _charPlayer = p; }
	public void setEnemy(EnemyCharacter e){ _charEnemy = e; }
	
	public void resetCursor() {
		_cursorX = 410; // x coord of cursor
		_cursorY = 400; // y coord of cursor
		_cursorIndexX = 0;
		_cursorIndexY = 0;
	}

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

	public void render() { repaint(); } // do we need "render" if it just repaints?
	
	Font font0 = new Font("Comic sans MS", Font.BOLD, 50);
	Font font1 = new Font("Comic sans MS", Font.PLAIN, 25);
	Font font2 = new Font("arial", Font.BOLD, 30);
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		if (_currentScreen.equals("PlayerTurn")){
			g.drawImage(background, 0,0, null);
			g.drawImage(_player, 125, 300, null);
			g.drawImage(_enemy, 700, 75, null);
			g.drawImage(_cursor, _cursorX, _cursorY, null);
			g.setFont(font0);
			g.setColor(Color.BLACK);
			g2d.draw(playerInfoBox);
			g.drawString(_charPlayer.getName(), playerInfoBox.x, playerInfoBox.y - 10);
			g.setColor(Color.WHITE);
			g2d.draw(enemyInfoBox);
			g.drawString(_charEnemy.getName(), enemyInfoBox.x, enemyInfoBox.y - 10);
			
			g.setFont(font1);
			g.setColor(Color.BLACK);
			g.drawString("HP:   " + _charPlayer.getHealth() + " / " + _charPlayer.getTotalHealth()
				  + "     MP:   " + _charPlayer.getMagic()  + " / " + _charPlayer.getTotalMagic(), 
				  playerInfoBox.x + 10, playerInfoBox.y + 45);
			g.setColor(Color.WHITE);
			g.drawString("HP:   " + _charEnemy.getHealth() + " / " + _charEnemy.getTotalHealth(), 
				  enemyInfoBox.x + 10, enemyInfoBox.y + 45);
			
			g2d.setFont(font2);
			g2d.setColor(Color.BLACK);
			g.drawString("FIGHT", topLeftButton.x + 70, topLeftButton.y + 30);
			g2d.draw(topLeftButton);
			g.drawString("DEFEND", topRightButton.x + 70, topRightButton.y + 30);
			g2d.draw(topRightButton);
			g.drawString("ITEM", bottomLeftButton.x + 70, bottomLeftButton.y + 30);
			g2d.draw(bottomLeftButton);
			g.drawString("RUN", bottomRightButton.x + 70, bottomRightButton.y + 30);
			g2d.draw(bottomRightButton);
			
		} else if (_currentScreen.equals("PlayerTurnFight")){	
			g.drawImage(background, 0,0, null);
			g.drawImage(_player, 125, 300, null);
			g.drawImage(_enemy, 700, 75, null);
			g.drawImage(_cursor, _cursorX, _cursorY, null);
			
			g.setFont(font0);
			g.setColor(Color.BLACK);
			g2d.draw(playerInfoBox);
			g.drawString(_charPlayer.getName(), playerInfoBox.x, playerInfoBox.y - 10);
			g.setColor(Color.WHITE);
			g2d.draw(enemyInfoBox);
			g.drawString(_charEnemy.getName(), enemyInfoBox.x, enemyInfoBox.y - 10);
			
			g.setFont(font1);
			g.setColor(Color.BLACK);
			g.drawString("HP:   " + _charPlayer.getHealth() + " / " + _charPlayer.getTotalHealth()
				  + "     MP:   " + _charPlayer.getMagic()  + " / " + _charPlayer.getTotalMagic(), 
				  playerInfoBox.x + 10, playerInfoBox.y + 45);
			g.setColor(Color.WHITE);
			g.drawString("HP:   " + _charEnemy.getHealth() + " / " + _charEnemy.getTotalHealth(), 
				  enemyInfoBox.x + 10, enemyInfoBox.y + 45);
			
			g2d.setFont(font2);
			g2d.setColor(Color.BLACK);
			g.drawString("MELEE", topLeftButton.x + 70, topLeftButton.y + 30);
			g2d.draw(topLeftButton);
			g.drawString("MAGIC", topRightButton.x + 70, topRightButton.y + 30);
			g2d.draw(topRightButton);
			g.drawString("SPECIAL", bottomLeftButton.x + 70, bottomLeftButton.y + 30);
			g2d.draw(bottomLeftButton);
			g.drawString("BACK", bottomRightButton.x + 70, bottomRightButton.y + 30);
			g2d.draw(bottomRightButton);
			
			
		} else if (_currentScreen.equals("PlayerTurnItem")){
			g.drawImage(background, 0,0, null);
			g.drawImage(_player, 125, 300, null);
			g.drawImage(_enemy, 700, 75, null);
			g.drawImage(_cursor, _cursorX, _cursorY, null);
			
			g.setFont(font0);
			g.setColor(Color.BLACK);
			g2d.draw(playerInfoBox);
			g.drawString(_charPlayer.getName(), playerInfoBox.x, playerInfoBox.y - 10);
			g.setColor(Color.WHITE);
			g2d.draw(enemyInfoBox);
			g.drawString(_charEnemy.getName(), enemyInfoBox.x, enemyInfoBox.y - 10);
			
			g.setFont(font1);
			g.setColor(Color.BLACK);
			g.drawString("HP:   " + _charPlayer.getHealth() + " / " + _charPlayer.getTotalHealth()
				  + "     MP:   " + _charPlayer.getMagic()  + " / " + _charPlayer.getTotalMagic(), 
				  playerInfoBox.x + 10, playerInfoBox.y + 45);
			g.setColor(Color.WHITE);
			g.drawString("HP:   " + _charEnemy.getHealth() + " / " + _charEnemy.getTotalHealth(), 
				  enemyInfoBox.x + 10, enemyInfoBox.y + 45);
			
			g2d.setFont(font2);
			g2d.setColor(Color.BLACK);
			g.drawString("POTION", topLeftButton.x + 70, topLeftButton.y + 30);
			g2d.draw(topLeftButton);
			g.drawString("MGK POTION", topRightButton.x + 70, topRightButton.y + 30);
			g2d.draw(topRightButton);
			g.drawString("ATK ITEM", bottomLeftButton.x + 70, bottomLeftButton.y + 30);
			g2d.draw(bottomLeftButton);
			g.drawString("BACK", bottomRightButton.x + 70, bottomRightButton.y + 30);
			g2d.draw(bottomRightButton);
			
		} else if (_currentScreen.equals("EnemyTurn")) {
			g.drawImage(background, 0,0, null);
			g.drawImage(_player, 125, 300, null);
			g.drawImage(_enemy, 700, 75, null);
			
			g.setFont(font0);
			g.setColor(Color.BLACK);
			g2d.draw(playerInfoBox);
			g.drawString(_charPlayer.getName(), playerInfoBox.x, playerInfoBox.y - 10);
			g.setColor(Color.WHITE);
			g2d.draw(enemyInfoBox);
			g.drawString(_charEnemy.getName(), enemyInfoBox.x, enemyInfoBox.y - 10);
			
			g.setFont(font1);
			g.setColor(Color.BLACK);
			g.drawString("HP:   " + _charPlayer.getHealth() + " / " + _charPlayer.getTotalHealth()
				  + "     MP:   " + _charPlayer.getMagic()  + " / " + _charPlayer.getTotalMagic(), 
				  playerInfoBox.x + 10, playerInfoBox.y + 45);
			g.setColor(Color.WHITE);
			g.drawString("HP:   " + _charEnemy.getHealth() + " / " + _charEnemy.getTotalHealth(), 
				  enemyInfoBox.x + 10, enemyInfoBox.y + 45);
			
			String s = _charEnemy.enemyAttack(_charPlayer); // ENEMY TAKES TURN HERE
			switch(s) {
			case "def" :
				this.getGraphics().drawImage(defend, 700, 50, null);
    			try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					
					e1.printStackTrace();
				}
    			defend.flush();
    			break;
			case "mag":
				this.getGraphics().drawImage(magic, 125, 300, null);
    			try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					
					e1.printStackTrace();
				}
    			magic.flush();
    			break;
			case "spe":
				this.getGraphics().drawImage(special, 125, 300, null);
    			try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					
					e1.printStackTrace();
				}
    			special.flush();
    			break;
			case "mis":
				this.getGraphics().drawImage(specialMissed, 20, 160, null);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					
					e1.printStackTrace();
				}
				specialMissed.flush();
				break;
			case "mel": default:
				this.getGraphics().drawImage(melee, 125, 300, null);
    			try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					
					e1.printStackTrace();
				}
    			melee.flush();
    			break;
			}
			
			if (_charPlayer.isDead()){ // if player dies, end fight
				_currentScreen = "GameOver";
				repaint();
			} else { // if the player isn't dead, transition to player turn
				_currentScreen = "PlayerTurn";
				repaint();
			}
			
			
		} else if (_currentScreen.equals("Victory")) {
			g.setFont(font0);
			g.setColor(Color.GREEN);
			g.drawImage(background, 0,0, null);
			g.drawString("VICTORY", 350, 200);
			
		} else if (_currentScreen.equals("VictoryInfo")) {
			g.setFont(font0);
			g.setColor(Color.GREEN);
			g.drawImage(background, 0,0, null);
			g.drawString("VICTORY", 350, 200);
			
			boolean tempBool = false;
			if (_charPlayer.getExp() >= tempExpVal) { // check if a level-up has occurred
				_charPlayer.levelUp();
				_charPlayer.updateExp4NextLvl();
				tempBool = true;
			}
			
			g.setColor(Color.WHITE);
			g.setFont(font2);
			g.drawString("REWARDS", 250, 250);
			g.drawString("Money: +$" + _charEnemy.getMoney() 
				+ "..........TOTAL: $" + _charPlayer.getMoney(), 250, 290);
			g.drawString("EXP:     +  " + _charEnemy.getExp() 
				+ "..........EXP UNTIL NEXT LEVEL:  "
				+ (_charPlayer.getExp4NextLvl() - _charPlayer.getExp()), 250, 330);
			
			// DISPLAY HOW MUCH EXP UNTIL NEXT LEVEL
			
			if (tempBool) { // display that level up occured
				g.setFont(font0);
				g.setColor(Color.GREEN);
				g.drawString("LEVEL UP! Current Level: " + _charPlayer.getLevel(), 250, 400);
				tempBool = false;
			}
			
		
		} else if (_currentScreen.equals("GameOver")){
			g.setFont(font0);
			g.setColor(Color.RED);
			g.drawImage(background, 0,0, null);
			g.drawString("GAME OVER", 350, 300);
		
		} else if (_currentScreen.equals("RunSuccess")) {
			g.setFont(font0);
			g.setColor(Color.WHITE);
			g.drawImage(background, 0,0, null);
			g.drawString("RUN SUCCESSFUL", 300, 300);
		}
       
    }
	

	@Override
	public void onEnter() {
		_enemy = _charEnemy.getBattleSprite();
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
        		if ((_cursorIndexX == 0) && (_cursorIndexY == 0)){ // top left (fight) button selected
        			resetCursor();
        			_currentScreen = "PlayerTurnFight";
        			//repaint();
        		} else if ((_cursorIndexX == 0) && (_cursorIndexY == 1)){ // bottom left (item) button selected
        			resetCursor();
        			_currentScreen = "PlayerTurnItem";
        			//repaint();
        		} else if ((_cursorIndexX == 1) && (_cursorIndexY == 0)){ // top right (defend) button selected
        			// DEFEND
        			_charPlayer.defend();
        			_charEnemy.restoreDef(); // restore enemy's defend status to false
        			
        			this.getGraphics().drawImage(defend, 125, 300, null);
        			this.getGraphics().fillRect(460, 400, 450, 90);
        			try {
						Thread.sleep(500);
					} catch (InterruptedException e1) {
						
						e1.printStackTrace();
					}
        			defend.flush();
        			
        			System.out.println("DEFEND used");
        			resetCursor();
        			_currentScreen = "EnemyTurn";
        		} else if ((_cursorIndexX == 1) && (_cursorIndexY == 1)){ // bottom right (run) button selected
        			if (_charEnemy.isBoss()){
        				this.getGraphics().drawImage(cannotRun, 460, 500, null);
        				this.getGraphics().fillRect(460, 400, 450, 90);
        				try {
    						Thread.sleep(500);
    					} catch (InterruptedException e1) {
    						
    						e1.printStackTrace();
    					}
        				cannotRun.flush();
        				resetCursor();
        				_currentScreen = "EnemyTurn";
        			} else {
        				Random r = new Random();
        				if (r.nextInt(100) >= 40){ // successful run 60% of the time
        					System.out.println("RunSuccess");
        					_currentScreen = "RunSuccess";

        				} else {
        					System.out.println("RunFailed");
        					this.getGraphics().drawImage(runFailed, 460, 500, null);
        					this.getGraphics().fillRect(460, 400, 450, 90);
            				try {
        						Thread.sleep(500);
        					} catch (InterruptedException e1) {
        						
        						e1.printStackTrace();
        					}
            				runFailed.flush();
            				resetCursor();
        					_currentScreen = "EnemyTurn";
        				}
        			}
        		}	
        	} else if (_currentScreen.equals("PlayerTurnFight")){

        		if ((_cursorIndexX == 0) && (_cursorIndexY == 0)){ // top left (melee) button selected
        			// MELEE ATTACK HAPPENS HERE
        			System.out.println("Player used MELEE");
        			_charPlayer.attack(_charEnemy);
        			
        			this.getGraphics().drawImage(melee, 700, 50, null);
        			this.getGraphics().fillRect(460, 400, 450, 90);
        			try {
						Thread.sleep(500);
					} catch (InterruptedException e1) {
						
						e1.printStackTrace();
					}
        			melee.flush();
        			
        			if (_charEnemy.isDead()){ // if enemy dies, end fight
        				_currentScreen = "Victory";
        			} else { // if the enemy isn't dead, transition to enemy turn
        				resetCursor();
        				_currentScreen = "EnemyTurn";
        			}
        		
        		} else if ((_cursorIndexX == 0) && (_cursorIndexY == 1)) { // bottom left (special) button selected
        			// SPECIAL ATTACK HAPPENS HERE
        			int spHit = _charPlayer.specialAttack(_charEnemy);
        			
        			if (spHit >= 0) {
        				this.getGraphics().drawImage(special, 700, 50, null);
        				this.getGraphics().fillRect(460, 400, 450, 90);
            			try {
    						Thread.sleep(500);
    					} catch (InterruptedException e1) {
    						
    						e1.printStackTrace();
    					}
            			special.flush();
        			} else {
        				this.getGraphics().drawImage(specialMissed, 350, 500, null);
        				this.getGraphics().fillRect(460, 400, 450, 90);
        				try {
    						Thread.sleep(500);
    					} catch (InterruptedException e1) {
    						
    						e1.printStackTrace();
    					}
        				specialMissed.flush();
        			}
        			
        			
        			if (_charEnemy.isDead()) { // if enemy dies, end fight
        				_currentScreen = "Victory";
        			} else { // if the enemy isn't dead, transition to enemy turn
        				resetCursor();
    	    			_currentScreen = "EnemyTurn";
    	    		}
        			
        		} else if ((_cursorIndexX == 1) && (_cursorIndexY == 0)) { // top right (magic) button selected
        			// MAGIC ATTACK HAPPENS HERE
        			if (_charPlayer.getMagic() - 2 >= 0) {
        				System.out.println("Magic Attack Success");
        				_charPlayer.magicAttack(_charEnemy);
        				
        				this.getGraphics().drawImage(magic, 700, 50, null);
        				this.getGraphics().fillRect(460, 400, 450, 90);
            			try {
    						Thread.sleep(500);
    					} catch (InterruptedException e1) {
    						
    						e1.printStackTrace();
    					}
            			magic.flush();
        				
        				if (_charEnemy.isDead()) { // if enemy dies, end fight
    	    				_currentScreen = "Victory";
    	    			} else { // if the enemy isn't dead, transition to enemy turn
    	    				resetCursor();
    	    				_currentScreen = "EnemyTurn";
    	    			}
        			} else {
        				System.out.println("NOT ENOUGH MP");
        				this.getGraphics().drawImage(notEnoughMP, 460, 500, null);
        				this.getGraphics().fillRect(460, 400, 450, 90);
        				try {
    						Thread.sleep(500);
    					} catch (InterruptedException e1) {
    						
    						e1.printStackTrace();
    					}
        				notEnoughMP.flush();
        			}
        			
        		} else if ((_cursorIndexX == 1) && (_cursorIndexY == 1)) { // bottom right (back) button selected
        			// this button transitions back to "PlayerTurn"
        			_currentScreen = "PlayerTurn";
        			resetCursor();
        		}
        		
        	} else if (_currentScreen.equals("Victory")) {
        		tempExpVal = _charPlayer.getExp4NextLvl();
        		_charEnemy.awardOnVictory(_charPlayer);  // EXP AND MONEY AWARDED HERE
        	
        		_currentScreen = "VictoryInfo";
        		
        	} else if (_currentScreen.equals("VictoryInfo")) {
        		if (_charEnemy.isBoss()) {
        			// NEED TO INCREMENT BOSS LEVEL//////////////////////////////////////////////////////////
        			
        			_charPlayer.beatBoss();
        			
        		}
        		
        		stateStack.pop();
        		
        	} else if (_currentScreen.equals("GameOver")) {
        		stateStack.pop();
        		
        	} else if (_currentScreen.equals("RunSuccess")){
        		stateStack.pop();
        		
        	}
        	
        }
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
        	resetCursor();
        	backtransition();
        }
        update();
		render();
	}
	
	private void backtransition() {
		if(_currentScreen.equals("PlayerTurnFight"))
			_currentScreen = "PlayerTurn";
		else if(_currentScreen.equals("PlayerTurnItem"))
			_currentScreen = "PlayerTurn";
	}
	

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}