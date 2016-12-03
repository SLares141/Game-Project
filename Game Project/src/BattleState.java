import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
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
import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class BattleState extends JPanel implements State, KeyListener {

	private BufferedImage background, _player, _enemy, _cursor, _smallCursor;
	private BufferedImage melee, special, magic, defend, item;
	private BufferedImage notEnoughMP, specialMissed, runFailed, cannotRun;
	private Player _charPlayer;
	private Inventory _inv;
	private int _invIndex;
	private int _invStart = 0;
	private EnemyCharacter _charEnemy;
	private int _cursorX, _cursorY, _cursorIndexX, _cursorIndexY;
	private int _xOffset = 0; //necessary for item menu
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
			_cursor = ImageIO.read(new File("images/small-arrow.png"));
			_smallCursor = ImageIO.read(new File("images/almost-smallest-arrow.png"));
			melee = ImageIO.read(new File("images/melee.png"));
			special = ImageIO.read(new File("images/special.png"));
			magic = ImageIO.read(new File("images/magic.png"));
			defend = ImageIO.read(new File("images/shield.png"));
			item = ImageIO.read(new File("images/itemUsed.png"));
			notEnoughMP = ImageIO.read(new File("images/notEnoughMP.png"));
			specialMissed = ImageIO.read(new File("images/specialMissed.png"));
			runFailed = ImageIO.read(new File("images/runFailed.png"));
			cannotRun = ImageIO.read(new File("images/cannotRun.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.addNotify();
	}

	// setter methods to give the BattleState a player, an enemy, and the inventory
	public void setPlayer(Player p){ _charPlayer = p; }
	public void setEnemy(EnemyCharacter e){ _charEnemy = e; }
	public void setInv(Inventory i) { _inv = i; }

	public void resetCursor() {
		_cursorX = 425; // x coord of cursor
		_cursorY = 406; // y coord of cursor
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
	Font font2 = new Font("Comic sans MS", Font.BOLD, 30);
	Font font4 = new Font("Comic sans MS", Font.PLAIN, 16);

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		if (_currentScreen.equals("PlayerTurn")){
			g.drawImage(background, 0,0, null);
			g.drawImage(_player, 125, 300, null);
			if(_charEnemy.isBoss()) 
				g.drawImage(_enemy, 630, 0, null);
			else
				g.drawImage(_enemy, 730, 75, null);
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
			g.drawString("FIGHT", topLeftButton.x + 100 - g.getFontMetrics().stringWidth("FIGHT")/2, topLeftButton.y + 30);
			g2d.draw(topLeftButton);
			g.drawString("DEFEND", topRightButton.x + 100 - g.getFontMetrics().stringWidth("DEFEND")/2, topRightButton.y + 30);
			g2d.draw(topRightButton);
			g.drawString("ITEM", bottomLeftButton.x + 100 - g.getFontMetrics().stringWidth("ITEM")/2, bottomLeftButton.y + 30);
			g2d.draw(bottomLeftButton);
			g.drawString("RUN", bottomRightButton.x + 100 - g.getFontMetrics().stringWidth("RUN")/2, bottomRightButton.y + 30);
			g2d.draw(bottomRightButton);

		} else if (_currentScreen.equals("PlayerTurnFight")){	
			g.drawImage(background, 0,0, null);
			g.drawImage(_player, 125, 300, null);
			if(_charEnemy.isBoss()) 
				g.drawImage(_enemy, 630, 0, null);
			else
				g.drawImage(_enemy, 730, 75, null);
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
			g.drawString("MELEE", topLeftButton.x + 100 - g.getFontMetrics().stringWidth("MELEE")/2, topLeftButton.y + 30);
			g2d.draw(topLeftButton);
			g.drawString("MAGIC", topRightButton.x + 100 - g.getFontMetrics().stringWidth("MAGIC")/2, topRightButton.y + 30);
			g2d.draw(topRightButton);
			g.drawString("SPECIAL", bottomLeftButton.x + 100 - g.getFontMetrics().stringWidth("SPECIAL")/2, bottomLeftButton.y + 30);
			g2d.draw(bottomLeftButton);
			g.drawString("BACK", bottomRightButton.x + 100 - g.getFontMetrics().stringWidth("BACK")/2, bottomRightButton.y + 30);
			g2d.draw(bottomRightButton);


		} else if (_currentScreen.equals("PlayerTurnItem")){
			g.drawImage(background, 0,0, null);
			g.drawImage(_player, 125, 300, null);
			if(_charEnemy.isBoss()) 
				g.drawImage(_enemy, 630, 0, null);
			else
				g.drawImage(_enemy, 730, 75, null);
			g.drawImage(_smallCursor, _cursorX + 47  + _xOffset, _cursorY + 5, null);

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

			g2d.setFont(font4);
			g2d.setColor(Color.BLACK);
			g.drawRect(topLeftButton.x, topLeftButton.y, 450, 90);

			int x = 500;
			int y = 428;
			System.out.println(_inv.getNumItems());
			for(int j = _invStart; j < _inv.getNumItems() && j < _invStart + 4; j++) {
				if(j%2 == 0) {
					g.drawString(_inv.getItem(j).getName(), x, y);
					g.drawString("x" + _inv.getItemAmount(j), x + 140, y);
					x = 700;
					continue;
				}
				g.drawString(_inv.getItem(j).getName(), x, y);
				g.drawString("x" + _inv.getItemAmount(j), x + 140, y);
				x = 500;
				y += 50;
			}
		} else if (_currentScreen.equals("EnemyTurn")) {
			
			g.drawImage(background, 0,0, null);
			g.drawImage(_player, 125, 300, null);
			if(_charEnemy.isBoss()) 
				g.drawImage(_enemy, 630, 0, null);
			else
				g.drawImage(_enemy, 730, 75, null);

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
			g.drawString("VICTORY", (_frame.getWidth() - g.getFontMetrics().stringWidth("VICTORY"))/2, 200);

		} else if (_currentScreen.equals("VictoryInfo")) {
			g.setFont(font0);
			g.setColor(Color.GREEN);
			g.drawImage(background, 0,0, null);
			g.drawString("VICTORY", (_frame.getWidth() - g.getFontMetrics().stringWidth("VICTORY"))/2, 200);

			boolean tempBool = false;
			if (_charPlayer.getExp() >= tempExpVal) { // check if a level-up has occurred
				_charPlayer.levelUp();
				_charPlayer.updateExp4NextLvl();
				tempBool = true;
			}

			g.setColor(Color.WHITE);
			g.setFont(font2);
			g.drawString("REWARDS", 150, 280);
			g.drawString("Money: +$" + _charEnemy.getMoney() 
			+ "..........TOTAL: $" + _charPlayer.getMoney(), 150, 320);
			g.drawString("EXP:    + " + _charEnemy.getExp() 
			+ "..........EXP UNTIL NEXT LEVEL: "
			+ (_charPlayer.getExp4NextLvl() - _charPlayer.getExp()), 150, 360);

			// DISPLAY HOW MUCH EXP UNTIL NEXT LEVEL

			if (tempBool) { // display that level up occured
				g.setFont(font0);
				g.setColor(Color.GREEN);
				g.drawString("LEVEL UP! Current Level: " + _charPlayer.getLevel(), (_frame.getWidth() - g.getFontMetrics().stringWidth("LEVEL UP! Current Level: " + _charPlayer.getLevel()))/2, 430);
				tempBool = false;
			}


		} else if (_currentScreen.equals("GameOver")){
			g.setFont(font0);
			g.setColor(Color.RED);
			g.drawImage(background, 0,0, null);
			g.drawString("GAME OVER", (_frame.getWidth() - g.getFontMetrics().stringWidth("GAME OVER"))/2, 300);

		} else if (_currentScreen.equals("RunSuccess")) {
			g.setFont(font0);
			g.setColor(Color.BLUE);
			g.drawImage(background, 0,0, null);
			g.drawString("RUN SUCCESSFUL", (_frame.getWidth() - g.getFontMetrics().stringWidth("RUN SUCCESSFUL"))/2, 300);
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
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			if (_cursorIndexX == 0) { // cursorIndex at 0 indicates left buttons
				_cursorX += 250;
				_cursorIndexX = 1;
				if(_currentScreen.equals("PlayerTurnItem")) {
					_xOffset = -50;
					_invIndex++;
				}
			} else { // if _cursorIndexX == 1 (indicating right buttons)
				_cursorX -= 250;
				_cursorIndexX = 0;
				if(_currentScreen.equals("PlayerTurnItem")) {
					_xOffset = 0;
					_invIndex--;
				}
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			if (_cursorIndexX == 1) { // right buttons
				_cursorX -= 250;
				_cursorIndexX = 0;
				if(_currentScreen.equals("PlayerTurnItem")) {
					_xOffset = 0;
					_invIndex--;
				}
			} else { // if _cursorIndexX == 0 (left buttons)
				_cursorX += 250;
				_cursorIndexX = 1;
				if(_currentScreen.equals("PlayerTurnItem")) {
					_xOffset = -50;
					_invIndex++;
				}
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			if(_currentScreen.equals("PlayerTurnItem")) {
				if (_cursorIndexY == 1) { 
					_cursorY -= 50;
					_cursorIndexY = 0;
					_invIndex -= 2;
				} else { 
					if((_invIndex > 0 && _cursorIndexX == 0) || (_invIndex > 1 && _cursorIndexX == 1)) {
						_invIndex -= 2;
						_invStart -= 2;
					}
				}
			}
			else {
				if (_cursorIndexY == 1) { // cursorIndexY at 0 indicates top buttons
					_cursorY -= 50;
					_cursorIndexY = 0;
				} else { // if _cursorIndexY == 1 (indicating bottom buttons)
					_cursorY += 50;
					_cursorIndexY = 1;
				}
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			if(_currentScreen.equals("PlayerTurnItem")) {
				if (_cursorIndexY == 0) {
					_cursorY += 50;
					_cursorIndexY = 1;
					_invIndex += 2;

				} else { 
					if((_invIndex < 16 && _cursorIndexX == 0) || (_invIndex < 17 && _cursorIndexX == 1)) {
						_invIndex += 2;
						_invStart += 2;
					}
				}
			}
			else {
				if (_cursorIndexY == 0) { // cursorIndexY at 1 indicates bottom buttons
					_cursorY += 50;
					_cursorIndexY = 1;
				} else { // if _cursorIndexY == 0 (indicating top buttons)
					_cursorY -= 50;
					_cursorIndexY = 0;
				}
			}
		}

		if	(e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE){
			if (_currentScreen.equals("PlayerTurn")){
				if ((_cursorIndexX == 0) && (_cursorIndexY == 0)){ // top left (fight) button selected
					resetCursor();
					_currentScreen = "PlayerTurnFight";
					//repaint();
				} else if ((_cursorIndexX == 0) && (_cursorIndexY == 1)){ // bottom left (item) button selected
					resetCursor();
					_invIndex = 0;
					_invStart = 0;
					_currentScreen = "PlayerTurnItem";
					//repaint();
				} else if ((_cursorIndexX == 1) && (_cursorIndexY == 0)){ // top right (defend) button selected
					// DEFEND
					_charPlayer.defend();
					_charEnemy.restoreDef(); // restore enemy's defend status to false

					this.getGraphics().drawImage(defend, 125, 300, null);
					//this.getGraphics().fillRect(460, 400, 450, 90);
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
						//this.getGraphics().fillRect(460, 400, 450, 90);
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
							this.getGraphics().drawImage(runFailed, 520, 500, null);
							//this.getGraphics().fillRect(460, 400, 450, 90);
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
			} 
			else if (_currentScreen.equals("PlayerTurnItem")) {
				if(_inv.getItem(_invIndex) != null) {
					_inv.use(_invIndex, _charPlayer);
					this.getGraphics().drawImage(item, 430, 480, null);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					item.flush();
					_currentScreen = "EnemyTurn";
				}
			}
			else if (_currentScreen.equals("PlayerTurnFight")) {

				if ((_cursorIndexX == 0) && (_cursorIndexY == 0)){ // top left (melee) button selected
					// MELEE ATTACK HAPPENS HERE
					System.out.println("Player used MELEE");
					_charPlayer.attack(_charEnemy);

					this.getGraphics().drawImage(melee, 700, 50, null);
					//this.getGraphics().fillRect(460, 400, 450, 90);
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
						//this.getGraphics().fillRect(460, 400, 450, 90);
						try {
							Thread.sleep(500);
						} catch (InterruptedException e1) {

							e1.printStackTrace();
						}
						special.flush();
					} else {
						this.getGraphics().drawImage(specialMissed, 350, 500, null);
						//this.getGraphics().fillRect(460, 400, 450, 90);
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
						//this.getGraphics().fillRect(460, 400, 450, 90);
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
						//this.getGraphics().fillRect(460, 400, 450, 90);
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
		if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_CAPS_LOCK){
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