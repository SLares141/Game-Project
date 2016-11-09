import java.util.ArrayList;

public class StateMapSingleton {
	private class Node {
		private String name;
		private State state;

		public Node(String name, State state) {
			this.name = name;
			this.state = state;
		}

		public String getName() {
			return name;
		}
		public State getState() {
			return state;
		}
	}

	private ArrayList<Node> map = new ArrayList<Node>();

	private static StateMapSingleton StateMapSingleton = new StateMapSingleton();

	private StateMapSingleton() {}

	public static StateMapSingleton getInstance() {
		return StateMapSingleton;
	}

	public void put(String name, State state) {
		Node n = new Node(name, state);
		map.add(n);
	}

	public State get(String name) {
		for(Node n: map) {
			if(name.equals(n.getName()))
				return n.getState();
		}
		return null;
	}
}