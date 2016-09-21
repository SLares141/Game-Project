import java.util.HashMap;
import java.util.Stack;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
/*
 * I am not sure exactly how we are going to structure the code, 
 * but some things in here will be useful to have in some form.
 */
public class Game extends JFrame {
	private Dimension _screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private double _windowWidth = _screenSize.getWidth();
    private double _windowHeight = _screenSize.getHeight();

	
	public Game() {
		createWindow();
	}
	//with the create window method within the game constructor,
	//we will be able to simplify the setup in the main method.
	private void createWindow() {
		JFrame frame = new JFrame("RPG");
		frame.setSize((int) _windowWidth,(int) _windowHeight);
		frame.setLocationRelativeTo(null);
		
		/**
		 * real fullscreen below, replace setsize method if you want to use this.
		 * Note: X button doesn't exist
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setUndecorated(true);
		*/
		
		
		frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		
        Game g = new Game();
      
       
        //i don't know if this is the right place to set up the HashMap of states
		HashMap<String, State> stateMap = new HashMap<String, State>();
		stateMap.put("menu", new MainMenuState());
		stateMap.put("game", new GameState());
		
		
		StateStack stateStack = new StateStack(stateMap);
		//the main menu is set as the initial state
		stateStack.push("menu");
		
		//the next few lines are tests to make sure the stack is working properly
		System.out.println(stateStack.peek());
		stateStack.push("game");
		System.out.println(stateStack.peek());
		stateStack.pop();
		System.out.println(stateStack.peek());
		
		//At the end of the main method, there should be the game loop.
		//The update and render methods are called on the stateStack instance, 
		//so only the top element (the current state) should be updated and rendered.
		/*
		 while(true) {
			float elapsedTime = System.nanoTime();
			stateStack.update();
			stateStack.render();
		}
		*/
	}
}
