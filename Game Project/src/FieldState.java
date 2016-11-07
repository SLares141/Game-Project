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
import java.util.HashMap;
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
	

    private BufferedImage _sprite;

    private Player player;
    private Coordinate _sp;
    private Coordinate _oldsp;
    private boolean loadall = true;
    TileSet ts = new TileSet();
    
    //causes significant loadtime
    Map<Coordinate, Tile> map0 = makeMap0();
   // Map<Coordinate, Tile> map1 = makeMap1();
    
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
		this.player = p;
		_sp = p.getLocation();
		_oldsp = new Coordinate(_sp.x, _sp.y);
		_sprite = p.getSprite();
		/*
		try {
	           _sprite = ImageIO.read(new File("images/strawberry.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		this.addNotify();
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
			for(Map.Entry<Coordinate, Tile> entry: map0.entrySet()){
				Coordinate c = entry.getKey();
				Tile t = entry.getValue();
				g.drawImage(t.im, c.x, c.y, null);
			}
		}else{
			//prints the tile that the sprite moved from
			System.out.println("covering old char sprite");
			g.drawImage(map0.get(_oldsp).im, _oldsp.x, _oldsp.y, null);
		}
		g.drawImage(_sprite, _sp.x, _sp.y, 32, 32, null); 
		
	}
    
	

	@Override
	public void onEnter() {
		loadall = true;
		repaint();
		//this.requestFocusInWindow(); may be called later, DO NOT DELETE
		this.addNotify(); //may be called later, DO NOT DELETE

	}

	@Override
	public void onExit() {
	/**
	 * Can be used to end music, or pause the game or something.
	 * 
	 */
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
		else //indicates the player is moving, not escaping to menu
		{
			_oldsp.setX(_sp.x);
			_oldsp.setY(_sp.y);
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				System.out.println("Right key pressed");
				_sp.setX(_sp.x += 32);
				
		        
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				System.out.println("Left key pressed");
			    _sp.setX(_sp.x -= 32);
			    
			}
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				System.out.println("Up key pressed");
				_sp.setY(_sp.y -= 32);
			    
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				System.out.println("Down key pressed");
				_sp.setY(_sp.y += 32);
			    
			}
			
			//Checks boundaries of the character sprite
			if(_sp.x > 992){
				_sp.x = 992;
			}else if(_sp.y > 544){
				_sp.y = 544;
			}else if(_sp.x < 0){
				_sp.x = 0;
			}else if(_sp.y < 0){
				_sp.y = 0;
			}
			player.setLocation(_sp);
			repaint();
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
	
	//CONSTRUCTING MAP0
	public Map<Coordinate, Tile> makeMap0(){
		int[][] ia = new int[][]{//			10 				  16		  20	
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
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
				map.put(new Coordinate(x*32, y*32), new Tile(x*32, y*32, ia[y][x]));
			}
		}
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
				map.put(new Coordinate(x*32, y*32), new Tile(x*32, y*32, ia[y][x]));
			}
		}
		return map;
	}

}

	

