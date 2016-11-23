import java.awt.Image;

public class Item {
	
	protected String name;
	protected Image sprite;
	protected String description;
	
	public Item(String name) { this.name = name; } // constructor
	
	public String getName() { return name; }
	public void setName(String s) { name = s; }
	
	public boolean equals(Item i) {
		if(name == i.getName())
			return true;
		return false;
	}
}