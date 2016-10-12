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

	public State get(String name) throws Exception {
		int i = -1;
		for(int j = 0; j < map.size(); j++) {
			if(name.equals(map.get(j).getName()))
				i = j;
		}
		if(i < 0)
			throw new Exception("State name is invalid.");
		return map.get(i).getState();
	}
}