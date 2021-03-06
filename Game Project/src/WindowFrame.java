import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

//This class will create a Jframe instance visible by all other state classes. 
//This design pattern, known as the Singleton, will allow the JFrame to have different JPanel instances loaded.

public class WindowFrame extends JFrame {

    private static double _windowWidth = 1040;
    private static double _windowHeight = 614;

    private static JFrame frame = new JFrame("RPG");
	
   private static WindowFrame windowFrame = new WindowFrame();

   
   
   /* A private Constructor prevents any other
    * class from instantiating.
    * with the create window method within the game constructor,
    * we will be able to simplify the setup in the main method.
    * Other methods protected by singleton-ness 
    */
   private WindowFrame() { 
	 
		frame.setSize((int)_windowWidth,(int)_windowHeight);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
   
   public int getWidth() {
	   return (int)_windowWidth;
   }
  
   public int getHeight() {
	   return (int)_windowHeight;
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