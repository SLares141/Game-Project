import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

//This class will create a Jframe instance visible by all other state classes. 
//This design pattern, known as the Singleton, will allow the JFrame to have different JPanel instances loaded.

public class WindowFrame extends JFrame {

	//private static Dimension _screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static double _windowWidth = 1040;//1024 These are the dimensions we want?.
    private static double _windowHeight = 614;//576.
    private static JFrame frame = new JFrame("RPG");
	
   private static WindowFrame windowFrame = new WindowFrame();

   
   
   /* A private Constructor prevents any other
    * class from instantiating.
    * with the create window method within the game constructor,
    * we will be able to simplify the setup in the main method.
    * Other methods protected by singleton-ness 
    */
   private WindowFrame() { 
	   frame.setSize((int) _windowWidth,(int) _windowHeight);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	//	frame.setSize(960,540);   This is commented out since dimensions dont match 
		frame.setVisible(true);
   }

   
   
   /* Static 'instance' method */
   public static WindowFrame getInstance( ) {
      return windowFrame;
   }

   
  
   protected static void addState(State state){
	   frame.getContentPane().add((JPanel)state);
	   frame.setVisible(true);
	
   }
   
   protected static void removeState(State state){
	   frame.getContentPane().remove((JPanel) state);
	
	   
   }
   
   protected static void quit(){
	   frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	   
   }
   
   
   
   
   
   
   /**
    * TODO
    * fullscreen option still in development
   protected static void fullScreen() {
	    frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setUndecorated(true);
	   
   }
   */
   
   /**
    * TODO
    * window option still in development
   protected static void revertToWindow() {
	    frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setUndecorated(true);
	   
   }
   */
   
}