
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
	private int numEquip = 0;
	
	ItemNode[] inv = new ItemNode[SIZE];
	ItemNode[] equipment = new ItemNode[SIZE];
	
	public Inventory() {
		buildEquipment();
	}
	
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
		buildEquipment();
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
		buildEquipment();
	}
	
	public Item use(int index, Character c) {
		Consumable cons = (Consumable)inv[index].getItem();
		cons.use(c);
		if(inv[index].getAmount() == 1) {
			inv[index] = null;
			for(int j = index; j < numItems - 1; j++) {
				System.out.println(j);
				inv[j] = inv[j + 1];
				inv[j + 1] = null;
			}
			numItems--;
			buildEquipment();
			return cons;
		}
		inv[index].decrement();
		buildEquipment();
		return cons;	
	}
	
	public void buildEquipment() {
		clearEquipment();
		int i = 0;
		for(int j = 0; j < numItems; j++) {
			if(inv[j].getItem() instanceof Weapon || inv[j].getItem() instanceof Armor) {
				equipment[i] = inv[j];
				numEquip++;
				i++;
			}
		}
	}
	public void clearEquipment() {
		for(int j = 0; j < SIZE; j++) 
			equipment[j] = null;
		numEquip = 0;
	}

	public int getItemAmount(int index) {
		return inv[index].getAmount();
	}
	public Item getItem(int index) {
		if(inv[index] != null) 
			return inv[index].getItem();
		return null;
	}
	public int getEquipAmount(int index) {
		return equipment[index].getAmount();
	}
	public Item getEquip(int index) {
		if(equipment[index] != null) 
			return equipment[index].getItem();
		return null;
	}
	
	public int getMoney() {
		return money;
	}
	public int getNumItems() {
		return numItems;
	}
	public int getNumEquip() {
		return numEquip;
	}
}

