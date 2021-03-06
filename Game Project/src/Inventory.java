import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

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
	private int numItems = 0;
	private int numEquip = 0;
	private boolean invFull;
	private boolean equipFull;

	ItemNode[] inv = new ItemNode[SIZE];
	ItemNode[] equipment = new ItemNode[SIZE];
	
	public static ItemMap itemMap = new ItemMap();

	//adds one of a specific item
	public void add(Item i) {
		for(int j = 0; j < SIZE; j++) {
			//for consumables
			if(i instanceof Consumable) {
				if(numItems == SIZE) {
					invFull = true;
					break;
				}
				if(inv[j] == null) {
					inv[j] = new ItemNode(i, 1);
					numItems++;
					break;
				}
				else if(inv[j].getItem().getName().equals(i.getName()) && inv[j].getAmount() < 99) {
					inv[j].increment();
					break;
				}
				else if(inv[j].getItem().getName().equals(i.getName()) && inv[j].getAmount() == 99) {
					break;
				}
			}
			//for equipment
			else if(i instanceof Weapon || i instanceof Armor){
				if(numEquip == SIZE) {
					equipFull = true;
					break;
				}
				if(equipment[j] == null) {
					equipment[j] = new ItemNode(i, 1);
					numEquip++;
					break;
				}
				else if(equipment[j].getItem().getName().equals(i.getName()) && equipment[j].getAmount() < 99) {
					equipment[j].increment();
					break;
				}
				else if(equipment[j].getItem().getName().equals(i.getName()) && equipment[j].getAmount() == 99) {
					break;
				}
			}
		}
	}

	//adds multiple of a specific item
	public void add(Item i, int x) {
		int count = 1;
		//for consumables
		if(i instanceof Consumable) {
			while(count <= x && numItems < SIZE) {
				for(int j = 0; j < SIZE; j++) {
					if(inv[j] == null) {
						inv[j] = new ItemNode(i, 1);
						numItems++;
						break;
					}
					else if(inv[j].getItem().getName().equals(i.getName()) && inv[j].getAmount() < 99) {
						inv[j].increment();
						break;
					}
					else if(inv[j].getItem().getName().equals(i.getName()) && inv[j].getAmount() == 99) {
						break;
					}
				}
				count++;
			}
		}
		//for equipment
		else if(i instanceof Weapon || i instanceof Armor) {
			while(count <= x && numEquip < SIZE) {
				for(int j = 0; j < SIZE; j++) {
					if(equipment[j] == null) {
						equipment[j] = new ItemNode(i, 1);
						numEquip++;
						break;
					}
					else if(equipment[j].getItem().getName().equals(i.getName()) && equipment[j].getAmount() < 99) {
						equipment[j].increment();
						break;
					}
					else if(equipment[j].getItem().getName().equals(i.getName()) && equipment[j].getAmount() == 99) {
						break;
					}
				}
				count++;
			}
		}
	}

	//uses a consumable on a character
	public Consumable use(int index, Character c) {
		Consumable cons = (Consumable)inv[index].getItem();
		c.use(cons);
		if(inv[index].getAmount() == 1) {
			inv[index] = null;
			//moves each subsequent item back
			for(int j = index; j < numItems - 1; j++) {
				inv[j] = inv[j + 1];
				inv[j + 1] = null;
			}
			numItems--;
			return cons;
		}
		inv[index].decrement();
		return cons;	
	}

	//equips a weapon or armor to a character
	public Item equip(int index, Character c) {
		if(equipment[index].getItem() instanceof Weapon) {
			Weapon w = (Weapon)equipment[index].getItem();
			if(c.getWeapon() != null && !c.getWeapon().getName().equals("no weapon")) 
				add(c.getWeapon());
			c.equip(w);
			if(equipment[index].getAmount() == 1) {
				equipment[index] = null;
				//moves each subsequent piece of equipment back 
				for(int j = index; j < numEquip - 1; j++) {
					equipment[j] = equipment[j + 1];
					equipment[j + 1] = null;
				}
				numEquip--;
				return w;
			}
			equipment[index].decrement();
			return w;	
		}
		else {
			Armor a = (Armor)equipment[index].getItem();
			if(c.getArmor() != null && !c.getArmor().getName().equals("no armor")) 
				add(c.getArmor());
			c.equip(a);
			if(equipment[index].getAmount() == 1) {
				equipment[index] = null;
				//moves each subsequent piece of equipment back 
				for(int j = index; j < numEquip - 1; j++) {
					equipment[j] = equipment[j + 1];
					equipment[j + 1] = null;
				}
				numEquip--;
				return a;
			}
			equipment[index].decrement();
			return a;	
		}
	}
	public void equip(Weapon w, Character c) {
		if(w.getName().equals("no weapon")) {
			if(c.getWeapon() != null && !c.getWeapon().getName().equals("no weapon")) 
				add(c.getWeapon());
			c.equip(w);
		}
	}
	public void equip(Armor a, Character c) {
		if(a.getName().equals("no armor")) {
			if(c.getArmor() != null && !c.getArmor().getName().equals("no armor")) 
				add(c.getArmor());
			c.equip(a);
		}
	}

	public void clear() {
		for(int j = 0; j < SIZE; j++) {
			inv[j] = null;
			numItems = 0;
			equipment[j] = null;
			numEquip = 0;
		}
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

	public int getNumItems() {
		return numItems;
	}
	public int getNumEquip() {
		return numEquip;
	}
}