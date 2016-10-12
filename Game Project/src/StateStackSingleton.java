import java.awt.Graphics;
import java.util.Stack;
import java.util.HashMap;

/* This serves as a way of switching between the game's states. 
 * In using the state machine, we avoid a single, bloated update method.
 * Instead, we will have different methods of updating and rendering the game in each state.
 * This is a singleton implementation
 */

public class StateStackSingleton {

	public static StateMapSingleton stateMap;
	public Stack<State> mStack;

	private static StateStackSingleton StateStackSingleton = new StateStackSingleton();

	/* A private Constructor prevents any other
	 * class from instantiating.
	 * with the create window method within the game constructor,
	 * we will be able to simplify the setup in the main method.
	 * Other methods protected by singleton-ness 
	 */
	private StateStackSingleton() { 
		this.stateMap = StateMapSingleton.getInstance();
		mStack = new Stack<State>();
	}

	/* Static 'instance' method */
	protected static StateStackSingleton getInstance( ) {
		return StateStackSingleton;
	}

	protected void update() {
		State top = mStack.peek();
		top.update();
	}

	protected void render() {
		State top = mStack.peek();
		top.render();
	}

	protected void push(String name) throws Exception {
		State state = stateMap.get(name);
		mStack.push(state);
		
		
	
	}

	protected State pop() {
		return mStack.pop();
	}

	protected State peek() {
		return mStack.peek();
	}
}   