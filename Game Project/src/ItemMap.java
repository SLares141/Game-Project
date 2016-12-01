import java.util.ArrayList;

public class ItemMap {
	private class Node {
		private String name;
		private Item item;

		public Node(String name, Item item) {
			this.name = name;
			this.item = item;
		}

		public String getName() {
			return name;
		}
		public Item getItem() {
			return item;
		}
	}
	private ArrayList<Node> map = new ArrayList<Node>();

	public ItemMap() {
		map.add(new Node("Strawberry Juice", new Consumable("Strawberry Juice", 5, true)));
		map.add(new Node("Strawberry Jelly", new Consumable("Strawberry Jelly", 10, true)));
		map.add(new Node("Strawberry Jam", new Consumable("Strawberry Jam", 15, true)));
		map.add(new Node("Moo Moo Milk", new Consumable("Moo Moo Milk", 100, true)));
		map.add(new Node("Elixer", new Consumable("Moo Moo Milk", 10, false)));
		
		map.add(new Node("Sword", new Weapon("Sword", 5)));
		map.add(new Node("Staff", new Weapon("Staff", 5)));
		map.add(new Node("no weapon", new Weapon("no weapon", 0)));
		
		map.add(new Node("Shield", new Armor("Shield", 5, 1)));
		map.add(new Node("Robe", new Armor("Robe", 1, 5)));
		map.add(new Node("no armor", new Armor("no armor", 0, 0)));
	}
	
	public Item get(String name) {
		for(Node n: map) {
			if(name.equals(n.getName()))
				return n.getItem();
		}
		return null;
	}
}

