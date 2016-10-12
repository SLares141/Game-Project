import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
/*
 * I am not sure exactly how we are going to structure the code, 
 * but some things in here will be useful to have in some form.
 */
public class StateStackSingletonTest extends JFrame {
	private Dimension _screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private double _windowWidth = _screenSize.getWidth();
	private double _windowHeight = _screenSize.getHeight();
	public static JFrame frame;


	BufferedImage background = null;

	public StateStackSingletonTest() {
		createWindow();
	}

	private void createWindow() {
		frame = new JFrame("RPG");
		frame.setSize(new Dimension(960,540));
		frame.setLocationRelativeTo(null);

		/**
		 * real fullscreen below, replace setsize method if you want to use this.
		 * Note: X button doesn't exist
		 */
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		//frame.setUndecorated(true);


		frame.setVisible(true);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) throws Exception {

		StateStackSingletonTest test = new StateStackSingletonTest();

		MainMenuState menu = new MainMenuState();
		FieldState field = new FieldState();

		StateMapSingleton stateMap = StateMapSingleton.getInstance();

		stateMap.put("menu", menu);
		stateMap.put("field", field);
		
		System.out.println(stateMap.get("menu"));
		System.out.println(stateMap.get("field"));
		
		StateStackSingleton stateStack = StateStackSingleton.getInstance();
		stateStack.push("menu");
		System.out.println(stateStack.peek());
		
		stateStack.push("field");
		System.out.println(stateStack.peek());
		
		stateStack.pop();
		System.out.println(stateStack.peek());
	}
}
