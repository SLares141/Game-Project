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
	private static WindowFrame _frame = WindowFrame.getInstance(); //used to streamline _frame adding and removing states
	private static StateStackSingleton StateStackSingleton = new StateStackSingleton();
	private int gameCounter = 0;

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

	protected void push(BattleState bs){
		
		if(!mStack.isEmpty()) //means that the old state can be removed from the windowFrame.	 
		{
			State oldState = peek();
			_frame.removeState(oldState); //gets old state and removes it from windowFrame
			oldState.onExit();
		}
			
		
		//State newState = stateMap.get(name); //state the user wants to enter here.
		_frame.addState(bs); // in with the new
		bs.onEnter(); //do entrance code
		
		mStack.push(bs); //actually pushes it to the stack
	}
	protected void push(String name) {
		
		if(!mStack.isEmpty()) //means that the old state can be removed from the windowFrame.	 
		{
			State oldState = peek();
			_frame.removeState(oldState); //gets old state and removes it from windowFrame
			oldState.onExit();
		}
			
		
		State newState = stateMap.get(name); //state the user wants to enter here.
		_frame.addState(newState); // in with the new
		newState.onEnter(); //do entrance code
		
		mStack.push(newState); //actually pushes it to the stack
		
	}

	protected State pop() {
		State currentState = mStack.pop(); //pops the value out
		_frame.removeState(currentState); //removes it from the frame
		currentState.onExit(); //exit code
		
		if(!mStack.isEmpty())
		{
			State newState = peek();
			_frame.addState(newState); //new state is added to windowFrame
			newState.onEnter(); //calls entrance code
		}
			
		return currentState; //still returns expected value
	}
	
	protected void popAndPush() {
		State currentState = mStack.pop(); //pops the value out
		_frame.removeState(currentState); //removes it from the frame
		currentState.onExit(); //exit code
		
		
		push("field" + gameCounter);
		
		
		/*State newState = stateMap.get("field" + gameCounter); //state the user wants to enter here.
		_frame.addState(newState); // in with the new
		((FieldState) newState).getPlayer().prepareNextLevel();
		((FieldState) newState).setPlayer(new Player());
		
		newState.onEnter(); //do entrance code
		
		mStack.push(newState); //actually pushes it to the stack
		*/
	}
	
	
	
	protected State peek() {
		return mStack.peek();
	}
	
	public void incrementCount() { gameCounter++; }
	public int getCount() { return gameCounter; }
	
	
}   
