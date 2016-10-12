import java.awt.Graphics;

/*
 * This gives us a uniform way of defining the different states.
 * Each one will need this functionality in different ways.
 */
public interface State{
	  public void update();
	  public void render();
	  public void onEnter();
	  public void onExit();
}
