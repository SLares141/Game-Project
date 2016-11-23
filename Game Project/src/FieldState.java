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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    private Player player;
    private BufferedImage _playersprite, carrot;
    private Coordinate _sp;
    private Coordinate _oldsp;
    private boolean loadall = true;
    
    //causes significant loadtime
    List<Map<Coordinate,Tile>> mapList = null;
    Map<Coordinate, Tile> map0 = makeMap0();
    Map<Coordinate, Tile> map1 = makeMap1();
    Map<Coordinate, Tile> map2 = makeMap2();
    private int currentMapNum;
    
	int _windowWidth = 1024;
	int _windowHeight = 576;

	WindowFrame _frame = WindowFrame.getInstance(); // should this be static??
	StateMapSingleton stateMap = StateMapSingleton.getInstance();
	StateStackSingleton stateStack = StateStackSingleton.getInstance();
	
	
	
	
	// this is the name of the state that the field state will transition to.
	//onExit function will look at this, and push the string to the state stack
	//this will allow the proper state to be put on the stack, and to transition accordingly.
	// in the case of field state, this will be either main menu, inventory, or battle states.
	String _stateDestination; 	
		
	
	public FieldState(Player p)
	{
		System.out.println("in constructor");
		
		Graphics g = _frame.getGraphics();
		addKeyListener(this);
		this.setFocusable(true);
		
		if (p.isDead() || p.beatBoss()) {
			p.setLocation(new Coordinate(512, 288));
			p.setMap(2);
		}
		
		this.player = p;
		_sp = p.getLocation();
		_oldsp = new Coordinate(_sp.x, _sp.y);
		_playersprite = p.getSprite();
		mapList = makeMapList();
		currentMapNum = p.getMap();
		this.addNotify();
		try {
			carrot = ImageIO.read(new File("images/carrot.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addNotify() {
        super.addNotify();
        requestFocus();
    }
    
    

	@Override
	public void update() {
        
		
		
	}

	public void render() {
		//repaint();
		
	}
	public void paintComponent(Graphics g) {
		//grid of tile values
		
		
		int x = 0, y;
		//loads map to be the map where a given coordinate is the key, and the value is a tile given by above grid
		
		//checks if it needs to load the whole grid
		if (loadall){
			
			//prints the entire grid
			System.out.println("PRINTING ENTIRE GRID");
			for(Map.Entry<Coordinate, Tile> entry: mapList.get(currentMapNum).entrySet()){
				Coordinate c = entry.getKey();
				Tile t = entry.getValue();
				g.drawImage(t.im, c.x, c.y, null);
			}
		}else{
			//prints the tile that the sprite moved from
			System.out.println("covering old char sprite");
			g.drawImage(mapList.get(currentMapNum).get(_oldsp).im, _oldsp.x, _oldsp.y, null);
		}
		g.drawImage(_playersprite, _sp.x, _sp.y, 32, 32, null); 
		drawEnemies(g);
	}
    
	

	private void drawEnemies(Graphics g) {
		if(currentMapNum == 0){
			EnemyCharacter e = new EnemyCharacter();
			e.setSprite(carrot);
			
			Coordinate c = new Coordinate(32*5, 5*32);
			e.setLocation(c);
			
			g.drawImage(e.getSprite(), e.getLocation().x, e.getLocation().y, null);
		}
	}

	@Override
	public void onEnter() {
		if (player.isDead()) {
			/*
			 * We can increment a counter just so that we can create a new unique FieldState
			 * that is independent from the one in which the player just got a GameOver in.
			 * This will reset the stats of both the player and enemies
			 */
			stateStack.incrementCount();
			stateMap.put("field" + stateStack.getCount(), new FieldState(player));
			stateStack.pop();
		} 
		/*
		 * 	when FieldState is entered, the whole field needs to be painted.
		 */
		loadall = true;
		repaint();
		//this.requestFocusInWindow(); may be called later, DO NOT DELETE
		this.addNotify(); //may be called later, DO NOT DELETE

	}

	@Override
	/*
	 * save the players new data onExit of the fieldState
	 */
	public void onExit() {
		if (player.isDead()) {
			player.resetPlayer();
			player = new Player();
		}
		else
			player.savePlayer();
	}

	@Override
	public void keyPressed(KeyEvent e) {

		loadall = false;
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE ) { //press escape to go to menu. WILL BE CHANGED!
	            System.out.println("Back to main menu!");
	            loadall = true;
	            _stateDestination = "menu";
	            stateStack.pop(); //exit out and return to menu
		}     
		else if (e.getKeyCode() == KeyEvent.VK_M) {
			//Pressing M goes to the inventory
			System.out.println("To the inventory");
			_stateDestination = "inventory";
			stateStack.push("inventory");
		}
		else //indicates the player is moving, not escaping to menu
		{
			//Saving the old coordinates of the sprite
			_oldsp.setX(_sp.x);
			_oldsp.setY(_sp.y);
			//increment the coordinate of the character when certain 
			//buttons are pressed.
			if (e.getKeyCode() == KeyEvent.VK_RIGHT 
					|| e.getKeyCode() == KeyEvent.VK_D) {
				System.out.println("Right key pressed");
				//_sp.setX(_sp.x += 32);
				movePlayer(32, 0);
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT
					|| e.getKeyCode() == KeyEvent.VK_A) {
				System.out.println("Left key pressed");
			    //_sp.setX(_sp.x -= 32);
			    movePlayer(-32,0);
			}
			if (e.getKeyCode() == KeyEvent.VK_UP
					|| e.getKeyCode() == KeyEvent.VK_W) {
				System.out.println("Up key pressed");
				//_sp.setY(_sp.y -= 32);
				movePlayer(0,-32);
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN
					|| e.getKeyCode() == KeyEvent.VK_S) {
				System.out.println("Down key pressed");
				//_sp.setY(_sp.y += 32);
				movePlayer(0,32);
			}
			//movePlayer takes care of checking bounds/setting
			//new player coordinate
			repaint();
		}
	}

	public void movePlayer(int a, int b){
		System.out.println("in moveplayer");
		Coordinate check = new Coordinate(_sp.x + a, _sp.y + b);
		Tile oldTile = mapList.get(currentMapNum).get(_sp);
		Tile nextTile = mapList.get(currentMapNum).get(check);
		
		if((nextTile == null) && (oldTile.isPortal())){
			shiftMap(oldTile.getDestination(), check);
		}else if(nextTile == null){
			//do nothing, on boundary
		}else if (!nextTile.canMoveTo()){
			//DO NOTHING 
		}else if (nextTile.hasEnemy()){
			EnemyCharacter e = nextTile.getEnemy();
			BattleState bs = new BattleState();
			bs.setEnemy(e);
			bs.setPlayer(player);
			stateStack.push(bs);
		}else if (nextTile.isBorder()){
			_sp = check;
			player.setLocation(_sp);
		}else if (nextTile.isPortal()){
			shiftMap(nextTile.getDestination(),check);
		}else {
			_sp = check;
			player.setLocation(_sp);
		}
		//Checks the boundaries to make sure it doesnt go off screen
		//in the event it goes off screen, go to shiftMap.
		/*
		if(_sp.x > 992){
			_sp.x = 992;
			//shiftMap();
		}else if(_sp.y > 544){
			_sp.y = 544;
			//shiftMap();
		}else if(_sp.x < 0){
			_sp.x = 0;
			//shiftMap();
		}else if(_sp.y < 0){
			_sp.y = 0;
			//shiftMap();
		}else{
			//if not on the edge of the screen, 
			player.setLocation(_sp);
		}
		*/
	}
	public void shiftMap(int n, Coordinate check){
		System.out.println("in shiftmap");
		//gets the tile that the sprite is trying to move from
		//checks if that tile is a portal, if it is, then it 
		//goes to its destination.
		Tile oldTile = mapList.get(currentMapNum).get(_sp);
		Tile nextTile = mapList.get(currentMapNum).get(check);
		
		if((oldTile.isPortal())&&(oldTile.isBorder())){
			System.out.println("here");
			currentMapNum = oldTile.getDestination();
			loadall = true;
			player.setMap(currentMapNum);
			if(_sp.x == 992){ _sp.x = 0;}
			else if(_sp.x == 0){ _sp.x = 992;}
			else if(_sp.y == 0){ _sp.y = 544;}
			else if(_sp.y == 544){_sp.y = 0;}
			player.setLocation(_sp);
		}else if((nextTile.isPortal()) && (nextTile.isBuilding())){
			loadall = true;
			currentMapNum = nextTile.getDestination();
			_sp.set(nextTile.getReturnCord());
			player.setLocation(_sp);
			player.setMap(currentMapNum);
		}
		
		
		/*
		if(t.isPortal()){
			System.out.println("portal");
			currentMapNum = t.getDestination();
			loadall = true;
			player.setMap(currentMapNum);
			if(_oldsp.x == 992){ _sp.x = 0;}
			else if(_oldsp.x == 0){ _sp.x = 992;}
			else if(_oldsp.y == 0){ _sp.y = 544;}
			else if(_oldsp.y == 544){_sp.y = 0;}
			
			player.setLocation(_sp);
		}else{
			player.setLocation(_sp);
		}
		*/
	}
		
	

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	//CONSTRUCTING MAP0
	public Map<Coordinate, Tile> makeMap0(){
		int[][] ia = new int[][]{//			10 				  16		  20 21 22 23 24 25 26 27 	
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 5, 1, 1, 1, 1, 1},
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
		};
		//System.out.println("IN Makemap0"); debug message
		int x, y;
		Map<Coordinate, Tile> map = new HashMap<Coordinate, Tile>();
		
		for(x = 0; x < 32; x++){
			for(y = 0; y < 18; y++){
				Coordinate t = new Coordinate(x*32, y*32);
				
				if(x==0){
					map.put(t, new Tile(ia[y][x], true));
					map.get(t).setisBorder(true);
					map.get(t).setDestination(0);
				}else if(x==31){
					map.put(t, new Tile(ia[y][x], true));
					map.get(t).setisBorder(true);
					map.get(t).setDestination(0);
				}else if(y==0){
					map.put(t, new Tile(ia[y][x], true));
					map.get(t).setisBorder(true);
					map.get(t).setDestination(1);
				}else if(y==17){
					map.put(t, new Tile(ia[y][x], true));
					map.get(t).setisBorder(true);
					map.get(t).setDestination(0);
				}else{
					map.put(t, new Tile(ia[y][x], false));
				}
			}
		}
		Coordinate t = new Coordinate(26*32, 3*32);
		map.get(t).setDestination(2);
		map.get(t).setReturnCord(new Coordinate(512,512));
		t.set(5*32, 5*32);
		EnemyCharacter em = new EnemyCharacter();
		map.get(t).setEnemy(em);
		
		return map;
	}
	public Map<Coordinate, Tile> makeMap1(){
		int[][] ia = new int[][]{//			10 				  16		  20	
				{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{ 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
				{ 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
				{ 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
				{ 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
				{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
				{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
		};
		//System.out.println("IN Makemap0"); debug message
		int x, y;
		Map<Coordinate, Tile> map = new HashMap<Coordinate, Tile>();
		
		for(x = 0; x < 32; x++){
			for(y = 0; y < 18; y++){
				Coordinate t = new Coordinate(x*32, y*32);
				
				if(x==0){
					map.put(t, new Tile(ia[y][x], true));
					map.get(t).setisBorder(true);
					map.get(t).setDestination(1);
				}else if(x==31){
					map.put(t, new Tile(ia[y][x], true));
					map.get(t).setisBorder(true);
					map.get(t).setDestination(1);
				}else if(y==0){
					map.put(t, new Tile(ia[y][x], true));
					map.get(t).setisBorder(true);
					map.get(t).setDestination(1);
				}else if(y==17){
					map.put(t, new Tile(ia[y][x], true));
					map.get(t).setisBorder(true);
					map.get(t).setDestination(0);
				}else{
					map.put(t, new Tile(ia[y][x], false));
				}
			}
		}
		return map;
	}
	public Map<Coordinate, Tile> makeMap2(){
		int[][] ia = new int[][]{//			10 				  16		  20	
				{ 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
				{ 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4},
				{ 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4},
				{ 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4},
				{ 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4},
				{ 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4},
				{ 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4},
				{ 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4},
				{ 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4},
				{ 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4},
				{ 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4},
				{ 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4},
				{ 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4},
				{ 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4},
				{ 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4},
				{ 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4},
				{ 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4},
				{ 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}
		};
		//System.out.println("IN Makemap0"); debug message
		int x, y;
		Map<Coordinate, Tile> map = new HashMap<Coordinate, Tile>();
		
		for(x = 0; x < 32; x++){
			for(y = 0; y < 18; y++){
				Coordinate t = new Coordinate(x*32, y*32);
				
				map.put(t, new Tile(ia[y][x], false));
				if((y==17)&&((x==16)||(x==15))){
					map.get(t).setDestination(0);
					map.get(t).setReturnCord(new Coordinate(26*32, 3*32));
				}
			}
		}
		return map;
	}
	private List<Map<Coordinate, Tile>> makeMapList() {
		ArrayList<Map<Coordinate, Tile>> temp = new ArrayList<Map<Coordinate, Tile>>();
		temp.add(map0);
		temp.add(map1);
		temp.add(map2);
		return temp;
	}
}

