import java.util.HashMap;
import java.util.Stack;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class Game extends JPanel {
	private Dimension _screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private double _windowWidth = _screenSize.getWidth();
    private double _windowHeight = _screenSize.getHeight();
    private static WindowFrame _frame;
    private Graphics g;
   

	
	public Game() {
		createWindow();
		g = _frame.getGraphics();
	}
	//with the create window method within the game constructor,
	//we will be able to simplify the setup in the main method.
	private void createWindow() {
		_frame = WindowFrame.getInstance();
	}
	
	
	public static void main(String[] args) throws IOException {
		
       
	
		Game g = new Game();
      
       
		Player player = new Player();
		Inventory inv = new Inventory();
        //put all states into statemap
		StateMapSingleton stateMap = StateMapSingleton.getInstance();
		
		stateMap.put("menu", new MainMenuState());
		stateMap.put("field", new FieldState());
		stateMap.put("battle", new BattleState());
		stateMap.put("inventory", new InventoryMenuState(player, inv));
		

		
		StateStackSingleton stateStack = StateStackSingleton.getInstance();
		//the main menu is set as the initial state
		stateStack.push("menu");
	
		
		
		
		//At the end of the main method, there should be the game loop.
		//The update and render methods are called on the stateStack instance, 
		//so only the top element (the current state) should be updated and rendered.
		/*
		 while(true) {
			stateStack.update();
			stateStack.render();
		}
		*/
	}
}