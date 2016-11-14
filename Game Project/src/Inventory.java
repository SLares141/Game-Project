
public class Inventory  {
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
		public void setAmount(int amount) {
			this.amount = amount;
		}
		public void increment() {
			amount++;
		}
		public void decrement() {
			amount--;
		}
	}
	private final int SIZE = 18;
	private int money = 420;
	private int numItems = 0;
	
	ItemNode[] inv = new ItemNode[SIZE];
	
	public void add(Item i) {
		for(int j = 0; j < SIZE; j++) {
			if(numItems == SIZE) {
				//indicate that inventory is full
				break;
			}
			
			if(inv[j] == null) {
				inv[j] = new ItemNode(i, 1);
				numItems++;
				break;
			}
			else if(inv[j].getItem().equals(i) && inv[j].getAmount() < 99) {
				inv[j].increment();
				break;
			}
			else if(inv[j].getItem().equals(i) && inv[j].getAmount() == 99) {
				break;
			}
		}
	}
	
	public void add(Item i, int x) {
		int count = 1;
		while(count <= x && numItems < SIZE) {
			for(int j = 0; j < SIZE; j++) {
				if(inv[j] == null) {
					inv[j] = new ItemNode(i, 1);
					numItems++;
					break;
				}
				else if(inv[j].getItem().equals(i) && inv[j].getAmount() < 99) {
					inv[j].increment();
					break;
				}
				else if(inv[j].getItem().equals(i) && inv[j].getAmount() == 99) {
					break;
				}
			}
			count++;
		}
	}
	
	public Item use(int index, Character c) {
		Consumable cons = (Consumable)inv[index].getItem();
		cons.use(c);
		if(inv[index].getAmount() == 1) {
			inv[index] = null;
			for(int j = index; j < numItems; j++) {
				inv[j] = inv[j + 1];
				inv[j + 1] = null;
			}
			numItems--;
			return cons;
		}
		inv[index].decrement();
		return cons;	
	}

	public int getItemAmount(int index) {
		return inv[index].getAmount();
	}
	public Item getItem(int index) {
		if(inv[index] != null) 
			return inv[index].getItem();
		return null;
	}
	
	public int getMoney() {
		return money;
	}
	public int getNumItems() {
		return numItems;
	}
}
