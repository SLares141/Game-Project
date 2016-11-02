
public class Inventory {
	private class ItemNode {
		private Item i;
		private int amount;
		
		public ItemNode(Item i, int amount) {
			this.i = i;
			this.amount = amount;
		}
		
		public Item getItem() {
			return i;
		}
		public int getAmount() {
			return amount;
		}
		public void increment() {
			amount++;
		}
		public void decrement() {
			amount--;
		}
	}
	private final int SIZE = 50;
	private int money = 420;
	
	ItemNode[] inv = new ItemNode[SIZE];
	
	/*
	public void add(Item i) {
		for(int j = 0; j < SIZE-1; j++) {
			if(inv[j] == null)
				inv[j] = new ItemNode(i, 1);
			else if(inv[j].getItem().equals(i)) //may have to write special .equals() method in Item class
				inv[j].increment();
		}
	}
	
	public Item use(int index) {
		c.use(inv[index].getItem());
		if(inv[index].getAmount() == 1) {
			Item temp = inv[index].getItem();
			inv[index] = null;
			return temp;
		}
		inv[index].decrement();
		return inv[index].getItem();	
	}
	*/
	
	public Item getItem(int index) {
		return inv[index].getItem();
	}
	
	public int getMoney() {
		return money;
	}
}
