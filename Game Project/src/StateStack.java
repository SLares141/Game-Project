import java.awt.Graphics;
import java.util.Stack;
import java.util.HashMap;

/* This serves as a way of switching between the game's states. 
 * In using the state machine, we avoid a single, bloated update method.
 * Instead, we will have different methods of updating and rendering the game in each state.
 */
public class StateStack {
	
	public Graphics g;
	//The HashMap gives us a way of accessing the states easily by using a certain key 
	HashMap<String, State> stateMap;
	
	//Using a stack allows us to have previous states waiting around for later use.
	//For example, we can push the battle state onto the stack. After the battle, we only have to pop it off to get back to the standard game map.
	Stack<State> mStack;
	
	public StateStack(HashMap<String, State> stateMap) {
		this.stateMap = stateMap;
		mStack = new Stack<State>();
	}
	
	public void update() {
        State top = mStack.peek();
        top.update();
    }
 
    public void render() {
        State top = mStack.peek();
        top.render();
    }
 
    public void push(String name) {
        State state = stateMap.get(name);
        mStack.push(state);
    }
 
    public State pop() {
        return mStack.pop();
    }
    
    public State peek() {
    	return mStack.peek();
    }
}
